package co.progredi.archivosxml;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import co.progredi.archivosxml.negocio.util.ArchivoDOM;
import co.progredi.archivosxml.negocio.util.ArchivoSAX;
import co.progredi.archivosxml.persistencia.entidad.Persona;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private android.widget.EditText etNombre;
    private android.widget.EditText etApellido;
    private android.widget.EditText etCorreo;
    private android.widget.EditText etDocumento;
    private android.widget.Button btnGuardar;
    private android.widget.Button btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnListar = (Button) findViewById(R.id.btnListar);
        this.btnGuardar = (Button) findViewById(R.id.btnGuardar);
        this.etDocumento = (EditText) findViewById(R.id.etDocumento);
        this.etCorreo = (EditText) findViewById(R.id.etCorreo);
        this.etApellido = (EditText) findViewById(R.id.etApellido);
        this.etNombre = (EditText) findViewById(R.id.etNombre);
        btnGuardar.setOnClickListener(this);
        btnListar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGuardar:
                guardar();
                break;
            case R.id.btnListar:
                listar();
                break;
        }
    }


    //    private void guardar() {
//        ArchivoSAX archivoSAX = new ArchivoSAX();
//        List<Persona> listaPersonas = archivoSAX.leer();
//        Persona persona = new Persona();
//        persona.setNombre(etNombre.getText().toString());
//        persona.setApellido(etApellido.getText().toString());
//        persona.setCorreo(etCorreo.getText().toString());
//        persona.setDocumento(etDocumento.getText().toString());
//        listaPersonas.add(persona);
//        archivoSAX.escritura(listaPersonas);
//        Toast.makeText(this, "Se registró correctamente ", Toast.LENGTH_LONG).show();
//    }
    private void guardar() {
        ArchivoDOM archivoDOM = new ArchivoDOM();
        List<Persona> listaPersonas = archivoDOM.leer();
        Persona persona = new Persona();
        persona.setNombre(etNombre.getText().toString());
        persona.setApellido(etApellido.getText().toString());
        persona.setCorreo(etCorreo.getText().toString());
        persona.setDocumento(etDocumento.getText().toString());
        listaPersonas.add(persona);
        archivoDOM.escribir(listaPersonas);
        Toast.makeText(this, "Se registró correctamente ", Toast.LENGTH_LONG).show();
    }

    private void listar() {
        ArchivoSAX archivoSAX = new ArchivoSAX();
        List<Persona> lista = archivoSAX.leer();
        Log.d("Lista Personas ", lista + "");
    }
}
