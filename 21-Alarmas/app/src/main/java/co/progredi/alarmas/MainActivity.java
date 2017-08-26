package co.progredi.alarmas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import co.progredi.alarmas.negocio.utilidades.AlarmaUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlarmaUtil.crearAlarma(this, 0);
    }


}
