package co.progredi.clienteweb.persistencia.entidad;

/**
 * Created by lrey on 8/15/17.
 */

public class Dispositivo {

    private long idDispositivo;
    private String marca;
    private String referencia;
    private String color;

    public long getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
