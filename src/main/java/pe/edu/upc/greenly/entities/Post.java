package pe.edu.upc.greenly.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "Post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPosts;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contenido;

    @Lob
    private byte[] imagen;

    @JsonIgnore
    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<Comentario> comentarios = new ArrayList<>();

    //@Temporal(TemporalType.DATE)
    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "campaña_id_campaña")
    private Campaña campaña;

    /*@ManyToOne
    @JoinColumn(name = "donante_id_donante")
    private Donante donante;*/

    public Post() {
    }

    public Post(Long idPosts, String contenido, byte[] imagen, LocalDate fechaPublicacion, Campaña campaña) {
        this.idPosts = idPosts;
        this.contenido = contenido;
        this.imagen = imagen;
        this.fechaPublicacion = fechaPublicacion;
        this.campaña = campaña;
    }

    public Long getIdPosts() {
        return idPosts;
    }

    public void setIdPosts(Long idPosts) {
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

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Campaña getCampaña() {
        return campaña;
    }

    public void setCampaña(Campaña campaña) {
        this.campaña = campaña;
    }

    @Override
    public String toString() {
        return "Post{" +
                "idPosts=" + idPosts +
                ", contenido='" + contenido + '\'' +
                ", imagen=" + Arrays.toString(imagen) +
                ", fechaPublicacion=" + fechaPublicacion +
                ", campaña=" + campaña +
                '}';
    }
}
