package co.progredi.aplicacion.negocio.delegados;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import co.progredi.aplicacion.persistencia.dao.PersonaDAO;
import co.progredi.aplicacion.persistencia.entidades.Persona;
import co.progredi.aplicacion.vista.MainActivity;

/**
 * Created by lrey on 7/27/17.
 */

public class PersonaDelegado extends GenericoDelegado {

    private MainActivity mainActivity;

    public PersonaDelegado(MainActivity contexto) {
        super(contexto);
    }

    public void insertar(Persona persona) {
        SQLiteDatabase cnn = null;
        PersonaDAO personaDAO;
        try {
            cnn = abrir();
            personaDAO = new PersonaDAO(cnn);
            personaDAO.insertar(persona);
            List<Persona> lista = personaDAO.consultar();
            Log.d("Consultar", "" + lista);
            commit(cnn);
        } catch (Exception e) {
            e.printStackTrace();
            rollBack(cnn);
        } finally {
            cerrar(cnn);
        }
    }

}
