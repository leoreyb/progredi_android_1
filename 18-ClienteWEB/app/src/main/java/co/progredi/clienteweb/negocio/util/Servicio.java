package co.progredi.clienteweb.negocio.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

import co.progredi.clienteweb.persistencia.dto.RespuestaDTO;

/**
 * Created by lrey on 8/17/17.
 */

public class Servicio {

    private static final String urlServidor = "http://192.168.0.24:8080/AndroidWEB/rest/dispositivo";

    public static void invocar(final Object parametros, final String nombreParametros, final Type tipoRespuesta, final Handler puente, final String ruta) {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                Message mensaje = new Message();
                RespuestaDTO respuesta = new RespuestaDTO();
                String parametro = new Gson().toJson(parametros);
                Log.d("Parametros", parametro);
                try {
                    URL url = new URL(urlServidor + ruta);
                    HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                    conexion.setRequestMethod("POST");
                    conexion.setRequestProperty("Content-Type", "application/json");
                    conexion.setDoInput(true);
                    conexion.setDoOutput(true);
                    OutputStreamWriter out = new OutputStreamWriter(conexion.getOutputStream());
                    out.write(parametro);
                    out.flush();
                    out.close();
                    BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                    String linea = lector.readLine();
                    String contenido = "";
                    while (linea != null) {
                        contenido += linea;
                        linea = lector.readLine();
                    }
                    lector.close();
                    Log.d("contenido", contenido);
                    respuesta = new Gson().fromJson(contenido, tipoRespuesta);
                    respuesta.setRuta(ruta);
                    mensaje.obj = respuesta;
                    conexion.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    respuesta.setCodigo(-1);
                    respuesta.setMensaje("Error fatal " + e.getMessage());
                    mensaje.obj = respuesta;
                }
                puente.sendMessage(mensaje);
            }
        });
        hilo.start();

    }
}
