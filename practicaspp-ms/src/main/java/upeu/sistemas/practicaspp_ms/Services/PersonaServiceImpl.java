package upeu.sistemas.practicaspp_ms.Services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import upeu.sistemas.practicaspp_ms.Entities.Persona;
import upeu.sistemas.practicaspp_ms.Entities.TipoPersona;
import upeu.sistemas.practicaspp_ms.Repository.PersonaRepository;

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
            actual.setDni(persona.getDni());
            actual.setEstado(persona.getEstado());
            actual.setFechaRegistro(persona.getFechaRegistro());
            actual.setNombre(persona.getNombre());
            actual.setApellido(persona.getApellido());
            actual.setTelefono(persona.getTelefono());
            return personaRepository.save(persona);
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
