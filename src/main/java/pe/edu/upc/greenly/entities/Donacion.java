package pe.edu.upc.greenly.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "donaciones")
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String descripcion;
    private Integer montoDonado;
    private String metodoEntrega;
    private LocalDate fechaDonacion;
    private Long idDonante;
    private Long idCampaña;
    /*
    private Long idTipoDonacion;
    private Long idEstadoDonacion;
     */

    @ManyToOne
    @JoinColumn(name = "estado_donacion_id")
    private EstadoDonacion estadoDonacion;

    @ManyToOne
    @JoinColumn(name = "tipo_donacion_id")
    private TipoDonacion tipoDonacion;


    public Donacion() {

    }

    /*
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

    public List<EstadoDonacion> getEstadoDonacion() {
        return estadoDonacion;
    }

    public void setEstadoDonacion(List<EstadoDonacion> estadoDonacion) {
        this.estadoDonacion = estadoDonacion;
    }
        */
}
