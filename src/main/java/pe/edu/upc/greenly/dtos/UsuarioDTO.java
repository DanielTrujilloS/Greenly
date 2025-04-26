package pe.edu.upc.greenly.dtos;

public class UsuarioDTO {

    private int id;
    private String username;
    private String password;
    private boolean enable;
    private RolDTO rol; // Relación con RolDTO

    // Constructor
    public UsuarioDTO(int id, String username, String password, boolean enable, RolDTO rol) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enable = enable;
        this.rol = rol;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public RolDTO getRol() {
        return rol;
    }

    public void setRol(RolDTO rol) {
        this.rol = rol;
    }
}