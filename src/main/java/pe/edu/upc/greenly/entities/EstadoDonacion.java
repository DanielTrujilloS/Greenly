package pe.edu.upc.greenly.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "estadoDonacion")
public class EstadoDonacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "donacion_id")
    private Donacion donacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Donacion getDonacion() {
        return donacion;
    }

    public void setDonacion(Donacion donacion) {
        this.donacion = donacion;
    }
}
