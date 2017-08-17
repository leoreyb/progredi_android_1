package co.progredi.clienteweb;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

import co.progredi.clienteweb.persistencia.dto.RespuestaDTO;
import co.progredi.clienteweb.persistencia.entidad.Dispositivo;

public class MainActivity extends AppCompatActivity implements Handler.Callback, Runnable {

    private Thread hilo;
    private Handler puente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hilo = new Thread(this);
        puente = new Handler(this);
        hilo.start();
    }

    @Override
    public boolean handleMessage(Message msg) {
        Toast.makeText(this, ((RespuestaDTO) msg.obj).getMensaje(), Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void run() {
        try {
            invocar();
        } catch (Exception e) {
            e.printStackTrace();
            Message mensaje = new Message();
            RespuestaDTO respuesta = new RespuestaDTO();
            respuesta.setCodigo(-1);
            respuesta.setMensaje("Error fatal");
            mensaje.obj = respuesta;
            puente.sendMessage(mensaje);
        }
    }

    private void invocar() throws IOException {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setColor("Negro");
        dispositivo.setReferencia("Iphone");
        dispositivo.setMarca("Apple");

        String urlServidor = "http://192.168.0.24:8080/AndroidWEB/insertar";
        URL url = new URL(urlServidor);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setDoInput(true);
        conexion.setDoOutput(true);
        conexion.setRequestMethod("POST");

        OutputStreamWriter out = new OutputStreamWriter(conexion.getOutputStream());
        out.write("dispositivo=" + new Gson().toJson(dispositivo));
        out.write("&ime=123");
        out.flush();
        out.close();

        BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea = lector.readLine();
        String contenido = "";

        while (linea != null) {
            contenido += linea;
            linea = lector.readLine();
        }

        Type tipo = new TypeToken<RespuestaDTO<Dispositivo>>() {
        }.getType();

        RespuestaDTO<Dispositivo> respuesta = new Gson().fromJson(contenido, tipo);
        Message mensaje = new Message();
        mensaje.obj = respuesta;
        puente.sendMessage(mensaje);

    }
}
