package co.progredi.aplicacion.persistencia.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lrey on 7/27/17.
 */

public class ConexionBD extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE = "";
    private static final int VERSION = 2;
    private static ConexionBD conexion;


    public ConexionBD(Context context) {
        super(context, NOMBRE_BASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Ejecutar las DDL
        String sql = "CREATE TABLE persona(" +
                "id_persona INTEGER primary key AUTOINCREMET," +
                "nombre text not null," +
                "apellido text not null," +
                "correo text," +
                "celular text ," +
                "fecha_nacimiento text" +
                ")";

        String sqlUsuario = "CREATE TABLE usuario(" +
                "id_usuario integer primary key AUTOINCREMENT," +
                "nombre_usuario text not null," +
                "clave text not null," +
                "id_persona integer not null" +
                ")";

        db.execSQL(sql);
        db.execSQL(sqlUsuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static ConexionBD getInstancia(Context contexto) {
        if (conexion == null) {
            conexion = new ConexionBD(contexto);
        }
        return conexion;
    }

    public SQLiteDatabase abrir() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        return db;
    }


}
