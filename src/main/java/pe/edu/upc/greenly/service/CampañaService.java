package pe.edu.upc.greenly.service;

import pe.edu.upc.greenly.dtos.CampañaDTO;
import pe.edu.upc.greenly.dtos.RolDTO;
import pe.edu.upc.greenly.entities.Campaña;

import java.util.List;

public interface CampañaService {
    CampañaDTO addCampaña (CampañaDTO campañaDTO);
    void deleteCampaña (int id);
    CampañaDTO findById(int id);
    List<CampañaDTO> listAll();
}
