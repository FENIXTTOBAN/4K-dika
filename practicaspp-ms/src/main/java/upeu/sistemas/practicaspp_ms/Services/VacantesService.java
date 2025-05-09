package upeu.sistemas.practicaspp_ms.Services;

import upeu.sistemas.practicaspp_ms.Entities.Vacantes;

import java.util.List;
import java.util.Optional;

public interface VacantesService {
    Vacantes guardar(Vacantes vacantes);
    Vacantes updateCupos(Long id, int ocupados);
    Optional<Vacantes> findById(Long id);
    Optional<Vacantes> findByOfertaId(Long ofertaId);
    List<Vacantes> listarTodo();
}
