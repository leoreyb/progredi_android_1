package co.progredi.proveedorserver.persistencia.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lrey on 8/1/17.
 */

public class Conexion extends SQLiteOpenHelper {

    public Conexion(Context context) {
        super(context, "proveedor", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase cnn) {
        String sql = "CREATE TABLE persona(" +
                "id integer primary key AUTOINCREMENT," +
                "nombre text," +
                "apellido text," +
                "correo text" +
                ")";
        cnn.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase abrir() {
        return getWritableDatabase();
    }
}
