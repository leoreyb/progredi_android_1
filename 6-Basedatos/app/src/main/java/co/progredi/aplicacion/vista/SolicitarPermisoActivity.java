package co.progredi.aplicacion.vista;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import co.progredi.aplicacion.R;

public class SolicitarPermisoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_permiso);
        solicitarPermisos();
    }

    private void solicitarPermisos() {
        ActivityCompat.requestPermissions(this, GenericoActivity.PERMISOS, 8888);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("respuesta", "" + requestCode
                + " PERMISOS" + permissions + " grand " +
                grantResults + " nuestroPermisos" + GenericoActivity.PERMISOS);
        boolean permisos = GenericoActivity.validarPermisos(this);
        
    }

}
