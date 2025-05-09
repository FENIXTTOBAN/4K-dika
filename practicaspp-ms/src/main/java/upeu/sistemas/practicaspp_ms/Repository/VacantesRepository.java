package upeu.sistemas.practicaspp_ms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upeu.sistemas.practicaspp_ms.Entities.Vacantes;

import java.util.Optional;

public interface VacantesRepository extends JpaRepository<Vacantes, Long> {
    Optional<Vacantes> findByOfertaId(Long ofertaId);
}
