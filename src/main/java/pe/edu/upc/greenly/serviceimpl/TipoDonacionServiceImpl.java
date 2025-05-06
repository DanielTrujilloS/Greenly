package pe.edu.upc.greenly.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.greenly.entities.EstadoDonacion;
import pe.edu.upc.greenly.entities.TipoDonacion;
import pe.edu.upc.greenly.repositories.TipoDonacionRepository;
import pe.edu.upc.greenly.service.TipoDonacionService;

import java.util.List;

@Service
public class TipoDonacionServiceImpl implements TipoDonacionService {

    @Autowired
    private TipoDonacionRepository tipoDonacionRepository;

    @Override
    public TipoDonacion addTipoDonacion(TipoDonacion tipoDonacion) {

        if (tipoDonacion.getNombre() == null || tipoDonacion.getNombre().isEmpty()) {
            return null; // mensaje de error: "El estado del tipo de estado no puede estar vacio"
        }

        return tipoDonacionRepository.save(tipoDonacion);
    }

    @Override
    public void deleteTipoDonacion(Long id) {
        TipoDonacion tipoDonacionEncontrado = findById(id);
        if (tipoDonacionEncontrado != null) {
            tipoDonacionRepository.delete(tipoDonacionEncontrado);
        }
    }

    @Override
    public TipoDonacion findById(Long id) {
        return tipoDonacionRepository.findById(id).orElse(null);
    }

    @Override
    public List<TipoDonacion> listAll() {
        return tipoDonacionRepository.findAll();
    }

    @Override
    public TipoDonacion editTipoDonacion(TipoDonacion tipoDonacion) {
        TipoDonacion tipoDonacionEncontrado = findById(tipoDonacion.getId());

        if (tipoDonacion.getNombre() != null || !tipoDonacion.getNombre().isEmpty()) {
            tipoDonacionEncontrado.setNombre(tipoDonacion.getNombre());
        }

        List<TipoDonacion> tipoDonaciones = tipoDonacionRepository.findByNombre(tipoDonacion.getNombre());
        if (tipoDonaciones != null && !tipoDonaciones.isEmpty()) {
            return null;
        }

        return tipoDonacionRepository.save(tipoDonacionEncontrado);
    }
}
