package www.sistemaspracticas.ofertapostulacion_ms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.EstadoPostulacion;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.Postulacion;
import java.util.List;

public interface PostulacionRepository extends JpaRepository<Postulacion, Long> {
    List<Postulacion> findByEstado(EstadoPostulacion estado);
    List<Postulacion> findByPersonaId(Long personaId);
    List<Postulacion> findByOfertaId(Long ofertaId);
    List<Postulacion> findByOfertaIdAndEstado(Long ofertaId, EstadoPostulacion estado);
}