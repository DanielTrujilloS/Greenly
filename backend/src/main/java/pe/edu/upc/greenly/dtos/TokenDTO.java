package pe.edu.upc.greenly.dtos;

public class TokenDTO {
    private String jwtToken;
    private Long id;
    private String authorities;
    public TokenDTO() {
    }

    public TokenDTO(String jwtToken, Long id, String authorities) {
        this.jwtToken = jwtToken;
        this.id = id;
        this.authorities = authorities;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

}
