package pe.edu.upc.greenly.services;

import pe.edu.upc.greenly.dtos.CampañaFavoritaDTO;

import java.util.List;

public interface CampañaFavoritaService {
    //public void insert(CampañaFavorita campañaFavorita);
    //public List<CampañaFavorita> list();
    public void delete(Long id);
    CampañaFavoritaDTO findById(Long id);
    CampañaFavoritaDTO addCampañaFavorito(CampañaFavoritaDTO dto);
    List<CampañaFavoritaDTO> listAll();
    CampañaFavoritaDTO updateCampañaFavorita(Long id, CampañaFavoritaDTO dto);
}
