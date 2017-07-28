package co.progredi.aplicacion.persistencia.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import co.progredi.aplicacion.persistencia.entidades.Persona;
import co.progredi.aplicacion.persistencia.entidades.Usuario;

/**
 * Created by lrey on 7/27/17.
 */

public class UsuarioDAO extends GenericoDAO<Usuario> {


    public UsuarioDAO(SQLiteDatabase cnn) {
        super(cnn, "usuario");
    }

    @Override
    public void insertar(Usuario entidad) {

    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public List<Usuario> consultar() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        Cursor cursor = cnn.rawQuery(
                "select usuario.* from usuario inner joinn persona on usuario.id_persona = persona.id_persona ", null);
        if (!cursor.moveToFirst()) {
            return listaUsuarios;
        }
        do {
            Usuario usuario = getUsuario(cursor);
            //PersonaDAO personaDAO=new PersonaDAO(cnn);
            //Persona persona =personaDAO.getPersona(cursor);
            Persona persona = PersonaDAO.getPersona(cursor);
            usuario.setPersona(persona);
            listaUsuarios.add(usuario);
        } while (cursor.moveToNext());
        return listaUsuarios;
    }

    @Override
    public void actualizar(Usuario entidad) {

    }

    public static Usuario getUsuario(Cursor cursor) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(getLong(cursor, "id_usuario"));
        usuario.setNombreUsuario(getString(cursor, "nombre_usuario"));
        usuario.setClave(getString(cursor, "clave"));
        usuario.setPersona(new Persona(getLong(cursor, "id_persona")));
        return usuario;
    }
}
