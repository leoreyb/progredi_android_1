package co.progredi.archivosxml.negocio.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

import co.progredi.archivosxml.persistencia.entidad.Persona;

/**
 * SAX
 * Created by lrey on 8/8/17.
 */

public class ManejadorSAX extends DefaultHandler {

    private List<Persona> listaPersonas;
    private Persona persona;
    private String dato;

    public ManejadorSAX(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("persona")) {
            persona = new Persona();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        dato = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (localName) {
            case "nombre":
                persona.setNombre(dato);
                break;
            case "apellido":
                persona.setApellido(dato);
                break;
            case "correo":
                persona.setCorreo(dato);
                break;
            case "documento":
                persona.setDocumento(dato);
                break;
            case "persona":
                listaPersonas.add(persona);
                persona = null;
                dato = "";
                break;
        }
    }

    @Override
    public void endDocument() throws SAXException {

    }
}
