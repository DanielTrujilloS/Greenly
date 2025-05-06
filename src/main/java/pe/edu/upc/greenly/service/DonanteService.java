package pe.edu.upc.greenly.service;

import pe.edu.upc.greenly.dtos.DonanteDTO;
import pe.edu.upc.greenly.entities.Donante;

import java.util.List;

public interface DonanteService {
    DonanteDTO addDonante(DonanteDTO donanteDTO);
    void deleteDonante(int id);
    DonanteDTO findDonanteById(int id);
    List<DonanteDTO> listAllDonantes();
}
