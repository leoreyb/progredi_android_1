package co.progredi.fragmentos.vista.fragmento;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import co.progredi.fragmentos.R;

/**
 * Created by lrey on 8/11/17.
 */

public class FragmentoRegistrarProducto extends GenericoFragmento {

    private android.widget.EditText etMarca;
    private android.widget.EditText etProducto;
    private android.widget.EditText etPrecio;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View formulario = inflater.inflate(R.layout.fragmento_registrar_producto, null);
        this.etPrecio = (EditText) formulario.findViewById(R.id.etPrecio);
        this.etProducto = (EditText) formulario.findViewById(R.id.etProducto);
        this.etMarca = (EditText) formulario.findViewById(R.id.etMarca);
        return formulario;
    }


    @Override
    public void click(View view) {
        String marca = etMarca.getText().toString();
        Snackbar.make(view, marca, Snackbar.LENGTH_LONG).show();
    }
}
