package co.progredi.ciclovida;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private android.widget.Button btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnCerrar = (Button) findViewById(R.id.btnCerrar);
        this.btnCerrar.setOnClickListener(this);
        Log.d("Ciclo", "oncreate");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Ciclo", "Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Ciclo", "Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Ciclo", "Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Ciclo", "Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Ciclo", "Destroy");
    }

    @Override
    public void onBackPressed() {
        alertaCerrarActividad();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    private void alertaCerrarActividad() {

        View contenido = LayoutInflater.from(this).inflate(R.layout.item_alerta, null);
        final EditText etTexto = (EditText) contenido.findViewById(R.id.etTexto);
        RatingBar ratingBar = (RatingBar) contenido.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                etTexto.setText("" + rating);
            }
        });
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        alerta.setTitle("Confirmación");
        alerta.setView(contenido);
        alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alerta.setNegativeButton("No", null);
        alerta.show();


    }
}
