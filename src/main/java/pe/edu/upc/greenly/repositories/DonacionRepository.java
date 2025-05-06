package pe.edu.upc.greenly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.greenly.entities.Donacion;

public interface DonacionRepository extends JpaRepository<Donacion, Long> {
}
