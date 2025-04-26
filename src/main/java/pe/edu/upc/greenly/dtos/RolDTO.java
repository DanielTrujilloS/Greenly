package pe.edu.upc.greenly.dtos;

public class RolDTO {

    private int id;
    private String rol;

    // Constructor
    public RolDTO(int id, String rol) {
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
}