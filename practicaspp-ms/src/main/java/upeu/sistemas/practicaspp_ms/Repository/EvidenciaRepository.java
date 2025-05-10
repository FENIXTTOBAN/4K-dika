package upeu.sistemas.practicaspp_ms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upeu.sistemas.practicaspp_ms.Entities.Evidencia;
import upeu.sistemas.practicaspp_ms.Entities.EstadoEvidencia;

import java.util.List;

public interface EvidenciaRepository extends JpaRepository<Evidencia, Long> {

    // Buscar evidencias por estado
    List<Evidencia> findByEstado(EstadoEvidencia estado);
    // Buscar evidencias por práctica (id de práctica)
    List<Evidencia> findByPracticaId(Long practicaId);
}