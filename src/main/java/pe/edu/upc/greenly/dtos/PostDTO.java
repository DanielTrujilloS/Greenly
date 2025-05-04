package pe.edu.upc.greenly.dtos;

import java.util.Date;

public class PostDTO {
    private int idPosts;
    private String contenido;
    private byte[] imagen;
    private Date fechaPublicacion;
    private int campañaId;
    private int donanteId;

    public int getIdPosts() {
        return idPosts;
    }

    public void setIdPosts(int idPosts) {
        this.idPosts = idPosts;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getCampañaId() {
        return campañaId;
    }

    public void setCampañaId(int campañaId) {
        this.campañaId = campañaId;
    }

    public int getDonanteId() {
        return donanteId;
    }

    public void setDonanteId(int donanteId) {
        this.donanteId = donanteId;
    }
}
