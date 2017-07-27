package co.progredi.conceptosbasicos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SegundaActivity extends AppCompatActivity {

    private android.widget.EditText etNumero;
    private android.widget.Button btnDevolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        this.btnDevolver = (Button) findViewById(R.id.btnDevolver);
        this.etNumero = (EditText) findViewById(R.id.etNumero);

        this.btnDevolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = etNumero.getText().toString();
                Intent parametros = new Intent();
                parametros.putExtra("numero", numero);
                setResult(1, parametros);
                finish();
            }
        });
    }
}
