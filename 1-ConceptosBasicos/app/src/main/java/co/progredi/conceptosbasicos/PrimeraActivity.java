package co.progredi.conceptosbasicos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PrimeraActivity extends AppCompatActivity {

    private android.widget.Button btnSegunda;
    private android.widget.TextView tvResultado;

    private final int PETICION_SEGUNDA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera);
        this.tvResultado = (TextView) findViewById(R.id.tvResultado);
        this.btnSegunda = (Button) findViewById(R.id.btnSegunda);
        this.btnSegunda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrimeraActivity.this, SegundaActivity.class);
                startActivityForResult(intent, PETICION_SEGUNDA);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        tvResultado.setText(data.getExtras().getString("numero"));
    }
}
