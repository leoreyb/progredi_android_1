package co.progredi.archivosxml.negocio.util;

import android.os.Environment;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import co.progredi.archivosxml.persistencia.entidad.Persona;

/**
 * Created by lrey on 8/8/17.
 */

public class ArchivoSAX {

    public static final String RUTA_ARCHIVO = Environment.getExternalStorageDirectory() + "/contactos.xml";

    public List<Persona> leer() {
        List<Persona> listaPersonas = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            return listaPersonas;
        }
        try {
            SAXParserFactory fabrica = SAXParserFactory.newInstance();
            SAXParser interpretador = fabrica.newSAXParser();
            interpretador.parse(archivo, new ManejadorSAX(listaPersonas));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaPersonas;
    }

    public void escritura(List<Persona> listaPersonas) {
        try {
            XmlSerializer serializador = Xml.newSerializer();
            serializador.setOutput(new FileOutputStream(RUTA_ARCHIVO), "UTF-8");
            serializador.startDocument("UTF-8", true);
            serializador.startTag("", "listapersonas");
            for (Persona persona : listaPersonas) {
                serializador.startTag("", "persona");

                serializador.startTag("", "nombre");
                serializador.text(persona.getNombre());
                serializador.endTag("", "nombre");

                serializador.startTag("", "apellido");
                serializador.text(persona.getApellido());
                serializador.endTag("", "apellido");

                serializador.startTag("", "documento");
                serializador.text(persona.getDocumento());
                serializador.endTag("", "documento");

                serializador.startTag("", "correo");
                serializador.text(persona.getCorreo());
                serializador.endTag("", "correo");

                serializador.endTag("", "persona");
            }
            serializador.endTag("", "listapersonas");
            serializador.endDocument();
            serializador.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
