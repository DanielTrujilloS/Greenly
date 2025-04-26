package pe.edu.upc.greenly.service;

import pe.edu.upc.greenly.dtos.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioDTO addUsuario(UsuarioDTO usuarioDTO);
    void deleteUsuario(int id);
    UsuarioDTO findById(int id);
    List<UsuarioDTO> listAll();
}