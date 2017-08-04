package co.progredi.proveedorserver.persistencia.dao;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lrey on 8/1/17.
 */

public class PersonaDAO {

    private SQLiteDatabase cnn;

    public PersonaDAO(SQLiteDatabase cnn) {
        this.cnn = cnn;
    }

}
