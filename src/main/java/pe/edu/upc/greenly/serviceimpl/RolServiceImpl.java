package pe.edu.upc.greenly.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.greenly.dtos.RolDTO;
import pe.edu.upc.greenly.entities.Rol;
import pe.edu.upc.greenly.repositories.RolRepository;
import pe.edu.upc.greenly.service.RolService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public RolDTO addRol(RolDTO rolDTO) {
        Rol rol = new Rol(rolDTO.getId(), rolDTO.getRol());
        Rol savedRol = rolRepository.save(rol);
        return new RolDTO(savedRol.getId(), savedRol.getRol());
    }

    @Override
    public void deleteRol(int id) {
        rolRepository.deleteById(id);
    }

    @Override
    public RolDTO findById(int id) {
        Rol rol = rolRepository.findById(id).orElse(null);
        if (rol != null) {
            return new RolDTO(rol.getId(), rol.getRol());
        }
        return null;
    }

    @Override
    public List<RolDTO> listAll() {
        List<Rol> roles = rolRepository.findAll();
        return roles.stream()
                .map(rol -> new RolDTO(rol.getId(), rol.getRol()))
                .collect(Collectors.toList());
    }
}