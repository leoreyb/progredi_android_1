package co.progredi.basedatos.persistencia.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import co.progredi.basedatos.persistencia.basedatos.Conexion;
import co.progredi.basedatos.persistencia.vo.Usuario;

/**
 * Created by lrey on 7/26/17.
 */

public class UsuarioDAO extends Conexion {

    public UsuarioDAO(Context context) {
        super(context);
    }

    public void insertar(Usuario usuario) {
        SQLiteDatabase cnn = null;
        try {
            cnn = abrir();
            ContentValues valores = new ContentValues();
            valores.put("nombre", usuario.getNombre());
            valores.put("apellido", usuario.getApellido());
            valores.put("correo", usuario.getCorreo());
            valores.put("clave", usuario.getClave());
            Long id = cnn.insert("usuario", null, valores);
            usuario.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public List<Usuario> consultar() {
        SQLiteDatabase cnn = null;
        List<Usuario> listaUsuarios = new ArrayList<>();
        String[] columnas = new String[]{"id", "nombre", "apellido", "correo", "clave"};
        try {
            cnn = abrir();
            Cursor resultSet = cnn.query("usuario", columnas, null, null, null, null, "apellido");
            if (!resultSet.moveToFirst()) {
                return listaUsuarios;
            }
            do {
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getLong(resultSet.getColumnIndex("id")));
                usuario.setNombre(resultSet.getString(resultSet.getColumnIndex("nombre")));
                usuario.setApellido(resultSet.getString(resultSet.getColumnIndex("apellido")));
                usuario.setCorreo(resultSet.getString(resultSet.getColumnIndex("correo")));
                usuario.setClave(resultSet.getString(resultSet.getColumnIndex("clave")));
                listaUsuarios.add(usuario);
            } while (resultSet.moveToNext());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cnn != null) {
                cnn.close();
            }
        }

        return listaUsuarios;

    }

}
