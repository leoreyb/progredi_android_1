package co.progredi.archivos.negocio.delegado;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import co.progredi.archivos.negocio.util.ArchivoUtil;
import co.progredi.archivos.persistencia.entidad.Factura;

/**
 * Created by lrey on 8/4/17.
 */

public class JsonDelegado {

    public void escritura(List<Factura> listaFactura) {
        String json = new Gson().toJson(listaFactura);
        ArchivoUtil.escritura(json);
    }

    public List<Factura> leer() {
        try {
            String json = ArchivoUtil.leerArchivo();
            Type tipo = new TypeToken<List<Factura>>() {
            }.getType();
            List<Factura> listaFactura = new Gson().fromJson(json, tipo);
            if (listaFactura == null) {
                listaFactura = new ArrayList<>();
            }
            return listaFactura;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


}
