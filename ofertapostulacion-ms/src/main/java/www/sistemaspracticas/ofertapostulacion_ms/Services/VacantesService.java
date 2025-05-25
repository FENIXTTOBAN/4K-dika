package www.sistemaspracticas.ofertapostulacion_ms.Services;

import www.sistemaspracticas.ofertapostulacion_ms.Entities.Vacantes;
import java.util.List;
import java.util.Optional;

public interface VacantesService {
    Vacantes guardar(Vacantes vacantes);
    Vacantes updateVacantes(Long id, int ocupados);
    Optional<Vacantes> findById(Long id);
    Optional<Vacantes> findByOfertaId(Long ofertaId);
    List<Vacantes> listarTodo();
}