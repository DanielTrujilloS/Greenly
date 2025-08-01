package pe.edu.upc.greenly.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.greenly.dtos.DonacionDTO;
import pe.edu.upc.greenly.dtos.TotalDonacionesPorCampañaDTO;
import pe.edu.upc.greenly.entities.*;
import pe.edu.upc.greenly.repositories.*;
import pe.edu.upc.greenly.services.DonacionService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service


public class DonacionServiceImpl implements DonacionService {

    @Autowired
    DonacionRepository donacionRepository;

    @Autowired
    DonanteRepository donanteRepository;

    @Autowired
    CampañaRepository campañaRepository;

    @Autowired
    TipoDonacionRepository tipoDonacionRepository;

    @Autowired
    EstadoDonacionRepository estadoDonacionRepository;

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
        Donante donante;
        if (donacionDTO.getIdDonante() != null) {
            donante = donanteRepository.findById(donacionDTO.getIdDonante())
                    .orElseThrow(() -> new RuntimeException("Donante no encontrado"));
        } else {
            List<Donante> donantes = donanteRepository.findAll();
            if (donantes.isEmpty()) throw new RuntimeException("No hay donantes en la base de datos");
            donante = donantes.get((int)(Math.random() * donantes.size()));
        }

        // Si la campaña es null, seleccionar una random
        Campaña campaña;
        if (donacionDTO.getIdCampaña() != null) {
            campaña = campañaRepository.findById(donacionDTO.getIdCampaña())
                    .orElseThrow(() -> new RuntimeException("Campaña no encontrada"));
        } else {
            List<Campaña> campañas = campañaRepository.findAll();
            if (campañas.isEmpty()) throw new RuntimeException("No hay campañas en la base de datos");
            campaña = campañas.get((int)(Math.random() * campañas.size()));
        }

        // Si tipoDonacion es null, seleccionar uno random
        TipoDonacion tipoDonacion;
        if (donacionDTO.getIdTipoDonacion() != null) {
            tipoDonacion = tipoDonacionRepository.findById(donacionDTO.getIdTipoDonacion())
                    .orElseThrow(() -> new RuntimeException("Tipo de donación no encontrado"));
        } else {
            List<TipoDonacion> tipos = tipoDonacionRepository.findAll();
            if (tipos.isEmpty()) throw new RuntimeException("No hay tipos de donación en la base de datos");
            tipoDonacion = tipos.get((int)(Math.random() * tipos.size()));
        }

        // Si estadoDonacion es null, seleccionar uno random
        EstadoDonacion estadoDonacion;
        if (donacionDTO.getIdEstadoDonacion() != null) {
            estadoDonacion = estadoDonacionRepository.findById(donacionDTO.getIdEstadoDonacion())
                    .orElseThrow(() -> new RuntimeException("Estado de donación no encontrado"));
        } else {
            List<EstadoDonacion> estados = estadoDonacionRepository.findAll();
            if (estados.isEmpty()) throw new RuntimeException("No hay estados de donación en la base de datos");
            estadoDonacion = estados.get((int)(Math.random() * estados.size()));
        }
        /*
        Donante donante = donanteRepository.findById(donacionDTO.getIdDonante());
        Campaña campaña = campañaRepository.findById(donacionDTO.getIdCampaña());
        TipoDonacion tipoDonacion = tipoDonacionRepository.findById(donacionDTO.getIdTipoDonacion());
        EstadoDonacion estadoDonacion = estadoDonacionRepository.findById(donacionDTO.getIdEstadoDonacion());
        */

