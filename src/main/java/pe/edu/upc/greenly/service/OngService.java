package pe.edu.upc.greenly.service;

import pe.edu.upc.greenly.dtos.OngDTO;
import pe.edu.upc.greenly.dtos.RolDTO;

import java.util.List;

public interface OngService {
    OngDTO addOng(OngDTO ongDTO);
    void deleteOng(int id);
    OngDTO findById(int id);
    List<OngDTO> listAll();
}
