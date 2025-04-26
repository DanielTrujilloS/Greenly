package pe.edu.upc.greenly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greenly.dtos.UsuarioDTO;
import pe.edu.upc.greenly.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/Greenly/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public UsuarioDTO addUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.addUsuario(usuarioDTO);
    }

    @GetMapping("/{id}")
    public UsuarioDTO getUsuario(@PathVariable int id) {
        return usuarioService.findById(id);
    }

    @GetMapping
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioService.listAll();
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable int id) {
        usuarioService.deleteUsuario(id);
    }
}