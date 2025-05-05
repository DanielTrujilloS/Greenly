package pe.edu.upc.greenly.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.greenly.dtos.Ubicacion_CampañaDTO;
import pe.edu.upc.greenly.entities.Ubicacion_Campaña;
import pe.edu.upc.greenly.repositories.Ubicacion_CampañaRepository;
import pe.edu.upc.greenly.service.Ubicacion_CampañaService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Ubicacion_CampañaServiceImpl implements Ubicacion_CampañaService {
    @Autowired
    private Ubicacion_CampañaRepository ubicacionCampañaRepository;

    @Override
    public Ubicacion_CampañaDTO addUbicacion_Campaña(Ubicacion_CampañaDTO dto) {
        Ubicacion_Campaña ubicacion = new Ubicacion_Campaña();
        ubicacion.setDepartamento(dto.getDepartamento());
        ubicacion.setDistrito(dto.getDistrito());
        ubicacion.setDireccion(dto.getDireccion());

        Ubicacion_Campaña saved = ubicacionCampañaRepository.save(ubicacion);

        return new Ubicacion_CampañaDTO(
                saved.getId(),
                saved.getDepartamento(),
                saved.getDistrito(),
                saved.getDireccion()
        );
    }

    @Override
    public void deleteUbicacion_Campaña(int id) {
        ubicacionCampañaRepository.deleteById(id);
    }

    @Override
    public Ubicacion_CampañaDTO findById(int id) {
        return ubicacionCampañaRepository.findById(id)
                .map(ubicacion -> new Ubicacion_CampañaDTO(
                        ubicacion.getId(),
                        ubicacion.getDepartamento(),
                        ubicacion.getDistrito(),
                        ubicacion.getDireccion()
                ))
                .orElse(null);
    }

    @Override
    public List<Ubicacion_CampañaDTO> listAll() {
        List<Ubicacion_Campaña> ubicaciones = ubicacionCampañaRepository.findAll();
        return ubicaciones.stream()
                .map(ubicacion -> new Ubicacion_CampañaDTO(
                        ubicacion.getId(),
                        ubicacion.getDepartamento(),
                        ubicacion.getDistrito(),
                        ubicacion.getDireccion()
                ))
                .collect(Collectors.toList());
    }
}
