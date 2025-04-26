package pe.edu.upc.greenly.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.greenly.dtos.RolDTO;
import pe.edu.upc.greenly.dtos.UsuarioDTO;
import pe.edu.upc.greenly.entities.Rol;
import pe.edu.upc.greenly.entities.Usuario;
import pe.edu.upc.greenly.repositories.UsuarioRepository;
import pe.edu.upc.greenly.service.RolService;
import pe.edu.upc.greenly.service.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolService rolService;

    @Override
    public UsuarioDTO addUsuario(UsuarioDTO usuarioDTO) {
        RolDTO rolDTO = usuarioDTO.getRol();
        Rol rol = new Rol(rolDTO.getId(), rolDTO.getRol());
        Usuario usuario = new Usuario(usuarioDTO.getId(), usuarioDTO.getUsername(), usuarioDTO.getPassword(), usuarioDTO.isEnable(), rol);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(savedUsuario.getId(), savedUsuario.getUsername(), savedUsuario.getPassword(), savedUsuario.isEnable(), rolDTO);
    }

    @Override
    public void deleteUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioDTO findById(int id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            RolDTO rolDTO = new RolDTO(usuario.getRol().getId(), usuario.getRol().getRol());
            return new UsuarioDTO(usuario.getId(), usuario.getUsername(), usuario.getPassword(), usuario.isEnable(), rolDTO);
        }
        return null;
    }

    @Override
    public List<UsuarioDTO> listAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getUsername(), usuario.getPassword(), usuario.isEnable(), new RolDTO(usuario.getRol().getId(), usuario.getRol().getRol())))
                .collect(Collectors.toList());
    }
}