package co.progredi.archivos.persistencia.entidad;

import java.util.Date;
import java.util.List;

/**
 *
 * Created by lrey on 8/4/17.
 */

public class Factura {

    private String nombreCliente;
    private Integer numero;
    private Float valorTotal;
    private Date fecha;
    private List<DetalleFactura> listaDetalle;

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<DetalleFactura> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<DetalleFactura> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }
}
