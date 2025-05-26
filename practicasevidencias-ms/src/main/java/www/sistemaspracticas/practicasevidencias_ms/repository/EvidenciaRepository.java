package www.sistemaspracticas.practicasevidencias_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import www.sistemaspracticas.practicasevidencias_ms.entities.Evidencia;

import java.util.List;

public interface EvidenciaRepository extends JpaRepository<Evidencia, Long> {
    List<Evidencia> findByIdPractica(Long idPractica);
}
