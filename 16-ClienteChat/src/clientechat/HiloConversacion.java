/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientechat;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;
import javax.swing.JTextArea;

/**
 *
 * @author lrey
 */
public class HiloConversacion extends Thread {

    private Socket cliente;
    private JTextArea txtConversacion;

    public HiloConversacion(Socket cliente, JTextArea txtConversacion) {
        this.cliente = cliente;
        this.txtConversacion = txtConversacion;
    }

    @Override
    public void run() {
        try {
            BufferedReader lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            while (true) {
                String linea = lector.readLine();
                MensajeDTO mensaje = new Gson().fromJson(linea, MensajeDTO.class);
                txtConversacion.append("Usuario: " + mensaje.getUsuario() + " fecha: "
                        + new Date(Long.parseLong(mensaje.getFecha())) + " " + mensaje.getMensaje() + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
