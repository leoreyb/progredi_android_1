package co.progredi.componentes;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private android.widget.ListView lstProductos;
    private android.widget.Button btnEliminar;
    private ArrayList<String> listaProductos = new ArrayList<>();
    private ArrayAdapter<String> adaptador;
    private TableLayout tlContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Se verifica si es la primera vez que se ejecuta la aplicación
        if (savedInstanceState == null) {
            llenarListaArreglo();
        }

        if (getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            Log.d("Pantalla", "Vertical");
            this.btnEliminar = (Button) findViewById(R.id.btnEliminar);
            this.lstProductos = (ListView) findViewById(R.id.lstProductos);
            llenarListView();
            this.btnEliminar.setOnClickListener(eventoClic);
        } else {
            tlContactos = (TableLayout) findViewById(R.id.tlProductos);
            llenarTabla();
        }
    }

    private void llenarTabla() {
        for (int i = 0; i < listaProductos.size(); i++) {
            String nombreProducto = listaProductos.get(i);
            TableRow fila = new TableRow(MainActivity.this);

            TextView producto = new TextView(MainActivity.this);
            producto.setText(nombreProducto);
            fila.addView(producto);

            Button btnDinamico = new Button(MainActivity.this);
            btnDinamico.setText("Eliminar");
            btnDinamico.setTag(nombreProducto);
            btnDinamico.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View boton) {
                    String nombre = (String) boton.getTag();
                    for (int i = 0; i < listaProductos.size(); i++) {
                        if (listaProductos.get(i).equals(nombre)) {
                            listaProductos.remove(i);
                        }
                    }
                    tlContactos.removeView((View) boton.getParent());
                }
            });
            fila.addView(btnDinamico);

            tlContactos.addView(fila);
        }

    }

    private void llenarListaArreglo() {
        for (int i = 0; i < 20; i++) {
            listaProductos.add("Producto " + i);
        }
    }

    private void llenarListView() {
        adaptador = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_checked,
                listaProductos);
        lstProductos.setAdapter(adaptador);
        lstProductos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        adaptador.notifyDataSetChanged();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("listaProductos", listaProductos);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        listaProductos = (ArrayList<String>) savedInstanceState.getSerializable("listaProductos");
        Log.d("Tamaño ", "" + listaProductos.size());
        if (getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            llenarListView();
        } else {
            llenarTabla();
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    private View.OnClickListener eventoClic = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SparseBooleanArray itemsSeleccionados = lstProductos.getCheckedItemPositions();
            for (int i = itemsSeleccionados.size() - 1; i >= 0; i--) {
                int llave = itemsSeleccionados.keyAt(i);
                boolean esSeleccionado = itemsSeleccionados.get(llave);
                if (esSeleccionado) {
                    listaProductos.remove(llave);
                }
            }
            adaptador.notifyDataSetChanged();
            lstProductos.clearChoices();
        }
    };
}
