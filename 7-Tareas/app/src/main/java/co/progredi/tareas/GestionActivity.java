package co.progredi.tareas;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GestionActivity extends AppCompatActivity {

    private List<String> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarAlerta();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });

        setTitle("Registro de tareas");
        llenarLista();
    }

    private void llenarLista() {
        for (int i = 0; i < 50; i++) {
            lista.add("Tarea " + i);
        }
        ListView lst = (ListView) findViewById(R.id.lst);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);

        lst.setAdapter(adaptador);
    }

    private void mostrarAlerta() {

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Gestionar Tarea");
        alerta.setPositiveButton("Finalizar", null);
        alerta.setNegativeButton("Editar", null);
        alerta.setNeutralButton("Eliminar", null);
        View view = LayoutInflater.from(this).inflate(R.layout.item_alerta, null);
        alerta.setView(view);
        alerta.show();

    }

}
