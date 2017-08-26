package co.progredi.alarmas.negocio.utilidades;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.Date;

import co.progredi.alarmas.negocio.servicio.ServicioAlarma;

/**
 * Created by lrey on 8/25/17.
 */

public class AlarmaUtil {

    public static void crearAlarma(Context contexto, int dia) {

        PendingIntent servicio = PendingIntent.getService(
                contexto,
                1,
                new Intent(contexto, ServicioAlarma.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        long tiempoNotificacion = obtenerFecha(dia);
        AlarmManager manejador = (AlarmManager) contexto.getSystemService(Context.ALARM_SERVICE);
        manejador.set(AlarmManager.RTC_WAKEUP, tiempoNotificacion, servicio);
    }

    private static long obtenerFecha(int dia) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, dia);
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 42);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

}
