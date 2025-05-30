package www.sistemaspracticas.personas_ms.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import www.sistemaspracticas.personas_ms.entities.Persona;
import www.sistemaspracticas.personas_ms.entities.TipoPersona;
import www.sistemaspracticas.personas_ms.repository.PersonaRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona guardar(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public Persona actualizar(Long id, Persona persona) {
        return personaRepository.findById(id).map(actual -> {
            actual.setNombres(persona.getNombres());
            actual.setApellidos(persona.getApellidos());
            actual.setDni(persona.getDni());
            actual.setTelefono(persona.getTelefono());
            actual.setEstado(persona.getEstado());
            actual.setTipoPersona(persona.getTipoPersona());
            return personaRepository.save(actual);
        }).orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + id));
    }

    @Override
    public List<Persona> listarTodo() {
        return personaRepository.findAll();
    }

    @Override
    public List<Persona> listarPorTipo(TipoPersona tipoPersona) {
        return personaRepository.findByTipoPersona(tipoPersona);
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return personaRepository.findById(id);
    }
}
