package pe.edu.upc.greenly.services;

import pe.edu.upc.greenly.dtos.DonacionDTO;
import pe.edu.upc.greenly.dtos.TotalDonacionesPorCampañaDTO;

import java.util.List;

public interface DonacionService {
    public DonacionDTO addDonacionDTO(DonacionDTO donacionDTO);
    public void deleteDonacion(Long id);
    public DonacionDTO findById(Long id);
    public List<DonacionDTO> listAll();
    //public Donacion editDonacion(Donacion donacionDTO);
    DonacionDTO updateDonacion(Long id, DonacionDTO dto);
    //SQL QUERY TOTAL DE DONACIONES POR CAMPAÑA RONALD
    List<TotalDonacionesPorCampañaDTO> obtenerTotalesPorCampaña();
    //ANDRES
    List<DonacionDTO> obtenerDonacionesMasAntiguas();
    //ANDRES
    List<DonacionDTO> obtenerDonacionesRecientes();
}
