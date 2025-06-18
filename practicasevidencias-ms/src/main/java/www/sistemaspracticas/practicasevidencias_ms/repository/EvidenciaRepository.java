package www.sistemaspracticas.practicasevidencias_ms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import www.sistemaspracticas.practicasevidencias_ms.entities.Evidencia;

import java.util.List;

public interface EvidenciaRepository extends JpaRepository<Evidencia, Long> {
    @Query("SELECT e FROM Evidencia e WHERE e.idPractica = :idPractica ORDER BY e.fechaSubida DESC")
    List<Evidencia> findAllByPracticaOrderByFechaDesc(@Param("idPractica") Long idPractica);
}
