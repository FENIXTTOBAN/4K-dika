package upeu.sistemas.practicaspp_ms.Services;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import upeu.sistemas.practicaspp_ms.Entities.EstadoPostulacion;
import upeu.sistemas.practicaspp_ms.Entities.EstadoPractica;
import upeu.sistemas.practicaspp_ms.Entities.Practica;
import upeu.sistemas.practicaspp_ms.Repository.PracticaRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PracticaServiceImpl implements PracticaService {

    private final PracticaRepository practicaRepository;

    public PracticaServiceImpl(PracticaRepository practicaRepository) {
        this.practicaRepository = practicaRepository;
    }

    @Override
    public Practica savePractica(Practica practica) {

        if (practica.getPostulacion().getEstado() == EstadoPostulacion.ACEPTADA) {
            if (practica.getEstado() == null) {
                practica.setEstado(EstadoPractica.EN_PROCESO);
            }
            return practicaRepository.save(practica);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La práctica solo puede ser creada si la postulación está aceptada.");
        }
    }

    @Override
    public List<Practica> getAllPracticas() {
        return practicaRepository.findAll();
    }

    @Override
    public List<Practica> getPracticasByEstado(EstadoPractica estado) {
        return practicaRepository.findByEstado(estado);
    }

    @Override
    public List<Practica> getPracticasByPostulacionId(Long postulacionId) {
        return practicaRepository.findByPostulacionId(postulacionId);
    }

    @Override
    public List<Practica> getPracticasByPersonaId(Long personaId) {
        return practicaRepository.findByPersonaId(personaId);
    }

    @Override
    public Optional<Practica> getPracticaById(Long id) {
        return practicaRepository.findById(id);
    }

    @Override
    public Practica updatePractica(Long id, EstadoPractica estado) {
        return practicaRepository.findById(id)
                .map(existingPractica -> {
                    existingPractica.setEstado(estado);

                    if (estado == EstadoPractica.EN_PROCESO && existingPractica.getEstado() != EstadoPractica.EN_PROCESO) {
                        existingPractica.setEstado(EstadoPractica.EN_PROCESO);
                    } else if (estado == EstadoPractica.FINALIZADA) {
                        existingPractica.setEstado(EstadoPractica.FINALIZADA);
                    } else {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede cambiar el estado a ese valor.");
                    }

                    return practicaRepository.save(existingPractica);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Practica no encontrada con ID: " + id));
    }

    @Override
    public void deletePractica(Long id) {
        practicaRepository.findById(id)
                .ifPresentOrElse(practicaRepository::delete,
                        () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Practica no encontrada con ID: " + id); });
    }
}
