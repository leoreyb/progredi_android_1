package co.progredi.vistaspersonalizadas.componentes;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import co.progredi.vistaspersonalizadas.R;

/**
 * Created by lrey on 7/24/17.
 */

public class InicioSesionView extends LinearLayout implements View.OnClickListener {

    private View componente;
    private Button btnIniciar;
    private EditText etUsuario;
    private EditText etClave;


    public InicioSesionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        componente = LayoutInflater.from(context).inflate(R.layout.item_inicio_sesion, this);
        etUsuario = (EditText) componente.findViewById(R.id.etUsuario);
        etClave = (EditText) componente.findViewById(R.id.etClave);
        btnIniciar = (Button) componente.findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIniciar:
                validar();
                break;
        }
    }

    private void validar() {
        try {
            validarVacio(etUsuario.getText().toString(), "usuario");
            validarVacio(etClave.getText().toString(), "clave");
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void validarVacio(String texto, String campo) throws Exception {
        if (texto.trim().equalsIgnoreCase("")) {
            throw new Exception("Debe diligenciar el campo " + campo);
        }
    }
}
