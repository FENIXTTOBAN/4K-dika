package upeu.sistemas.practicaspp_ms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upeu.sistemas.practicaspp_ms.Entities.Persona;
import upeu.sistemas.practicaspp_ms.Entities.TipoPersona;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findByTipoPersona(TipoPersona tipoPersona);
}
