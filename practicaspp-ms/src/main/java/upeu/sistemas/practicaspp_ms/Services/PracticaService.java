package upeu.sistemas.practicaspp_ms.Services;

import upeu.sistemas.practicaspp_ms.Entities.Practica;
import upeu.sistemas.practicaspp_ms.Entities.EstadoPractica;

import java.util.List;
import java.util.Optional;

public interface PracticaService {

    Practica savePractica(Practica practica);
    Practica updatePractica(Long id, EstadoPractica estado);
    List<Practica> getAllPracticas();
    List<Practica> getPracticasByEstado(EstadoPractica estado);
    List<Practica> getPracticasByPostulacionId(Long postulacionId);
    List<Practica> getPracticasByPersonaId(Long personaId);
    Optional<Practica> getPracticaById(Long id);
    void deletePractica(Long id);
}
