package co.progredi.archivos.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.progredi.archivos.R;
import co.progredi.archivos.negocio.delegado.JsonDelegado;
import co.progredi.archivos.persistencia.entidad.DetalleFactura;
import co.progredi.archivos.persistencia.entidad.Factura;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private android.widget.EditText etNombreCliente;
    private android.widget.EditText etNumero;
    private android.widget.EditText etTotal;
    private android.widget.ListView lstDetalles;
    private android.widget.Button btnAgregarDetalles;
    private android.widget.Button btnAgregarFactura;
    private List<DetalleFactura> listaDetalles = new ArrayList<>();
    private ArrayAdapter<DetalleFactura> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaDetalles);
        this.btnAgregarFactura = (Button) findViewById(R.id.btnAgregarFactura);
        this.btnAgregarDetalles = (Button) findViewById(R.id.btnAgregarDetalles);
        this.lstDetalles = (ListView) findViewById(R.id.lstDetalles);
        this.etTotal = (EditText) findViewById(R.id.etTotal);
        this.etNumero = (EditText) findViewById(R.id.etNumero);
        this.etNombreCliente = (EditText) findViewById(R.id.etNombreCliente);
        btnAgregarDetalles.setOnClickListener(this);
        btnAgregarFactura.setOnClickListener(this);
        lstDetalles.setAdapter(adaptador);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAgregarDetalles:
                agregarDetalle();
                break;
            case R.id.btnAgregarFactura:
                agregarFactura();
                break;
        }
    }

    private void agregarFactura() {
        String cliente = etNombreCliente.getText().toString();
        String numero = etNumero.getText().toString();
        String valorTotal = etTotal.getText().toString();
        if (cliente.equals("") || !numero.matches("[\\d]*")) {
            return;
        }
        Factura factura = new Factura();
        factura.setNombreCliente(cliente);
        factura.setNumero(Integer.parseInt(numero));
        factura.setFecha(new Date());
        factura.setValorTotal(Float.parseFloat(valorTotal));
        factura.setListaDetalle(listaDetalles);
        JsonDelegado jsonDelegado = new JsonDelegado();
        List<Factura> listaFactura = jsonDelegado.leer();
        listaFactura.add(factura);
        jsonDelegado.escritura(listaFactura);
    }


    private void agregarDetalle() {
        Intent intent = new Intent(this, DetallesActivity.class);
        startActivityForResult(intent, 8);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != 8 || resultCode != 1) {
            return;
        }
        DetalleFactura detalleFactura = (DetalleFactura) data.getExtras().get("infoDetalle");
        listaDetalles.add(detalleFactura);
        adaptador.notifyDataSetChanged();
        actualizarEncabezado();
    }

    private void actualizarEncabezado() {
        Float valorTotalFactura = 0F;
        for (DetalleFactura detalle : listaDetalles) {
            valorTotalFactura += detalle.getValorTotal();
        }
        etTotal.setText(valorTotalFactura.toString());
    }
}
