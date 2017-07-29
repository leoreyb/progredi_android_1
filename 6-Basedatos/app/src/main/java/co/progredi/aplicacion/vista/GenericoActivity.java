package co.progredi.aplicacion.vista;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by lrey on 7/27/17.
 */

public abstract class GenericoActivity extends AppCompatActivity {

    public final static String[] PERMISOS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };


    @Override
    protected void onResume() {
        super.onResume();
        boolean validar = verificar();
        if (validar) {
            init();
        }
    }

    public abstract void init();

    private boolean verificar() {
        boolean validar = validarPermisos(this);
        if (!validar) {
            Intent intent = new Intent(this, SolicitarPermisoActivity.class);
            startActivity(intent);
        }
        return validar;
    }

    public void mensaje(View view, String mensaje) {
        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    public static boolean validarPermisos(Context context) {
        for (int i = 0; i < PERMISOS.length; i++) {
            if (ContextCompat.checkSelfPermission(context, PERMISOS[i]) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

}
