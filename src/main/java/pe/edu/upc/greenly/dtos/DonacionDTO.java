package pe.edu.upc.greenly.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

public class DonacionDTO {

    private int id;
    private String name;
    private String descripcion;
    private Integer montoDonado;
    private String metodoEntrega;
    private LocalDate fechaDonacion;
    private Long idDonante;
    private Long idCampaña;
    private Long idTipoDonacion;
    private Long idEstadoDonacion;


    public DonacionDTO(int id, String name, String descripcion, Integer montoDonado, String metodoEntrega, LocalDate fechaDonacion, Long idDonante, Long idCampaña, Long idTipoDonacion, Long idEstadoDonacion) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.montoDonado = montoDonado;
        this.metodoEntrega = metodoEntrega;
        this.fechaDonacion = fechaDonacion;
        this.idDonante = idDonante;
        this.idCampaña = idCampaña;
        this.idTipoDonacion = idTipoDonacion;
        this.idEstadoDonacion = idEstadoDonacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getMontoDonado() {
        return montoDonado;
    }

    public void setMontoDonado(Integer montoDonado) {
        this.montoDonado = montoDonado;
    }

    public String getMetodoEntrega() {
        return metodoEntrega;
    }

    public void setMetodoEntrega(String metodoEntrega) {
        this.metodoEntrega = metodoEntrega;
    }

    public LocalDate getFechaDonacion() {
        return fechaDonacion;
    }

    public void setFechaDonacion(LocalDate fechaDonacion) {
        this.fechaDonacion = fechaDonacion;
    }

    public Long getIdDonante() {
        return idDonante;
    }

    public void setIdDonante(Long idDonante) {
        this.idDonante = idDonante;
    }

    public Long getIdCampaña() {
        return idCampaña;
    }

    public void setIdCampaña(Long idCampaña) {
        this.idCampaña = idCampaña;
    }

    public Long getIdTipoDonacion() {
        return idTipoDonacion;
    }

    public void setIdTipoDonacion(Long idTipoDonacion) {
        this.idTipoDonacion = idTipoDonacion;
    }

    public Long getIdEstadoDonacion() {
        return idEstadoDonacion;
    }

    public void setIdEstadoDonacion(Long idEstadoDonacion) {
        this.idEstadoDonacion = idEstadoDonacion;
    }
}
