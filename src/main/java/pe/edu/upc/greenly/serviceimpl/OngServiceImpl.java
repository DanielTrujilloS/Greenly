package pe.edu.upc.greenly.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.greenly.dtos.OngDTO;
import pe.edu.upc.greenly.entities.Ong;
import pe.edu.upc.greenly.entities.Usuario;
import pe.edu.upc.greenly.repositories.OngRepository;
import pe.edu.upc.greenly.repositories.UsuarioRepository;
import pe.edu.upc.greenly.service.OngService;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class OngServiceImpl implements OngService {
    @Autowired
    private OngRepository ongRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public OngDTO addOng(OngDTO ongDTO) {
        Usuario usuario = usuarioRepository.findById(ongDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + ongDTO.getUsuarioId()));

        Ong ong = new Ong();
        ong.setNombre(ongDTO.getNombre());
        ong.setDescripcion(ongDTO.getDescripcion());
        ong.setCorreo(ongDTO.getCorreo());
        ong.setDireccion(ongDTO.getDireccion());
        ong.setTelefono(ongDTO.getTelefono());
        ong.setUsuario(usuario);

        Ong savedOng = ongRepository.save(ong);

        return new OngDTO(
                savedOng.getId(),
                savedOng.getNombre(),
                savedOng.getDescripcion(),
                savedOng.getCorreo(),
                savedOng.getDireccion(),
                savedOng.getTelefono(),
                savedOng.getUsuario().getId()
        );
    }

    @Override
    public void deleteOng(int id) {
        ongRepository.deleteById(id);
    }

    @Override
    public OngDTO findById(int id) {
        Ong ong = ongRepository.findById(id).orElse(null);
        if (ong == null || ong.getUsuario() == null) {
            return null;
        }

        return new OngDTO(
                ong.getId(),
                ong.getNombre(),
                ong.getDescripcion(),
                ong.getCorreo(),
                ong.getDireccion(),
                ong.getTelefono(),
                ong.getUsuario().getId()
        );
    }

    @Override
    public List<OngDTO> listAll() {
        List<Ong> ongs = ongRepository.findAll();
        return ongs.stream()
                .filter(ong -> ong.getUsuario() != null)
                .map(ong -> new OngDTO(
                        ong.getId(),
                        ong.getNombre(),
                        ong.getDescripcion(),
                        ong.getCorreo(),
                        ong.getDireccion(),
                        ong.getTelefono(),
                        ong.getUsuario().getId()
                ))
                .collect(Collectors.toList());
    }
}
