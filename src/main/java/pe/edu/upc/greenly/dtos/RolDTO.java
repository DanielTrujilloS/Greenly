package pe.edu.upc.greenly.dtos;

public class RolDTO {

    private int id;
    private String rol;
    private int usuarioId;

    public RolDTO(int id, String rol, int usuarioId) {
        this.id = id;
        this.rol = rol;
        this.usuarioId = usuarioId;
    }

    public RolDTO() {
    }

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

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return "RolDTO{" +
                "id=" + id +
                ", rol='" + rol + '\'' +
                ", usuarioId=" + usuarioId +
                '}';
    }
}