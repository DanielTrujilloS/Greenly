package pe.edu.upc.greenly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greenly.dtos.DonacionDTO;
import pe.edu.upc.greenly.dtos.DonanteDTO;
import pe.edu.upc.greenly.entities.Donacion;
import pe.edu.upc.greenly.entities.TipoDonacion;
import pe.edu.upc.greenly.service.DonacionService;
import pe.edu.upc.greenly.service.TipoDonacionService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/donaciones")
public class DonacionController {

    @Autowired
    private DonacionService donacionService;

    @GetMapping("/listar")
    // http://localhost:8080/donaciones/listar
    public List<Donacion> listarTodo(){
        return donacionService.listAll();
    }

    @PostMapping("/agregar")
    // http://localhost:8080/donaciones/agregar
    public ResponseEntity<DonacionDTO> insertarDonacion(@RequestBody DonacionDTO donacionDTO) {
        DonacionDTO donacionDTONueva = donacionService.addDonacionDTO(donacionDTO);
        return new ResponseEntity<>(donacionDTONueva, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    // http://localhost:8080/donaciones/eliminar/1
    public ResponseEntity<HttpStatus> eliminarDonacion(@PathVariable("id") Long id) {
        donacionService.deleteDonacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Donacion> actualizarDonacion(@PathVariable("id") Long id, @RequestBody Donacion donacion) {
        donacion.setId(id);
        Donacion editDonacion = donacionService.editDonacion(donacion);
        return new ResponseEntity<>(editDonacion,HttpStatus.OK);
    }
}
