package co.progredi.clienteweb;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import co.progredi.clienteweb.negocio.util.Servicio;
import co.progredi.clienteweb.persistencia.dto.RespuestaDTO;
import co.progredi.clienteweb.persistencia.entidad.Dispositivo;

public class MainActivity extends AppCompatActivity implements Handler.Callback {


    private Handler puente;
    private android.widget.ListView lstDispositivos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.lstDispositivos = (ListView) findViewById(R.id.lstDispositivos);
        puente = new Handler(this);
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setIdDispositivo(8);
        dispositivo.setMarca("Sony18");
        dispositivo.setReferencia("Z34");
        dispositivo.setColor("Negro6");
        Type tipo = new TypeToken<RespuestaDTO<Dispositivo>>() {
        }.getType();
//         Servicio.invocar(dispositivo, "info", tipo, puente, "/insertar");
        Type tipoConsulta = new TypeToken<RespuestaDTO<List<Dispositivo>>>() {
        }.getType();
        Servicio.invocar(dispositivo, null, tipoConsulta, puente, "/consultar/criterio");
    }

    @Override
    public boolean handleMessage(Message msg) {
        RespuestaDTO respuesta = (RespuestaDTO) msg.obj;
        if (respuesta.getCodigo() <= 0) {
            Toast.makeText(this, respuesta.getMensaje(), Toast.LENGTH_LONG).show();
            return false;
        }
        String ruta = respuesta.getRuta();
        if (ruta.startsWith("/consultar")) {
            consultar(respuesta);
            return false;
        }
        switch (ruta) {
            case "/insertar":
                insertar(respuesta);
                break;
        }
        return false;
    }

    private void insertar(RespuestaDTO<Dispositivo> respuesta) {

        Toast.makeText(this, "Número de registro " + respuesta.getDatos().getIdDispositivo(), Toast.LENGTH_LONG).show();
    }

    private void consultar(RespuestaDTO<List<Dispositivo>> respuesta) {
        ArrayAdapter<Dispositivo> adaptador = new ArrayAdapter<Dispositivo>
                (this, android.R.layout.simple_list_item_1, respuesta.getDatos());
        lstDispositivos.setAdapter(adaptador);
    }

}
