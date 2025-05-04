package pe.edu.upc.greenly.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPosts;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contenido;

    @Lob
    private byte[] imagen;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_publicacion")
    private Date fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "campaña_id_campaña")
    private Campaña campaña;

    @ManyToOne
    @JoinColumn(name = "donante_id_donante")
    private Donante donante;

    public Post() {
    }

    public Post(int idPosts, String contenido, byte[] imagen, Date fechaPublicacion, Campaña campaña, Donante donante) {
        this.idPosts = idPosts;
        this.contenido = contenido;
        this.imagen = imagen;
        this.fechaPublicacion = fechaPublicacion;
        this.campaña = campaña;
        this.donante = donante;
    }

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

    public Campaña getCampaña() {
        return campaña;
    }

    public void setCampaña(Campaña campaña) {
        this.campaña = campaña;
    }

    public Donante getDonante() {
        return donante;
    }

    public void setDonante(Donante donante) {
        this.donante = donante;
    }
}
