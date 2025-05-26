package www.sistemaspracticas.practicasevidencias_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import www.sistemaspracticas.practicasevidencias_ms.entities.Practica;

import java.util.Optional;

public interface PracticaRepository extends JpaRepository<Practica, Long> {
    Optional<Practica> findByIdPostulacion(Long idPostulacion);
}
