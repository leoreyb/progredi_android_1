package co.progredi.alarmas.negocio.servicio;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import co.progredi.alarmas.MainActivity;
import co.progredi.alarmas.R;
import co.progredi.alarmas.negocio.utilidades.AlarmaUtil;

/**
 * Ejecuta el servicio de la Alarma
 * Created by lrey on 8/25/17.
 */

public class ServicioAlarma extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                invocarWebServices();
            }
        });
        hilo.start();
        return super.onStartCommand(intent, flags, startId);
    }

    private void invocarWebServices() {
        try {
            String nameSpace = "http://soap.negocio.androidweb.progredi.edu.co/";
            String metodo = "sumar";
            SoapObject soapObject = new SoapObject(nameSpace, metodo);
            soapObject.addProperty("numero1", 5);
            soapObject.addProperty("numero2", 3);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
            envelope.setOutputSoapObject(soapObject);
            HttpTransportSE transport = new HttpTransportSE("http://192.168.0.24:8080/AndroidWEB/ServicioSOAP?WSDL");
            transport.call(metodo, envelope);
            Object response = envelope.getResponse();
            mostrarNotificacion(response);
            AlarmaUtil.crearAlarma(this, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarNotificacion(Object response) {
        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this);
        notificacion.setSmallIcon(R.mipmap.ic_launcher);
        notificacion.setContentText(response + "");
        notificacion.setContentTitle("Alarma SOAP");
        notificacion.setPriority(NotificationCompat.PRIORITY_MAX);
        notificacion.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        Uri sonidoDefecto = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notificacion.setSound(sonidoDefecto);
        notificacion.setAutoCancel(true);
        notificacion.setOngoing(true);

        PendingIntent intent = PendingIntent.getActivity(this, 1, new Intent(this, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        notificacion.setContentIntent(intent);
        NotificationManager manejador = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manejador.notify(1, notificacion.build());
    }
}
