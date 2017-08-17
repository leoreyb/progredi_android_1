package co.progredi.clienteweb.persistencia.dto;

/**
 * Created by lrey on 8/15/17.
 */

public class RespuestaDTO<T> {

    private int codigo;
    private String mensaje;
    private T datos;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getDatos() {
        return datos;
    }

    public void setDatos(T datos) {
        this.datos = datos;
    }
}
