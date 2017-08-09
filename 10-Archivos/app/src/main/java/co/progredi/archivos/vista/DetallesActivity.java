package co.progredi.archivos.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.progredi.archivos.R;
import co.progredi.archivos.persistencia.entidad.DetalleFactura;

public class DetallesActivity extends AppCompatActivity implements View.OnClickListener {

    private android.widget.EditText etDescripcion;
    private android.widget.EditText etCantidad;
    private android.widget.EditText etValorUnitario;
    private android.widget.Button btnGuararDetalle;
    private EditText etValorTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        this.etValorTotal = (EditText) findViewById(R.id.etValorTotal);
        this.btnGuararDetalle = (Button) findViewById(R.id.btnGuararDetalle);
        this.etValorUnitario = (EditText) findViewById(R.id.etValorUnitario);
        this.etCantidad = (EditText) findViewById(R.id.etCantidad);
        this.etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        this.etValorUnitario.setOnFocusChangeListener(evento);
        this.etCantidad.setOnFocusChangeListener(evento);
        this.btnGuararDetalle.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String descripcion = etDescripcion.getText().toString();
        String cantidad = etCantidad.getText().toString();
        String valorUnitario = etValorUnitario.getText().toString();
        String valorTotal = etValorTotal.getText().toString();
        if ("".equals(descripcion) || cantidad.equals("") || "".equals(valorUnitario)) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show();
            return;
        }
        DetalleFactura detalle = new DetalleFactura();
        detalle.setDescripcion(descripcion);
        detalle.setCantidad(Integer.parseInt(cantidad));
        detalle.setValorUnitario(Float.parseFloat(valorUnitario));
        detalle.setValorTotal(Float.parseFloat(valorTotal));
        Intent intent = new Intent();
        intent.putExtra("infoDetalle", detalle);
        setResult(1, intent);
        finish();
    }


    private View.OnFocusChangeListener evento = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            etValorTotal.setText("0");
            Log.d("focus", hasFocus + "");
            if (hasFocus) {
                return;
            }
            String valorUnitario = etValorUnitario.getText().toString();
            String cantidad = etCantidad.getText().toString();
            if (cantidad.equalsIgnoreCase("") || valorUnitario.equalsIgnoreCase("")) {
                return;
            }
            Float resultado = Float.parseFloat(valorUnitario) * Float.parseFloat(cantidad);
            etValorTotal.setText(resultado.toString());
        }
    };
}
