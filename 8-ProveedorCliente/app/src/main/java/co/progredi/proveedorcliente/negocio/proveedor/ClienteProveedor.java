package co.progredi.proveedorcliente.negocio.proveedor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import co.progredi.proveedorcliente.persistencia.entidad.Persona;

/**
 * Created by lrey on 8/1/17.
 */

public class ClienteProveedor {

    private static final Uri CONTENT_URI = Uri.parse("content://co.progredi.proveedorserver/agenda");

    private Context contexto;

    public ClienteProveedor(Context contexto) {
        this.contexto = contexto;
    }

    public void insertar(Persona persona) throws Exception {
        ContentValues valores = new ContentValues();
        valores.put("nombre", persona.getNombre());
        valores.put("apellido", persona.getApellido());
        valores.put("correo", persona.getCorreo());
        Uri insertado = contexto.getContentResolver().insert(CONTENT_URI, valores);
        Long id = Long.parseLong(insertado.getPathSegments().get(1));
        persona.setId(id);
    }

    public List<Persona> consultar() throws Exception {
        List<Persona> listaPersonas = new ArrayList<>();
        Cursor cursor = contexto.getContentResolver().query(CONTENT_URI, null, null, null, "nombre");
        if (!cursor.moveToFirst()) {
            throw new Exception("Error al consultar los registros");
        }
        do {
            Persona persona = new Persona();
            persona.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
            persona.setApellido(cursor.getString(cursor.getColumnIndex("apellido")));
            persona.setCorreo(cursor.getString(cursor.getColumnIndex("correo")));
            persona.setId(cursor.getLong(cursor.getColumnIndex("id")));
            listaPersonas.add(persona);
        } while (cursor.moveToNext());
        return listaPersonas;
    }


}
