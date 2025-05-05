package pe.edu.upc.greenly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greenly.entities.EstadoDonacion;
import pe.edu.upc.greenly.entities.TipoDonacion;
import pe.edu.upc.greenly.service.EstadoDonacionService;
import pe.edu.upc.greenly.service.TipoDonacionService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/estadoDonaciones")
public class EstadoDonacionController {

    @Autowired
    private EstadoDonacionService estadoDonacionService;

    @GetMapping("/listar")
    // http://localhost:8080/tipoDonaciones/listar
    public List<EstadoDonacion> listarTodo(){
        return estadoDonacionService.listAll();
    }

    @PostMapping("/agregar")
    // http://localhost:8080/tipoDonaciones/agregar
    public ResponseEntity<EstadoDonacion> insertarTipoDonacion(@RequestBody EstadoDonacion estadoDonacion) {
        EstadoDonacion estadoDonacionNueva = estadoDonacionService.addEstadoDonacion(estadoDonacion);
        return new ResponseEntity<>(estadoDonacionNueva, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    // http://localhost:8080/tipoDonaciones/eliminar/1
    public ResponseEntity<HttpStatus> eliminarTipoDonacion(@PathVariable("id") Long id) {
        estadoDonacionService.deleteEstadoDonacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<EstadoDonacion> actualizarTipoDonacion(@PathVariable("id") Long id, @RequestBody EstadoDonacion estadoDonacion) {
        estadoDonacion.setId(id);
        EstadoDonacion editEstadoDonacion = estadoDonacionService.editEstadoDonacion(estadoDonacion);
        return new ResponseEntity<>(editEstadoDonacion,HttpStatus.OK);
    }
}