        //Donacion donacion = new Donacion(0L, donacionDTO.getName(), donacionDTO.getDescripcion(), donacionDTO.getMontoDonado(), donacionDTO.getMetodoEntrega(), donacionDTO.getFechaDonacion(), null, null, null, null);
        Donacion donacion = new Donacion();
        donacion.setName(donacionDTO.getName());
        donacion.setDescripcion(donacionDTO.getDescripcion());
        donacion.setMontoDonado(donacionDTO.getMontoDonado());
        donacion.setMetodoEntrega(donacionDTO.getMetodoEntrega());
        donacion.setFechaDonacion(donacionDTO.getFechaDonacion());
        //return null;
        donacion.setDonante(donante);
        donacion.setCampaña(campaña);
        donacion.setTipoDonacion(tipoDonacion);
        donacion.setEstadoDonacion(estadoDonacion);
        Donacion savedDonacion = donacionRepository.save(donacion);
        System.out.println("ID generado: " + savedDonacion.getId());
        if(savedDonacion.getId()==null){
            System.out.println("No se puede agregar la donacion");
        }
        else {
            System.out.println("se puede agregar la donacion");
        }
        return new DonacionDTO(
                savedDonacion.getId(),
                savedDonacion.getName(),
                savedDonacion.getDescripcion(),
                savedDonacion.getMontoDonado(),
                savedDonacion.getMetodoEntrega(),
                savedDonacion.getFechaDonacion(),
                savedDonacion.getDonante().getId(),
                savedDonacion.getCampaña().getId(),
                savedDonacion.getTipoDonacion().getId(),
                savedDonacion.getEstadoDonacion().getId()

        );
    }

    /*@Override
    public void deleteDonacion(Long id) {
        Donacion donacionEncontrado = donacionRepository.findById(id).orElse(null);
        if (donacionEncontrado != null) {
            donacionRepository.delete(donacionEncontrado);
        }
    }*/

    @Override
    public void deleteDonacion(Long id) {
        donacionRepository.deleteById(id);
        /*try {
            donacionRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar donación con ID " + id);
            e.printStackTrace();
        }*/
    }

    @Override
    public DonacionDTO findById(Long id) {
        return donacionRepository.findById(id)
                .map(c -> new DonacionDTO(
                        c.getId(),
                        c.getName(),
                        c.getDescripcion(),
                        c.getMontoDonado(),
                        c.getMetodoEntrega(),
                        c.getFechaDonacion(),
                        c.getDonante().getId(),
                        c.getCampaña().getId(),
                        c.getTipoDonacion().getId(),
                        c.getEstadoDonacion().getId()
                ))
                .orElse(null);
    }

    @Override
    public List<DonacionDTO> listAll() {
        return donacionRepository.findAll().stream()
                .map(c -> new DonacionDTO(
                        c.getId(),
                        c.getName(),
                        c.getDescripcion(),
                        c.getMontoDonado(),
                        c.getMetodoEntrega(),
                        c.getFechaDonacion(),
                        c.getDonante().getId(),
                        c.getCampaña().getId(),
                        c.getTipoDonacion().getId(),
                        c.getEstadoDonacion().getId()
                ))
                .collect(Collectors.toList());
    }

    /*@Override
    public List<Donacion> listAll() {
        return donacionRepository.findAll();
    }*/

    /*@Override
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
    }*/

    @Override
    public DonacionDTO updateDonacion(Long id, DonacionDTO donacionDTO) {
        if (donacionDTO.getName() == null || donacionDTO.getName().isEmpty()) {
            throw new RuntimeException("El nombre de la donación no puede estar vacio");
        }
        if (donacionDTO.getDescripcion() == null || donacionDTO.getDescripcion().isEmpty()) {
            throw new RuntimeException("La descripción de la donación no puede estar vacia");
        }
        if (donacionDTO.getMontoDonado() == null || donacionDTO.getMontoDonado() < 0.0) {
            throw new RuntimeException("El monto donado de la donación no puede ser menor a 0");
        }
        if (donacionDTO.getMetodoEntrega() == null || donacionDTO.getMetodoEntrega().isEmpty()) {
            throw new RuntimeException("El método de entrega no puede estar vacio");
        }

        // --- Selección automática si viene null ---
        Donante donante = (donacionDTO.getIdDonante() != null) ?
                donanteRepository.findById(donacionDTO.getIdDonante())
                        .orElseThrow(() -> new RuntimeException("Donante no encontrado")) :
                donanteRepository.findAll().stream().findAny()
                        .orElseThrow(() -> new RuntimeException("No existen donantes"));

        Campaña campaña = (donacionDTO.getIdCampaña() != null) ?
                campañaRepository.findById(donacionDTO.getIdCampaña())
                        .orElseThrow(() -> new RuntimeException("Campaña no encontrada")) :
                campañaRepository.findAll().stream().findAny()
                        .orElseThrow(() -> new RuntimeException("No existen campañas"));

        TipoDonacion tipoDonacion = (donacionDTO.getIdTipoDonacion() != null) ?
                tipoDonacionRepository.findById(donacionDTO.getIdTipoDonacion())
                        .orElseThrow(() -> new RuntimeException("Tipo de donación no encontrado")) :
                tipoDonacionRepository.findAll().stream().findAny()
                        .orElseThrow(() -> new RuntimeException("No existen tipos de donación"));

        EstadoDonacion estadoDonacion = (donacionDTO.getIdEstadoDonacion() != null) ?
                estadoDonacionRepository.findById(donacionDTO.getIdEstadoDonacion())
                        .orElseThrow(() -> new RuntimeException("Estado de donación no encontrado")) :
                estadoDonacionRepository.findAll().stream().findAny()
                        .orElseThrow(() -> new RuntimeException("No existen estados de donación"));

        Donacion donacion = new Donacion();
        donacion.setName(donacionDTO.getName());
        donacion.setDescripcion(donacionDTO.getDescripcion());
        donacion.setMontoDonado(donacionDTO.getMontoDonado());
        donacion.setMetodoEntrega(donacionDTO.getMetodoEntrega());
        donacion.setFechaDonacion(donacionDTO.getFechaDonacion());
        donacion.setDonante(donante);
        donacion.setCampaña(campaña);
        donacion.setTipoDonacion(tipoDonacion);
        donacion.setEstadoDonacion(estadoDonacion);

        Donacion savedDonacion = donacionRepository.save(donacion);

        return new DonacionDTO(
                savedDonacion.getId(),
                savedDonacion.getName(),
                savedDonacion.getDescripcion(),
                savedDonacion.getMontoDonado(),
                savedDonacion.getMetodoEntrega(),
                savedDonacion.getFechaDonacion(),
                savedDonacion.getDonante().getId(),
                savedDonacion.getCampaña().getId(),
                savedDonacion.getTipoDonacion().getId(),
                savedDonacion.getEstadoDonacion().getId()
        );
    }

    //SQL QUERY TOTAL DE DONACIONES POR CAMPAÑA RONALD
    @Override
    public List<TotalDonacionesPorCampañaDTO> obtenerTotalesPorCampaña() {
        List<Object[]> resultados = donacionRepository.findTotalesPorCampaña();
        List<TotalDonacionesPorCampañaDTO> listaDTO = new ArrayList<>();

        for (Object[] fila : resultados) {
            Long campañaId = ((Number) fila[0]).longValue();
            String nombreCampaña = (String) fila[1];
            Double total = ((Number) fila[2]).doubleValue();

            listaDTO.add(new TotalDonacionesPorCampañaDTO(campañaId, nombreCampaña, total));
        }

        return listaDTO;
    }

    @Override
    public List<DonacionDTO> obtenerDonacionesMasAntiguas() {
        return donacionRepository.donacionesMasAntiguas();
    }

    @Override
    public List<DonacionDTO> obtenerDonacionesRecientes() {
        return donacionRepository.donacionesRecientes();
    }
}
