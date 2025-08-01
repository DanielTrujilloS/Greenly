package pe.edu.upc.greenly.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.greenly.entities.Usuario;
import pe.edu.upc.greenly.security.UserSecurity;
import pe.edu.upc.greenly.services.UsuarioService;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario userFound = userService.findByUsername(username);
        // --- FIX START ---
        if (userFound == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        // --- FIX END ---

        return new UserSecurity(userFound);
    }
}
