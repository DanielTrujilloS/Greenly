package pe.edu.upc.greenly.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String rol;

    // Relación uno a uno con Usuario
    @OneToOne(mappedBy = "rol", fetch = FetchType.LAZY)
    private Usuario usuario;

    // Constructor por defecto
    public Rol() {}

    // Constructor con parámetros
    public Rol(int id, String rol) {
        this.id = id;
        this.rol = rol;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}