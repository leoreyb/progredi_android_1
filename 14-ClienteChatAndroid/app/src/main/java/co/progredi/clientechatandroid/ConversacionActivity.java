package co.progredi.clientechatandroid;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.PrintStream;
import java.util.Date;

import co.progredi.clientechatandroid.persistencia.dto.MensajeDTO;

public class ConversacionActivity extends AppCompatActivity implements OnClickListener, Handler.Callback {


    private android.widget.ListView lstMensajes;
    private android.widget.EditText etMensaje;
    private android.widget.Button btnEnviar;
    private String usuario;
    private Thread hiloConexion;
    private Handler puente = new Handler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversacion);
        this.btnEnviar = (Button) findViewById(R.id.btnEnviar);
        this.etMensaje = (EditText) findViewById(R.id.etMensaje);
        this.lstMensajes = (ListView) findViewById(R.id.lstMensajes);
        usuario = getIntent().getExtras().getString("usuario");
        btnEnviar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        hiloConexion = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String mensaje = etMensaje.getText().toString();
                    MensajeDTO mensajeDTO = new MensajeDTO();
                    mensajeDTO.setMensaje(mensaje);
                    mensajeDTO.setUsuario(usuario);
                    mensajeDTO.setFecha(new Date().getTime() + "");
                    PrintStream escribir = new PrintStream(MainActivity.cliente.getOutputStream());
                    escribir.println(new Gson().toJson(mensajeDTO));
                    escribir.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    Message mensaje = new Message();
                    mensaje.obj = "Debe verificar su conexión a internet " + e.getMessage();
                    puente.sendMessage(mensaje);
                }
            }
        });
        hiloConexion.start();

    }

    @Override
    public boolean handleMessage(Message msg) {
        Toast.makeText(this, msg.obj.toString(), Toast.LENGTH_LONG).show();
        return false;
    }
}
