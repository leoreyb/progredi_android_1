package co.progredi.conceptosbasicos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText etFrase;
    private Button btnInvertir;
    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnInvertir = (Button) findViewById(R.id.btnInvertir);
        this.etFrase = (EditText) findViewById(R.id.etFrase);
    }

    public void invertir(View v) {
        String frase = etFrase.getText().toString();
        frase = new StringBuilder(frase).reverse().toString();
        contador++;
        Toast.makeText(MainActivity.this, "" + frase + " contador:" + contador, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("contador1", contador);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        contador = savedInstanceState.getInt("contador1");
        super.onRestoreInstanceState(savedInstanceState);
    }
}
