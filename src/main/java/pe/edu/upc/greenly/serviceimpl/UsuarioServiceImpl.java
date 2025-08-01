package pe.edu.upc.greenly.serviceimpl;

import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.greenly.dtos.TokenDTO;
import pe.edu.upc.greenly.dtos.UsuarioDTO;
import pe.edu.upc.greenly.entities.Authority;
import pe.edu.upc.greenly.entities.Usuario;
import pe.edu.upc.greenly.exceptions.KeyRepeatedDataExeception;
import pe.edu.upc.greenly.repositories.UsuarioRepository;
import pe.edu.upc.greenly.security.JwtUtilService;
import pe.edu.upc.greenly.security.UserSecurity;
import pe.edu.upc.greenly.services.AuthorityService;
import pe.edu.upc.greenly.services.UsuarioService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private JwtUtilService jwtUtilService;


    @Override
    public Usuario addUser(Usuario user) {
        Usuario userFound = usuarioRepository.findByUsername(user.getUsername());
        if(userFound != null){
            throw new KeyRepeatedDataExeception("Username: "+ user.getUsername()+" is already registeted");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setEnabled(true);

        return usuarioRepository.save(user);
    }

    @Override
    //Funcion para el registro en el front
    public TokenDTO registrarYGenerarToken(UsuarioDTO dto) {
        //Construye usuario desde DTO
        Usuario nuevoUsuario= new Usuario();
        nuevoUsuario.setUsername(dto.getUsername());
        nuevoUsuario.setPassword(dto.getPassword());//Se encripta en addUser
        nuevoUsuario.setEnabled(true);

        List<Authority> roles= authoritiesFromString(dto.getAuthorities());
        if(roles.isEmpty()){
            throw new RuntimeException("Rol invalido"+ dto.getAuthorities());
        }
        nuevoUsuario.setAuthorities(roles);
        Usuario registrado = this.addUser(nuevoUsuario);


        //Genera token
        String token = jwtUtilService.generateToken(new UserSecurity(registrado));

        Long id = registrado.getId();
        String rolesString= registrado.getAuthorities()
                .stream()
                .map(Authority::getName)
                .collect(Collectors.joining(";"));

        return new TokenDTO(token, id, rolesString);

    }


    private List<Authority> authoritiesFromString(String authorities){
        List<Authority> authoritiesList = new ArrayList<>();
        List<String> authoritiesStringList = Arrays.stream(authorities.split(";")).toList();
        for(String authorityString : authoritiesStringList){
            Authority authority = authorityService.findByName(authorityString);
            if (authority != null){
                authoritiesList.add(authority);
            }
        }
        return authoritiesList;
    }
    //@Override
//    public UsuarioDTO addUsuario(UsuarioDTO usuarioDTO) {
//        return null;
//    }

    @Override
    public UsuarioDTO addUsuario(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            return new UsuarioDTO(
                    usuario.getId(),
                    usuario.getUsername(),
                    usuario.getPassword(),
                    null, // authorities
                    usuario.isEnabled()
            );
        }
        return null;
    }


    @Override
    public List<UsuarioDTO> listAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getId(),
                        usuario.getUsername(),
                        usuario.getPassword(),
                        null, // authorities
                        usuario.isEnabled()
                ))
                .collect(Collectors.toList());
    }


    @Override
    public UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }

        if (usuarioDTO.getUsername() != null) {
            usuario.setUsername(usuarioDTO.getUsername());
        }

        if (usuarioDTO.getPassword() != null) {
            usuario.setPassword(usuarioDTO.getPassword());
        }

        if (usuarioDTO.getEnabled() != null) {
            usuario.setEnabled(usuarioDTO.getEnabled());
        }

        Usuario updateUsuario = usuarioRepository.save(usuario);

        return new UsuarioDTO(
                updateUsuario.getId(),
                updateUsuario.getUsername(),
                updateUsuario.getPassword(),
                null,
                updateUsuario.isEnabled()
        );
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }


}