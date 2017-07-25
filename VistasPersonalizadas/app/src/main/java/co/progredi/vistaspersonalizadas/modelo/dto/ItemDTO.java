package co.progredi.vistaspersonalizadas.modelo.dto;

/**
 * Created by lrey on 7/24/17.
 */

public class ItemDTO {

    private Long id;
    private String descripcion;
    private String titulo;
    private String imagen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return titulo + " " + descripcion;
    }
}
