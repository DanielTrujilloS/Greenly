package pe.edu.upc.greenly.service;

import pe.edu.upc.greenly.dtos.RolDTO;
import pe.edu.upc.greenly.dtos.Ubicacion_CampañaDTO;

import java.util.List;

public interface Ubicacion_CampañaService {
    Ubicacion_CampañaDTO addUbicacion_Campaña(Ubicacion_CampañaDTO ubicacion_campañaDTO);
    void deleteUbicacion_Campaña(int id);
    Ubicacion_CampañaDTO findById(int id);
    List<Ubicacion_CampañaDTO> listAll();
}
