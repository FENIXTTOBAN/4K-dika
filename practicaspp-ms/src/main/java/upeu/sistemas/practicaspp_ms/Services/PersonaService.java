package upeu.sistemas.practicaspp_ms.Services;

import upeu.sistemas.practicaspp_ms.Entities.Persona;
import upeu.sistemas.practicaspp_ms.Entities.TipoPersona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {
    Persona guardar(Persona persona);
    Persona actualizar(Long id, Persona persona);
    List<Persona> listarTodo();
    List<Persona> listarPorTipo(TipoPersona tipoPersona);
    Optional<Persona> findById(Long id);
}
