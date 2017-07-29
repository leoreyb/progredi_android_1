package co.progredi.aplicacion.vista;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import co.progredi.aplicacion.R;
import co.progredi.aplicacion.negocio.delegados.PersonaDelegado;
import co.progredi.aplicacion.persistencia.entidades.Persona;

public class MainActivity extends GenericoActivity {

    private PersonaDelegado personaDelegado;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private EditText etNombre;
    private EditText etApellido;
    private EditText etCorreo;
    private EditText etCelular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personaDelegado = new PersonaDelegado(MainActivity.this);
        setContentView(R.layout.activity_main);
        this.fab = (FloatingActionButton) findViewById(R.id.fab);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.etNombre = (EditText) findViewById(R.id.etNombre);
        this.etApellido = (EditText) findViewById(R.id.etApellido);
        this.etCorreo = (EditText) findViewById(R.id.etCorreo);
        this.etCelular = (EditText) findViewById(R.id.etCelular);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String correo = etCorreo.getText().toString();
                String celular = etCelular.getText().toString();
                Persona persona = new Persona();
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                persona.setCorreo(correo);
                persona.setCelular(celular);
                personaDelegado.insertar(persona);
                mensaje(view, "Se registro la información correctamente id:" + persona.getIdPersona());
                mensaje("Se registro la información correctamente id:" + persona.getIdPersona());
            }
        });
    }

    @Override
    public void init() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
