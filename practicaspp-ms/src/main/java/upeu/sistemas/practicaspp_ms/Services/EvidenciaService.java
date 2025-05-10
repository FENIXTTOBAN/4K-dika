package upeu.sistemas.practicaspp_ms.Services;

import upeu.sistemas.practicaspp_ms.Entities.Evidencia;
import upeu.sistemas.practicaspp_ms.Entities.EstadoEvidencia;

import java.util.List;
import java.util.Optional;

public interface EvidenciaService {
    Evidencia saveEvidencia(Evidencia evidencia);
    Evidencia updateEstadoEvidencia(Long id, EstadoEvidencia estado);
    List<Evidencia> getAllEvidencias();
    List<Evidencia> getEvidenciasByPracticaId(Long practicaId);
    Optional<Evidencia> getEvidenciaById(Long id);
    void deleteEvidencia(Long id);
}
