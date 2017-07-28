package co.progredi.aplicacion.persistencia.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import co.progredi.aplicacion.persistencia.entidades.Persona;

/**
 * Created by lrey on 7/27/17.
 */

public class PersonaDAO extends GenericoDAO<Persona> {

    public PersonaDAO(SQLiteDatabase cnn) {
        super(cnn, "persona");
    }

    @Override
    public void insertar(Persona entidad) {
        ContentValues valores = new ContentValues();
        valores.put("nombre", entidad.getNombre());
        valores.put("apellido", entidad.getApellido());
        valores.put("correo", entidad.getCorreo());
        valores.put("celular", entidad.getCelular());
        valores.put("fecha_nacimiento", getDate(entidad.getFechaNacimiento()));
        Long id = cnn.insert(tabla, null, valores);
        entidad.setIdPersona(id);
    }

    @Override
    public void eliminar(Long id) {
        cnn.delete(tabla, "id_persona=?", new String[]{id.toString()});
    }

    @Override
    public List<Persona> consultar() {
        String[] columnas = new String[]{"id_persona", "nombre", "apellido", "correo", "celular"};
        // String[] parametros = new String[]{"Oscar", "Baquero"};
        // cnn.query("persona", columnas, "nombre=?  and apellido =? ", parametros, null, null, "nombre");
        // select * from persona order by nombre
        Cursor cursor = cnn.query(tabla, columnas, null, null, null, null, "nombre");
        List<Persona> listaPersonas = new ArrayList<>();
        //if(cursor.getCount()==0){
        if (!cursor.moveToFirst()) {
            return listaPersonas;
        }
        do {
            listaPersonas.add(getPersona(cursor));
        } while (cursor.moveToNext());
        return listaPersonas;
    }

    @Override
    public void actualizar(Persona entidad) {
        ContentValues valores = new ContentValues();
        valores.put("nombre", entidad.getNombre());
        valores.put("apellido", entidad.getApellido());
        valores.put("correo", entidad.getCorreo());
        valores.put("celular", entidad.getCelular());
        valores.put("fecha_nacimiento", getDate(entidad.getFechaNacimiento()));
        cnn.update(tabla, valores, "id_persona = ? ", new String[]{entidad.getIdPersona().toString()});
    }

    public List<Persona> consultarSql() {
        Cursor cursor = cnn.rawQuery("select * from persona order by nombre ", null);
        List<Persona> listaPersonas = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return listaPersonas;
        }
        do {
            listaPersonas.add(getPersona(cursor));
        } while (cursor.moveToNext());
        return listaPersonas;
    }


    public static Persona getPersona(Cursor cursor) {
        Persona persona = new Persona();
        persona.setIdPersona(getLong(cursor, "id_persona"));
        persona.setNombre(getString(cursor, "nombre"));
        persona.setApellido(getString(cursor, "apellido"));
        persona.setCorreo(getString(cursor, "correo"));
        persona.setCelular(getString(cursor, "nombre"));
        persona.setFechaNacimiento(getDate(cursor, "fecha_nacimiento"));
        return persona;
    }


}
