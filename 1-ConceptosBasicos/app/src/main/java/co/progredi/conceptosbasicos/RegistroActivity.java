package co.progredi.conceptosbasicos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistroActivity extends AppCompatActivity {


    private android.widget.TextView tvNombre;
    private android.widget.EditText etNombre;
    private android.widget.TextView tvApellido;
    private android.widget.EditText etApellido;
    private android.widget.TextView tvCorreo;
    private android.widget.EditText etCorreo;
    private android.widget.Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        this.btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        this.etCorreo = (EditText) findViewById(R.id.etCorreo);
        this.tvCorreo = (TextView) findViewById(R.id.tvCorreo);
        this.etApellido = (EditText) findViewById(R.id.etApellido);
        this.tvApellido = (TextView) findViewById(R.id.tvApellido);
        this.etNombre = (EditText) findViewById(R.id.etNombre);
        this.tvNombre = (TextView) findViewById(R.id.tvNombre);

        this.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String correo = etCorreo.getText().toString();
                Intent intentDetalle = new Intent(RegistroActivity.this, DetalleActivity.class);
                intentDetalle.putExtra("nombre", nombre);
                intentDetalle.putExtra("apellido", apellido);
                intentDetalle.putExtra("correo", correo);
                startActivity(intentDetalle);
            }
        });
    }


}
