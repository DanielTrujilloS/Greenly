package pe.edu.upc.greenly.service;

import pe.edu.upc.greenly.dtos.RolDTO;

import java.util.List;

public interface RolService {

    RolDTO addRol(RolDTO rolDTO);
    void deleteRol(int id);
    RolDTO findById(int id);
    List<RolDTO> listAll();
}