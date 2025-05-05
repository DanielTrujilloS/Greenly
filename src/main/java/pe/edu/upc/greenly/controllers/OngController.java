package pe.edu.upc.greenly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greenly.dtos.OngDTO;
import pe.edu.upc.greenly.service.OngService;

import java.util.List;

@RestController
@RequestMapping("/Greenly/ongs")
public class OngController {
    @Autowired
    private OngService ongService;

    @PostMapping
    public ResponseEntity<OngDTO> addOng(@RequestBody OngDTO dto) {
        return ResponseEntity.ok(ongService.addOng(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OngDTO> getOng(@PathVariable int id) {
        OngDTO dto = ongService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<OngDTO> listOngs() {
        return ongService.listAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOng(@PathVariable int id) {
        ongService.deleteOng(id);
        return ResponseEntity.noContent().build();
    }
}
