package pe.edu.upc.greenly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greenly.dtos.CampañaDTO;
import pe.edu.upc.greenly.service.CampañaService;

import java.util.List;

@RestController
@RequestMapping("/Greenly/campañas")
public class CampañaController {
    @Autowired
    private CampañaService campañaService;

    @PostMapping
    public ResponseEntity<CampañaDTO> addCampaña(@RequestBody CampañaDTO dto) {
        return ResponseEntity.ok(campañaService.addCampaña(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampañaDTO> getCampaña(@PathVariable int id) {
        CampañaDTO dto = campañaService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<CampañaDTO> listCampañas() {
        return campañaService.listAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaña(@PathVariable int id) {
        campañaService.deleteCampaña(id);
        return ResponseEntity.noContent().build();
    }
}
