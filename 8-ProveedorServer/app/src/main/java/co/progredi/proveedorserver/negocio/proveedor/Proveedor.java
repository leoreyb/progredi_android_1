package co.progredi.proveedorserver.negocio.proveedor;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import co.progredi.proveedorserver.persistencia.basedatos.Conexion;

/**
 * Created by lrey on 8/1/17.
 */

public class Proveedor extends ContentProvider {


    private SQLiteDatabase cnn;
    private static final String AUTORIDAD = "co.progredi.proveedorserver";
    private static final String NOMBRE_PROVEEDOR = "agenda";
    private static final String URL = "content:/" + AUTORIDAD + "/" + NOMBRE_PROVEEDOR;
    public static final Uri CONTENT_URI = Uri.parse(URL);

    private static UriMatcher rutas = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int TODOS = 1;
    private static final int REGISTRO = 2;
    private static final int MAYOR_EDAD = 3;

    static {
        rutas.addURI(AUTORIDAD, NOMBRE_PROVEEDOR + "/", TODOS);
        rutas.addURI(AUTORIDAD, NOMBRE_PROVEEDOR + "/#", REGISTRO);
        rutas.addURI(AUTORIDAD, NOMBRE_PROVEEDOR + "/mayoredad/", MAYOR_EDAD);
    }


    @Override
    public boolean onCreate() {
        Conexion conexion = new Conexion(getContext());
        cnn = conexion.abrir();
        return cnn.isOpen();
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String[] columnas = new String[]{"id", "nombre", "apellido", "correo"};
        switch (rutas.match(uri)) {
            case REGISTRO:
                Long id = Long.parseLong(uri.getPathSegments().get(1));
                return cnn.query("persona", columnas, "id = ?", new String[]{id.toString()}, null, null, sortOrder);
            case TODOS:
                return cnn.query("persona", columnas, null, null, null, null, sortOrder);
        }
        throw new SQLException("Error al consultar la información ");
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (rutas.match(uri)) {
            case REGISTRO:
                return "vnd.android.cursor.item/vnd." + AUTORIDAD;
            case TODOS:
            case MAYOR_EDAD:
                return "vnd.android.cursor.dir/vnd." + AUTORIDAD;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Long id = cnn.insert("persona", null, values);
        if (id > 0) {
            return ContentUris.withAppendedId(uri, id);
        }
        throw new SQLException("Error al insertar el registro");
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (rutas.match(uri)) {
            case REGISTRO:
                Long id = Long.parseLong(uri.getPathSegments().get(1));
                return cnn.delete("persona", "id=?", new String[]{id.toString()});

            case TODOS:
                return cnn.delete("persona", "1=1", null);
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (rutas.match(uri)) {
            case REGISTRO:
                Long id = Long.parseLong(uri.getPathSegments().get(1));
                return cnn.update("persona", values, "id=?", new String[]{id.toString()});

        }
        throw new SQLException("Debe seleccionar un solo registro para realizar la edición");
    }
}
