package co.progredi.webservicessoap;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by lrey on 8/24/17.
 */

public class RecibeMensaje extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String mensaje = "";
        try {
            mensaje = remoteMessage.getData().get("message");
        } catch (Exception e) {
            e.printStackTrace();
        }
        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this);
        notificacion.setSmallIcon(R.mipmap.ic_launcher);
        notificacion.setContentText(mensaje + "");
        notificacion.setContentTitle("Webservices SOAP");
        notificacion.setPriority(NotificationCompat.PRIORITY_MAX);
        notificacion.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        Uri sonidoDefecto = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notificacion.setSound(sonidoDefecto);
        notificacion.setAutoCancel(false);
        PendingIntent intent = PendingIntent.getActivity(this, 1, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        notificacion.setContentIntent(intent);
        NotificationManager manejador = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manejador.notify(1, notificacion.build());
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Mi token: ", refreshedToken);
    }

}
