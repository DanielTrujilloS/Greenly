package pe.edu.upc.greenly.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pe.edu.upc.greenly.entities.Usuario;

import java.util.Collection;

public class UserSecurity implements UserDetails {

    private Usuario user;


    /* Metodos a implementar por UserDetails */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities().stream().map(AuthoritySecurity::new).toList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    /* Metodos de acceso e interaccion de la clase */

    public UserSecurity() {
    }

    public UserSecurity(Usuario user) {
        this.user = user;
    }


    public Usuario getUsuario() {
        return user;
    }

    public void setUsuario(Usuario user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserSecurity{" +
                "user=" + user +
                '}';
    }
}