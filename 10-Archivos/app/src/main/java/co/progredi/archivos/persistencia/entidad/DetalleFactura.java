package co.progredi.archivos.persistencia.entidad;

import java.io.Serializable;

/**
 * Created by lrey on 8/4/17.
 */

public class DetalleFactura implements Serializable {

    private Long idDetalle;
    private String descripcion;
    private float valorUnitario;
    private float valorTotal;
    private int cantidad;

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return descripcion + " - " + valorTotal;
    }
}
