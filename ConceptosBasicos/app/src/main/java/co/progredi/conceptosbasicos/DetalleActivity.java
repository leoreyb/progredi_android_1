package co.progredi.conceptosbasicos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvApellido;
    private TextView tvCorreo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        String nombre = getIntent().getExtras().getString("nombre");
        String apellido = getIntent().getExtras().getString("apellido");
        String correo = getIntent().getExtras().getString("correo");
        tvNombre = (TextView) findViewById(R.id.tvNombreDetalle);
        tvApellido = (TextView) findViewById(R.id.tvApellidoDetalle);
        tvCorreo = (TextView) findViewById(R.id.tvCorreoDetalle);
        tvNombre.setText(nombre);
        tvApellido.setText(apellido);
        tvCorreo.setText(correo);
    }
}
