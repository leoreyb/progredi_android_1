package co.progredi.webservicessoap;

import android.app.NotificationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnCrearNotificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnCrearNotificacion = (Button) findViewById(R.id.btnCrearNotificacion);
        this.btnCrearNotificacion.setOnClickListener(this);
        consumirServicio();
    }

    public void consumirServicio() {

        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //Retrofit - REST
                    String nameSpace = "http://soap.negocio.androidweb.progredi.edu.co/";
                    String metodo = "registrar";
                    SoapObject soapObject = new SoapObject(nameSpace, metodo);

                    SoapObject soapDispotivo = new SoapObject();
                    soapDispotivo.addProperty("idDispositivo", "45");
                    soapDispotivo.addProperty("marca", "Lion Apple");
                    soapDispotivo.addProperty("color", "Negro");
                    soapDispotivo.addProperty("referencia", "123");

                    soapObject.addProperty("dispositivo", soapDispotivo);
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
                    //envelope.dotNet=true;
                    envelope.setOutputSoapObject(soapObject);
                    HttpTransportSE transportador = new HttpTransportSE("http://192.168.0.24:8080/AndroidWEB/ServicioSOAP?WSDL");
                    transportador.call(metodo, envelope);
                    Object response = envelope.getResponse();
                    Log.d("Webservices SOAP ", response + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        hilo.start();
    }


    @Override
    public void onClick(View v) {

    }
}
