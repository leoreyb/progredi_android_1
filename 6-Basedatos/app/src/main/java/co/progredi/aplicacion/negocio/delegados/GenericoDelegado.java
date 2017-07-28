package co.progredi.aplicacion.negocio.delegados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import co.progredi.aplicacion.persistencia.basedatos.ConexionBD;
import co.progredi.aplicacion.vista.GenericoActivity;

/**
 * Created by lrey on 7/27/17.
 */

public abstract class GenericoDelegado {

    protected GenericoActivity contexto;

    public GenericoDelegado(GenericoActivity contexto) {
        this.contexto = contexto;
    }

    public SQLiteDatabase abrir() {
        return ConexionBD.getInstancia(contexto).abrir();
    }

    public void cerrar(SQLiteDatabase cnn) {
        if (cnn != null) {
            cnn.close();
        }
    }

    public void commit(SQLiteDatabase cnn) {
        cnn.setTransactionSuccessful();
    }

    public void rollBack(SQLiteDatabase cnn) {
        try {
            cnn.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
