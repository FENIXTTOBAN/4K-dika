package www.sistemaspracticas.personas_ms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import www.sistemaspracticas.personas_ms.entities.Persona;
import www.sistemaspracticas.personas_ms.entities.TipoPersona;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findByTipoPersona(TipoPersona tipoPersona);

}


