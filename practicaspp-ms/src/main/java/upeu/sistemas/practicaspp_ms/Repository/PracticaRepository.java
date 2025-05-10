package upeu.sistemas.practicaspp_ms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upeu.sistemas.practicaspp_ms.Entities.Practica;
import upeu.sistemas.practicaspp_ms.Entities.EstadoPractica;

import java.util.List;

public interface PracticaRepository extends JpaRepository<Practica, Long> {

    // Buscar prácticas por estado
    List<Practica> findByEstado(EstadoPractica estado);
    // Buscar prácticas por postulación (id de postulación)
    List<Practica> findByPostulacionId(Long postulacionId);
    // Buscar prácticas por persona (estudiante)
    List<Practica> findByPersonaId(Long personaId);
}