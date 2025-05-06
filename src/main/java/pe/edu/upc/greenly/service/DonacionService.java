package pe.edu.upc.greenly.service;

import pe.edu.upc.greenly.dtos.DonacionDTO;
import pe.edu.upc.greenly.entities.Donacion;
import pe.edu.upc.greenly.entities.TipoDonacion;

import java.util.List;

public interface DonacionService {
    public DonacionDTO addDonacionDTO(DonacionDTO donacionDTO);
    public void deleteDonacion(Long id);
    public Donacion findById(Long id);
    public List<Donacion> listAll();
    public Donacion editDonacion(Donacion donacionDTO);
}
