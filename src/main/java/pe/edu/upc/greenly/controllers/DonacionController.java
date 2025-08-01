package pe.edu.upc.greenly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greenly.dtos.DonacionDTO;
import pe.edu.upc.greenly.dtos.TotalDonacionesPorCampañaDTO;
import pe.edu.upc.greenly.services.DonacionService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/Greenly/donaciones")
public class DonacionController {

    @Autowired
    private DonacionService donacionService;

    @GetMapping("/listar")
    // http://localhost:8080/donaciones/listar
    public List<DonacionDTO> listarTodo(){
        return donacionService.listAll();
    }

    @PostMapping("/agregar")
    // http://localhost:8080/donaciones/agregar
    public ResponseEntity<DonacionDTO> insertarDonacion(@RequestBody DonacionDTO donacionDTO) {
        DonacionDTO donacionDTONueva = donacionService.addDonacionDTO(donacionDTO);
        return new ResponseEntity<>(donacionDTONueva, HttpStatus.CREATED);
    }

    /*@DeleteMapping("/eliminar/{id}")
    // http://localhost:8080/donaciones/eliminar/1
    public ResponseEntity<HttpStatus> eliminarDonacion(@PathVariable("id") Long id) {
        donacionService.deleteDonacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteDonacion(@PathVariable Long id) {
        donacionService.deleteDonacion(id);
        return ResponseEntity.noContent().build();
    }

    /*@PutMapping("/actualizar/{id}")
    public ResponseEntity<Donacion> actualizarDonacion(@PathVariable("id") Long id, @RequestBody Donacion donacion) {
        donacion.setId(id);
        Donacion editDonacion = donacionService.editDonacion(donacion);
        return new ResponseEntity<>(editDonacion,HttpStatus.OK);
    }*/

    @PutMapping("/modificar/{id}")
    public ResponseEntity<DonacionDTO> modificarCampaña(
            @PathVariable Long id,
            @RequestBody DonacionDTO dto) {
        DonacionDTO updated = donacionService.updateDonacion(id, dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<DonacionDTO> getCampaña(@PathVariable Long id) {
        DonacionDTO dto = donacionService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    //SQL QUERY TOTAL DE DONACIONES POR CAMPAÑA RONALD
    @GetMapping("/totales-por-campaña")
    public ResponseEntity<List<TotalDonacionesPorCampañaDTO>> obtenerTotalesPorCampaña() {
        List<TotalDonacionesPorCampañaDTO> resultado = donacionService.obtenerTotalesPorCampaña();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/listar/antiguas")
    public ResponseEntity<List<DonacionDTO>> obtenerDonacionesMasAntiguas() {
        List<DonacionDTO> donaciones = donacionService.obtenerDonacionesMasAntiguas();
        return ResponseEntity.ok(donaciones);
    }

    @GetMapping("/listar/recientes")
    public ResponseEntity<List<DonacionDTO>> obtenerDonacionesRecientes() {
        List<DonacionDTO> donaciones = donacionService.obtenerDonacionesRecientes();
        return ResponseEntity.ok(donaciones);
    }
}
