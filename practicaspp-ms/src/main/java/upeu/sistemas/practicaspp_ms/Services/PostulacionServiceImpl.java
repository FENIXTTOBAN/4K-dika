package upeu.sistemas.practicaspp_ms.Services;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import upeu.sistemas.practicaspp_ms.Entities.EstadoPractica;
import upeu.sistemas.practicaspp_ms.Entities.Postulacion;
import upeu.sistemas.practicaspp_ms.Entities.EstadoPostulacion;
import upeu.sistemas.practicaspp_ms.Entities.Practica;
import upeu.sistemas.practicaspp_ms.Repository.PostulacionRepository;
import upeu.sistemas.practicaspp_ms.Repository.PracticaRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostulacionServiceImpl implements PostulacionService {

    private final PostulacionRepository postulacionRepository;
    private final PracticaRepository practicaRepository;

    public PostulacionServiceImpl(PostulacionRepository postulacionRepository, PracticaRepository practicaRepository) {
        this.postulacionRepository = postulacionRepository;
        this.practicaRepository = practicaRepository;
    }

    @Override
    public Postulacion savePostulacion(Postulacion postulacion) {
        if (postulacion.getEstado() == EstadoPostulacion.ACEPTADA) {
            Practica practica = new Practica();
            practica.setPostulacion(postulacion);
            practica.setPersona(postulacion.getPersona());
            practica.setEstado(EstadoPractica.EN_PROCESO);
            practicaRepository.save(practica);
        }
        return postulacionRepository.save(postulacion);
    }

    @Override
    public List<Postulacion> getAllPostulaciones() {
        return postulacionRepository.findAll();
    }

    @Override
    public List<Postulacion> getPostulacionesByEstado(EstadoPostulacion estado) {
        return postulacionRepository.findByEstado(estado);
    }

    @Override
    public List<Postulacion> getPostulacionesByPersonaId(Long personaId) {
        return postulacionRepository.findByPersonaId(personaId);
    }

    @Override
    public List<Postulacion> getPostulacionesByOfertaId(Long ofertaId) {
        return postulacionRepository.findByOfertaId(ofertaId);
    }

    @Override
    public Optional<Postulacion> getPostulacionById(Long id) {
        return postulacionRepository.findById(id);
    }

    @Override
    public Postulacion updatePostulacion(Long id, EstadoPostulacion estado) {
        return postulacionRepository.findById(id)
                .map(existingPostulacion -> {
                    EstadoPostulacion estadoAnterior = existingPostulacion.getEstado();

                    existingPostulacion.setEstado(estado);

                    Postulacion updatedPostulacion = postulacionRepository.save(existingPostulacion);

                    if (estado == EstadoPostulacion.ACEPTADA && estadoAnterior != EstadoPostulacion.ACEPTADA) {
                        Practica practica = new Practica();
                        practica.setPostulacion(updatedPostulacion);
                        practica.setPersona(updatedPostulacion.getPersona());
                        practica.setEstado(EstadoPractica.EN_PROCESO);
                        practicaRepository.save(practica);
                    }

                    return updatedPostulacion;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulacion no encontrada con ID: " + id));
    }
    @Override
    public void deletePostulacion(Long id) {
        postulacionRepository.findById(id)
                .ifPresentOrElse(postulacionRepository::delete,
                        () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulacion no encontrada con ID: " + id); });
    }
}