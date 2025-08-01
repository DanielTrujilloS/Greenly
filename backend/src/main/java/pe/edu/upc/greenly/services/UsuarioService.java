package pe.edu.upc.greenly.services;

import pe.edu.upc.greenly.dtos.TokenDTO;
import pe.edu.upc.greenly.dtos.UsuarioDTO;
import pe.edu.upc.greenly.entities.Usuario;

import java.util.List;

public interface UsuarioService {

    UsuarioDTO addUsuario(UsuarioDTO usuarioDTO);
    void deleteUsuario(Long id);
    UsuarioDTO findById(Long id);
    List<UsuarioDTO> listAll();
    UsuarioDTO updateUsuario(Long id,UsuarioDTO usuarioDTO);
    public Usuario findByUsername(String username);
    public Usuario addUser(Usuario user);
    TokenDTO registrarYGenerarToken(UsuarioDTO usuarioDTO);
}