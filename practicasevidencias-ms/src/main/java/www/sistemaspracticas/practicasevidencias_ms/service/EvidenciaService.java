package www.sistemaspracticas.practicasevidencias_ms.service;

import www.sistemaspracticas.practicasevidencias_ms.entities.Evidencia;

import java.util.List;


public interface EvidenciaService {
    Evidencia create(Evidencia evidencia);
    Evidencia findById(Long id);
    Evidencia update(Evidencia evidencia, Long id);
    void delete(Long id);
    List<Evidencia> getAll();
    List<Evidencia> getByPracticaId(Long idPractica);
}
