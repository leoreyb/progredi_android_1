package co.progredi.fragmentos.vista.fragmento;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import co.progredi.fragmentos.R;

/**
 * Created by lrey on 8/11/17.
 */

public class FragmentoRegistrarUsuario extends Fragment implements View.OnClickListener {

    private android.widget.EditText etNombre;
    private android.widget.Button btnGuardar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View formulario = inflater.inflate(R.layout.fragmento_registrar_usuario, null);
        this.btnGuardar = (Button) formulario.findViewById(R.id.btnGuardar);
        this.etNombre = (EditText) formulario.findViewById(R.id.etNombre);
        this.btnGuardar.setOnClickListener(this);
        return formulario;
    }

    @Override
    public void onClick(View v) {
        String nombre = etNombre.getText().toString();
        Snackbar.make(v, nombre, Snackbar.LENGTH_LONG).show();

    }
}
