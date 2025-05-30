package www.sistemaspracticas.personas_ms.services;

import www.sistemaspracticas.personas_ms.entities.Persona;
import www.sistemaspracticas.personas_ms.entities.Persona;
import www.sistemaspracticas.personas_ms.entities.TipoPersona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {

    Persona guardar(Persona persona);

    Persona actualizar(Long id, Persona persona);

    List<Persona> listarTodo();

    List<Persona> listarPorTipo(TipoPersona tipoPersona); // ✔ asegurado

    Optional<Persona> findById(Long id);
}



