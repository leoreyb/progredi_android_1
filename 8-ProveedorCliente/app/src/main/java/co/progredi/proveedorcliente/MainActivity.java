package co.progredi.proveedorcliente;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import co.progredi.proveedorcliente.negocio.proveedor.ClienteProveedor;
import co.progredi.proveedorcliente.persistencia.entidad.Persona;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private android.widget.EditText etNombre;
    private android.widget.EditText etApellido;
    private android.widget.EditText etCorreo;
    private android.widget.Button btnRegistrar;
    private ClienteProveedor clienteProveedor;
    private List<Persona> listaPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clienteProveedor = new ClienteProveedor(this);
        this.btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        this.etCorreo = (EditText) findViewById(R.id.etCorreo);
        this.etApellido = (EditText) findViewById(R.id.etApellido);
        this.etNombre = (EditText) findViewById(R.id.etNombre);
        this.btnRegistrar.setOnClickListener(this);
        consultar();
    }

    private void consultar() {
        try {
            listaPersonas = clienteProveedor.consultar();
            Log.d("Lista Personas ", "" + listaPersonas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            String nombre = etNombre.getText().toString();
            String apellido = etApellido.getText().toString();
            String correo = etCorreo.getText().toString();
            Persona persona = new Persona();
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setCorreo(correo);
            clienteProveedor.insertar(persona);
            Toast.makeText(this, "Se insertó correctamente " + persona.getId(), Toast.LENGTH_LONG).show();
            etNombre.setText("");
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage() + "", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


}
