package co.progredi.vistaspersonalizadas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import co.progredi.vistaspersonalizadas.adaptador.ItemAdapter;
import co.progredi.vistaspersonalizadas.modelo.dto.ItemDTO;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ItemDTO> listaItems = new ArrayList<>();
    private ListView lstContactos;
    private ItemAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llenarLista();

        lstContactos = (ListView) findViewById(R.id.lstContactos);
        adaptador = new ItemAdapter(listaItems, MainActivity.this);
        lstContactos.setAdapter(adaptador);
        lstContactos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    private void llenarLista() {
        //Consulta  a base de datos
        for (int i = 0; i <= 20; i++) {
            ItemDTO item = new ItemDTO();
            item.setDescripcion("Descripción " + i);
            item.setTitulo("Título " + i);
            item.setId((long) i);
            listaItems.add(item);
        }
    }
}
