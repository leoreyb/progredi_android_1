package co.progredi.archivosxml.negocio.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import co.progredi.archivosxml.persistencia.entidad.Persona;

/**
 * DOM
 * Created by lrey on 8/8/17.
 */

public class ArchivoDOM {


    public List<Persona> leer() {
        List<Persona> listaPersonas = new ArrayList<>();
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder interpretador = fabrica.newDocumentBuilder();
            Document documento = interpretador.parse(new File(ArchivoSAX.RUTA_ARCHIVO));
            NodeList listaEtiquetas = documento.getElementsByTagName("persona");
            for (int i = 0; i < listaEtiquetas.getLength(); i++) {
                Persona persona = new Persona();
                Node nodoPersona = listaEtiquetas.item(i);
                NodeList caracteristicas = nodoPersona.getChildNodes();
                for (int j = 0; j < caracteristicas.getLength(); j++) {
                    Node elemento = caracteristicas.item(j);
                    switch (elemento.getNodeName()) {
                        case "nombre":
                            persona.setNombre(elemento.getTextContent());
                            break;
                        case "apellido":
                            persona.setApellido(elemento.getTextContent());
                            break;
                        case "correo":
                            persona.setCorreo(elemento.getTextContent());
                            break;
                        case "documento":
                            persona.setDocumento(elemento.getTextContent());
                            break;
                    }
                }
                listaPersonas.add(persona);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaPersonas;
    }


    public void escribir(List<Persona> listaPersonas) {
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder interpretador = fabrica.newDocumentBuilder();
            Document documento = interpretador.newDocument();
            Element elementoPrincipal = documento.createElement("listapersonas");
            for (Persona persona : listaPersonas) {
                Element elementoPersona = documento.createElement("persona");

                Element elementoNombre = documento.createElement("nombre");
                elementoNombre.setTextContent(persona.getNombre());
                elementoPersona.appendChild(elementoNombre);

                Element elementoApellido = documento.createElement("apellido");
                elementoApellido.setTextContent(persona.getApellido());
                elementoPersona.appendChild(elementoApellido);

                Element elementoCorreo = documento.createElement("correo");
                elementoCorreo.setTextContent(persona.getCorreo());
                elementoPersona.appendChild(elementoCorreo);

                Element elementoDocumento = documento.createElement("documento");
                elementoDocumento.setTextContent(persona.getDocumento());
                elementoPersona.appendChild(elementoDocumento);

                elementoPrincipal.appendChild(elementoPersona);
            }
            documento.appendChild(elementoPrincipal);

            TransformerFactory fabricaTransformador = TransformerFactory.newInstance();
            DOMSource origen = new DOMSource(documento);
            StreamResult archivoEscritura = new StreamResult(new FileOutputStream(ArchivoSAX.RUTA_ARCHIVO));
            fabricaTransformador.newTransformer().transform(origen, archivoEscritura);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
