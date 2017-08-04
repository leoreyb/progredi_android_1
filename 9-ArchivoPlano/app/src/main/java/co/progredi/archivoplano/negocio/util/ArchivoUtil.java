package co.progredi.archivoplano.negocio.util;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import co.progredi.archivoplano.persistencia.entidad.Producto;

/**
 * Created by lrey on 8/3/17.
 */

public class ArchivoUtil {

    private static String ruta = Environment.getExternalStorageDirectory() + "/productos.csv";

    public static void escritura(List<Producto> listaProductos) {
        try {
            PrintStream salida = new PrintStream(ruta);
            for (Producto producto : listaProductos) {
                salida.print(producto.getId() + ";" + producto.getNombre() + ";" +
                        producto.getDescripcion() + ";" + producto.getValor() + "\n");
            }
            salida.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List<Producto> leer() {
        List<Producto> listaProductos = new ArrayList<>();
        try {
            BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(ruta)));
            String linea = lector.readLine();
            while (linea != null) {
                String[] info = linea.split(";");
                Producto producto = new Producto();
                producto.setId(Long.valueOf(info[0]));
                producto.setNombre(info[1]);
                producto.setDescripcion(info[2]);
                producto.setValor(Float.valueOf(info[3]));
                listaProductos.add(producto);
                linea = lector.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProductos;
    }


}
