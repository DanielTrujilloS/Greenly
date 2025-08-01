package pe.edu.upc.greenly.controllers;

import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greenly.dtos.TokenDTO;
import pe.edu.upc.greenly.dtos.UsuarioDTO;
import pe.edu.upc.greenly.security.JwtUtilService;
import pe.edu.upc.greenly.security.UserSecurity;
import pe.edu.upc.greenly.services.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Greenly/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtilService jwtUtilService;

    @PostMapping("/agregar")
    public ResponseEntity<UsuarioDTO> addUsuario(@RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.addUsuario(dto));
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable Long id) {
        UsuarioDTO dto = usuarioService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/listar")
    public List<UsuarioDTO> listUsuarios() {
        return usuarioService.listAll();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id,@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.updateUsuario(id, usuarioDTO));
    }

    //Loguearte
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UsuarioDTO user) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        UserSecurity userSecurity = (UserSecurity)userDetailsService.loadUserByUsername(user.getUsername());

        String jwt = jwtUtilService.generateToken(userSecurity);
        Long id = userSecurity.getUsuario().getId();
        String authorities = userSecurity.getUsuario().getAuthorities()
                .stream()
                .map( a-> a.getName())
                .collect(Collectors.joining(";","",""));

        return new ResponseEntity<>(new TokenDTO(jwt,id, authorities), HttpStatus.OK);
    }

    //Registro
//    @PostMapping("/agregar")
//    public ResponseEntity<UsuarioDTO> addUsuario(@RequestBody UsuarioDTO dto){
//        try{
//            UsuarioDTO usuarioDTO = usuarioService.addUsuario(dto);
//            return ResponseEntity.ok(usuarioDTO);
//        }catch (RuntimeException e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(@RequestBody UsuarioDTO user) {
        try{
            TokenDTO tokenDTO= usuarioService.registrarYGenerarToken(user);
            return ResponseEntity.ok(tokenDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }


}