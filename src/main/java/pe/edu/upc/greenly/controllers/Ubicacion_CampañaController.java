package pe.edu.upc.greenly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greenly.dtos.Ubicacion_CampañaDTO;
import pe.edu.upc.greenly.service.Ubicacion_CampañaService;

import java.util.List;

@RestController
@RequestMapping("/Greenly/ubicaciones-campaña")
public class Ubicacion_CampañaController {
    @Autowired
    private Ubicacion_CampañaService ubicacionService;

    @PostMapping
    public ResponseEntity<Ubicacion_CampañaDTO> addUbicacion(@RequestBody Ubicacion_CampañaDTO dto) {
        return ResponseEntity.ok(ubicacionService.addUbicacion_Campaña(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion_CampañaDTO> getUbicacion(@PathVariable int id) {
        Ubicacion_CampañaDTO dto = ubicacionService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Ubicacion_CampañaDTO> listUbicaciones() {
        return ubicacionService.listAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUbicacion(@PathVariable int id) {
        ubicacionService.deleteUbicacion_Campaña(id);
        return ResponseEntity.noContent().build();
    }
}
