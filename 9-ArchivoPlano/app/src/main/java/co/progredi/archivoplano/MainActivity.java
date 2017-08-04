package co.progredi.archivoplano;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.progredi.archivoplano.negocio.util.ArchivoUtil;
import co.progredi.archivoplano.persistencia.entidad.Producto;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Producto> listaProductos = ArchivoUtil.leer();
        Log.d("Lista productos ", "" + listaProductos);
    }


    private void guardarProducto() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Bolsa");
        producto.setDescripcion("plástica");
        producto.setValor(80F);
        List<Producto> lista = new ArrayList<>();
        lista.add(producto);
        ArchivoUtil.escritura(lista);
    }
}
