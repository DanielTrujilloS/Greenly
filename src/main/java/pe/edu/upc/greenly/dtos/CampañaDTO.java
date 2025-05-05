package pe.edu.upc.greenly.dtos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import pe.edu.upc.greenly.entities.Ong;

import java.time.LocalDate;

public class CampañaDTO {
    private int id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int ongId;
    private int ubicacion_CampañaId;


    public CampañaDTO(int id, String titulo, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, int ongId, int ubicacion_CampañaId) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ongId = ongId;
        this.ubicacion_CampañaId = ubicacion_CampañaId;
    }

    public CampañaDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getOngId() {
        return ongId;
    }

    public void setOngId(int ongId) {
        this.ongId = ongId;
    }

    public int getUbicacion_CampañaId() {
        return ubicacion_CampañaId;
    }

    public void setUbicacion_CampañaId(int ubicacion_CampañaId) {
        this.ubicacion_CampañaId = ubicacion_CampañaId;
    }

    @Override
    public String toString() {
        return "CampañaDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", ongId=" + ongId +
                ", ubicacion_CampañaId=" + ubicacion_CampañaId +
                '}';
    }
}
