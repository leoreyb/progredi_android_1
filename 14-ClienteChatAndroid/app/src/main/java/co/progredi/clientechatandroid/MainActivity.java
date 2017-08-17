package co.progredi.clientechatandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Handler.Callback {

    private android.widget.EditText etIp;
    private android.widget.EditText etPuerto;
    private android.widget.EditText etUsuario;
    private android.widget.Button btnConectar;
    public static Socket cliente;
    private Thread hiloConexion;
    private Handler puente = new Handler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnConectar = (Button) findViewById(R.id.btnConectar);
        this.etUsuario = (EditText) findViewById(R.id.etUsuario);
        this.etPuerto = (EditText) findViewById(R.id.etPuerto);
        this.etIp = (EditText) findViewById(R.id.etIp);
        this.btnConectar.setOnClickListener(this);
        //   puente = new Handler(this);
    }

    @Override
    public void onClick(View v) {
        hiloConexion = new Thread(new Runnable() {
            @Override
            public void run() {
                Message mensaje = new Message();
                try {
                    String ip = etIp.getText().toString();
                    String puerto = etPuerto.getText().toString();
                    cliente = new Socket(ip, Integer.parseInt(puerto));
                    Intent intent = new Intent(MainActivity.this, ConversacionActivity.class);
                    intent.putExtra("usuario", etUsuario.getText().toString());
                    startActivity(intent);
                    mensaje.obj = "Se conectó correctamente";
                } catch (Exception e) {
                    e.printStackTrace();
                    mensaje.obj = "Error de conexión";

                }
                puente.sendMessage(mensaje);
            }
        });
        hiloConexion.start();
    }

    @Override
    public boolean handleMessage(Message msg) {
        Toast.makeText(MainActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
        return false;
    }
}
