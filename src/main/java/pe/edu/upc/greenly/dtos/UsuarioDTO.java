package pe.edu.upc.greenly.dtos;

public class UsuarioDTO {

    private int id;
    private String username;
    private String password;
    private boolean enable;

    public UsuarioDTO(int id, String username, String password, boolean enable) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enable = enable;
    }

    public UsuarioDTO() {
    }

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

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enable=" + enable +
                '}';
    }
}