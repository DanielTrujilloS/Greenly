package pe.edu.upc.greenly.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.greenly.dtos.DonacionDTO;
import pe.edu.upc.greenly.entities.*;
import pe.edu.upc.greenly.repositories.*;
import pe.edu.upc.greenly.service.DonacionService;

import java.util.List;

@Service
public class DonacionServiceImpl implements DonacionService {

    @Autowired
    private DonacionRepository donacionRepository;

    @Autowired
    private DonanteRepository donanteRepository;

    @Autowired
    private CampañaRepository campañaRepository;

    @Autowired
    private TipoDonacionRepository tipoDonacionRepository;

    @Autowired
    private EstadoDonacionRepository estadoDonacionRepository;

    @Override
    public DonacionDTO addDonacionDTO(DonacionDTO donacionDTO) {
        if (donacionDTO.getName() == null || donacionDTO.getName().isEmpty()) {
            return null; // mensaje de error: "El nombre de la donación no puede estar vacio"
        }

        if (donacionDTO.getDescripcion() == null || donacionDTO.getDescripcion().isEmpty()) {
            return null; // mensaje de error: "La descripción de la donación no puede estar vacio"
        }

        if (donacionDTO.getMontoDonado() < 0.0) {
            return null; // mensaje de error: "El monto donado de la donación no puede ser menor a 0"
        }

        if (donacionDTO.getMetodoEntrega() == null || donacionDTO.getMetodoEntrega().isEmpty()) {
            return null;
        }

        /*
        Donante donante = donanteRepository.findById(donacionDTO.getIdDonante());
        Campaña campaña = campañaRepository.findById(donacionDTO.getIdCampaña());
        TipoDonacion tipoDonacion = tipoDonacionRepository.findById(donacionDTO.getIdTipoDonacion());
        EstadoDonacion estadoDonacion = estadoDonacionRepository.findById(donacionDTO.getIdEstadoDonacion());
        */

        Donacion donacion = new Donacion(0L, donacionDTO.getName(), donacionDTO.getDescripcion(), donacionDTO.getMontoDonado(), donacionDTO.getMetodoEntrega(), donacionDTO.getFechaDonacion(), null, null, null, null);

        return null;
        //return donacionRepository.save(donacionDTO);
    }

    @Override
    public void deleteDonacion(Long id) {
        Donacion donacionEncontrado = donacionRepository.findById(id).orElse(null);
        if (donacionEncontrado != null) {
            donacionRepository.delete(donacionEncontrado);
        }
    }

    @Override
    public Donacion findById(Long id) {
        return donacionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Donacion> listAll() {
        return donacionRepository.findAll();
    }

    @Override
    public Donacion editDonacion(Donacion donacion) {
        Donacion donacionEncontrado = findById(donacion.getId());

        if (donacion.getName() != null || !donacion.getName().isEmpty()) {
            donacionEncontrado.setName(donacion.getName());
        }
        if (donacion.getDescripcion() != null || !donacion.getDescripcion().isEmpty()) {
            donacionEncontrado.setDescripcion(donacion.getDescripcion());
        }
        if (donacion.getMontoDonado() != null) {
            donacionEncontrado.setMontoDonado(donacion.getMontoDonado());
        }
        if (donacion.getMetodoEntrega() != null || !donacion.getMetodoEntrega().isEmpty()) {
            donacionEncontrado.setMetodoEntrega(donacion.getMetodoEntrega());
        }
        if (donacion.getFechaDonacion() != null) {
            donacionEncontrado.setFechaDonacion(donacion.getFechaDonacion());
        }

        return donacionRepository.save(donacionEncontrado);
    }
}
