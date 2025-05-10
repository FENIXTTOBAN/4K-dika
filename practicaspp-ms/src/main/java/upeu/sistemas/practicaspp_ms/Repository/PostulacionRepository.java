package upeu.sistemas.practicaspp_ms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upeu.sistemas.practicaspp_ms.Entities.Postulacion;
import upeu.sistemas.practicaspp_ms.Entities.EstadoPostulacion;

import java.util.List;

public interface PostulacionRepository extends JpaRepository<Postulacion, Long> {

    // Buscar postulaciones por estado
    List<Postulacion> findByEstado(EstadoPostulacion estado);
    // Buscar postulaciones por persona (estudiante) usando el id de persona
    List<Postulacion> findByPersonaId(Long personaId);
    // Buscar postulaciones por oferta
    List<Postulacion> findByOfertaId(Long ofertaId);
}
