package co.progredi.basedatos.persistencia.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lrey on 7/26/17.
 */

public class Conexion extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DATOS = "capacitacion";
    private static final int VERSION = 1;

    public Conexion(Context context) {
        super(context, NOMBRE_BASE_DATOS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE usuario ( " +
                "id INTEGER primary key AUTOINCREMENT, " +
                "nombre text not null, " +
                "apellido text not null, " +
                "correo text not null, " +
                "clave text not null ) ";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table
        //alter table
        //Salvar la información
        String sql = "DROP TABLE usuario"; //No es la forma más recomendada
        db.execSQL(sql);
        onCreate(db);
    }

    protected SQLiteDatabase abrir() {
        return getWritableDatabase();
    }

}
