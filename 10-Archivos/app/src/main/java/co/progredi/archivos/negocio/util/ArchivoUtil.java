package co.progredi.archivos.negocio.util;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Created by lrey on 8/4/17.
 */

public class ArchivoUtil {

    public static String fileName = Environment.getExternalStorageDirectory() + "/facturas.json";

    public static String leerArchivo() {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            String linea = lector.readLine();
            while (linea != null) {
                sb.append(linea);
                linea = lector.readLine();
            }
            lector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void escritura(String contenido) {
        try {
            PrintStream escribe = new PrintStream(fileName);
            escribe.println(contenido);
            escribe.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
