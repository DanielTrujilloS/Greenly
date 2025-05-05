package pe.edu.upc.greenly.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.greenly.dtos.EstadoDonacionDTO;
import pe.edu.upc.greenly.entities.EstadoDonacion;
import pe.edu.upc.greenly.entities.TipoDonacion;
import pe.edu.upc.greenly.repositories.EstadoDonacionRepository;
import pe.edu.upc.greenly.service.EstadoDonacionService;

import java.util.List;

@Service
public class EstadoDonacionServiceImpl implements EstadoDonacionService {

    @Autowired
    private EstadoDonacionRepository estadoDonacionRepository;


    @Override
    public EstadoDonacion addEstadoDonacion(EstadoDonacion estadoDonacion) {

        if (estadoDonacion.getEstado() == null || estadoDonacion.getEstado().isEmpty()) {
            return null; // mensaje de error: "El estado del tipo de estado no puede estar vacio"
        }

        if (estadoDonacion.getFecha() == null || estadoDonacion.getEstado().isEmpty()) {
            return null; // mensaje de error: "La fecha del tipo de estado no puede estar vacio"
        }

        return estadoDonacionRepository.save(estadoDonacion);
    }

    @Override
    public void deleteEstadoDonacion(Long id) {
        EstadoDonacion estadoDonacionEncontrado = findById(id);
        if (estadoDonacionEncontrado != null) {
            estadoDonacionRepository.delete(estadoDonacionEncontrado);
        }
    }

    @Override
    public EstadoDonacion findById(Long id) {
        return estadoDonacionRepository.findById(id).orElse(null);
    }

    @Override
    public List<EstadoDonacion> listAll() {
        return estadoDonacionRepository.findAll();
    }

    @Override
    public EstadoDonacion editEstadoDonacion(EstadoDonacion estadoDonacion) {
        EstadoDonacion estadoDonacionEncontrado = findById(estadoDonacion.getId());

        if (estadoDonacion.getEstado() != null || !estadoDonacion.getEstado().isEmpty()) {
            estadoDonacionEncontrado.setEstado(estadoDonacion.getEstado());
        }
        if (estadoDonacion.getFecha() != null || !estadoDonacion.getEstado().isEmpty()) {
            estadoDonacionEncontrado.setFecha(estadoDonacion.getFecha());
        }

        List<EstadoDonacion> estadoDonaciones = estadoDonacionRepository.findByEstado(estadoDonacion.getEstado());
        if (estadoDonaciones != null && !estadoDonaciones.isEmpty()) {
            return null;
        }

        return estadoDonacionRepository.save(estadoDonacionEncontrado);
    }
}
