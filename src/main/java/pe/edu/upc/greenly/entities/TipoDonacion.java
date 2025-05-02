package pe.edu.upc.greenly.entities;


import jakarta.persistence.*;
import jdk.jfr.Enabled;

import java.time.LocalDate;

@Entity
@Table(name = "tipoDonacion")
public class TipoDonacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String estado;
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "donacion_id")
    private Donacion donacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Donacion getDonacion() {
        return donacion;
    }

    public void setDonacion(Donacion donacion) {
        this.donacion = donacion;
    }
}
