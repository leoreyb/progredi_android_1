/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorchat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author lrey
 */
public class HiloCliente extends Thread {

    private Socket cliente;

    public HiloCliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        while (true) {
            try {
                BufferedReader lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                while (true) {
                    String linea = lector.readLine();
                    for (Socket clienteGuardado : Servidor.listaClientes) {
                        PrintStream out = new PrintStream(clienteGuardado.getOutputStream());
                        out.println(linea);
                        out.flush();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

}
