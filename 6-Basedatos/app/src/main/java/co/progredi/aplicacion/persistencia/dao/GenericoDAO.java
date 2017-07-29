package co.progredi.aplicacion.persistencia.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lrey on 7/27/17.
 */

public abstract class GenericoDAO<T> {

    protected SQLiteDatabase cnn;
    protected String tabla;


    public GenericoDAO(SQLiteDatabase cnn, String tabla) {
        this.cnn = cnn;
        this.tabla = tabla;
    }

    public abstract void insertar(T entidad);

    public abstract void eliminar(Long id);

    public abstract List<T> consultar();

    public abstract void actualizar(T entidad);


    public static Long getLong(Cursor cursor, String columna) {
        int indice = cursor.getColumnIndex(columna);
        if (indice == -1) {
            return null;
        }
        return cursor.getLong(cursor.getColumnIndex(columna));
    }

    public static String getString(Cursor cursor, String columna) {
        int indice = cursor.getColumnIndex(columna);
        if (indice == -1) {
            return null;
        }
        return cursor.getString(cursor.getColumnIndex(columna));
    }

    public static Date getDate(Cursor cursor, String columna) {
        try {
            String fecha = getString(cursor, columna);
            if (fecha == null) {
                return null;
            }
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return formato.parse(fecha);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getDate(Date fecha) {
        if (fecha == null) {
            return null;
        }
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return formato.format(fecha);
    }

}
