package co.progredi.aplicacion.vista;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import co.progredi.aplicacion.R;
import co.progredi.aplicacion.persistencia.basedatos.ConexionBD;

public class SplashActivity extends GenericoActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    @Override
    public void init() {
        exportarBase();
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void exportarBase() {
        try {
            File origen = new File(ConexionBD.RUTA_BASE);
            File destino = new File(Environment.getExternalStorageDirectory() + "/base.sqlite");
            FileOutputStream out = new FileOutputStream(destino);
            FileInputStream in = new FileInputStream(origen);
            byte[] data = new byte[in.available()];
            int lector;
            while ((lector = in.read(data)) > 0) {
                out.write(data, 0, lector);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
