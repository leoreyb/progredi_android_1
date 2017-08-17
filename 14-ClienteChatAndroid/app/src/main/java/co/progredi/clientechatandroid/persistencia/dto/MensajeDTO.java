package co.progredi.clientechatandroid.persistencia.dto;

/**
 * Created by lrey on 8/10/17.
 */

public class MensajeDTO {

    private String usuario;
    private String mensaje;
    private String fecha;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
