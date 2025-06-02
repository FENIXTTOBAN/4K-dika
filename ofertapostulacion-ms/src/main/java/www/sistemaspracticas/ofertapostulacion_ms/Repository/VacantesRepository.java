package www.sistemaspracticas.ofertapostulacion_ms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.Vacantes;
import java.util.Optional;

public interface VacantesRepository extends JpaRepository<Vacantes, Long> {
    Optional<Vacantes> findByOfertaId(Long ofertaId);
}
