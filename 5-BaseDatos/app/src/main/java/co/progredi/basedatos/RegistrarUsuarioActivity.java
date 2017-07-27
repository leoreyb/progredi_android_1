package co.progredi.basedatos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import co.progredi.basedatos.persistencia.dao.UsuarioDAO;
import co.progredi.basedatos.persistencia.vo.Usuario;

public class RegistrarUsuarioActivity extends AppCompatActivity {


    private android.widget.EditText etNombre;
    private android.widget.EditText etApellido;
    private android.widget.EditText etCorreo;
    private android.widget.EditText etClave;
    private android.widget.Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        this.btnGuardar = (Button) findViewById(R.id.btnGuardar);
        this.etClave = (EditText) findViewById(R.id.etClave);
        this.etCorreo = (EditText) findViewById(R.id.etCorreo);
        this.etApellido = (EditText) findViewById(R.id.etApellido);
        this.etNombre = (EditText) findViewById(R.id.etNombre);


        this.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setNombre(etNombre.getText().toString());
                usuario.setApellido(etApellido.getText().toString());
                usuario.setCorreo(etCorreo.getText().toString());
                usuario.setClave(etClave.getText().toString());

                new UsuarioDAO(RegistrarUsuarioActivity.this).insertar(usuario);
                finish();
            }
        });
    }

}
