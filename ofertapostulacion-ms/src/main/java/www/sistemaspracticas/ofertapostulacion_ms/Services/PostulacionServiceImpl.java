package www.sistemaspracticas.ofertapostulacion_ms.Services;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.*;
import www.sistemaspracticas.ofertapostulacion_ms.Repository.PostulacionRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostulacionServiceImpl implements PostulacionService {

    private final PostulacionRepository postulacionRepository;
    private final VacantesService vacantesService;

    public PostulacionServiceImpl(PostulacionRepository postulacionRepository, VacantesService vacantesService) {
        this.postulacionRepository = postulacionRepository;
        this.vacantesService = vacantesService;
    }

    @Override
    public Postulacion savePostulacion(Postulacion postulacion) {
        // En el futuro: podrías comunicarte con el microservicio de prácticas aquí
        // para registrar automáticamente una práctica si el estado es ACEPTADA.
        // Esto puede hacerse mediante una llamada REST (RestTemplate, Feign, etc.)

        /*
        if (postulacion.getEstado() == EstadoPostulacion.ACEPTADA) {
            PracticaDTO practica = new PracticaDTO();
            practica.setIdPersona(postulacion.getIdPersona());
            practica.setIdPostulacion(postulacion.getId());
            practica.setEstado("EN_PROCESO");

            restTemplate.postForObject(
                "http://practicasevidencias-ms/api/practicas",
                practica,
                PracticaDTO.class
            );
        }
        */
        return postulacionRepository.save(postulacion);
    }

    @Override
    public Postulacion updatePostulacion(Long id, EstadoPostulacion estado) {
        return postulacionRepository.findById(id)
                .map(postulacion -> {
                    EstadoPostulacion anterior = postulacion.getEstado();
                    postulacion.setEstado(estado);
                    Postulacion actualizada = postulacionRepository.save(postulacion);

                    //se acepta y antes no estaba aceptada, descontamos vacante
                    if (estado == EstadoPostulacion.ACEPTADA && anterior != EstadoPostulacion.ACEPTADA) {
                        Long ofertaId = actualizada.getOferta().getId();
                        vacantesService.findByOfertaId(ofertaId).ifPresent(v -> {
                            vacantesService.updateVacantes(v.getId(), v.getOcupados() + 1);
                        });
                    }

                    // En el futuro: creación de práctica si se acepta
                /*
                if (estado == EstadoPostulacion.ACEPTADA && anterior != EstadoPostulacion.ACEPTADA) {
                    PracticaDTO practica = new PracticaDTO();
                    practica.setIdPersona(actualizada.getIdPersona());
                    practica.setIdPostulacion(actualizada.getId());
                    practica.setEstado("EN_PROCESO");

                    restTemplate.postForObject(
                        "http://practicasevidencias-ms/api/practicas",
                        practica,
                        PracticaDTO.class
                    );
                }
                */

                    return actualizada;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulacion no encontrada con ID: " + id));
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
    public List<Postulacion> getPostulacionesByOfertaAndEstado(Long ofertaId, EstadoPostulacion estado) {
        return postulacionRepository.findByOfertaIdAndEstado(ofertaId, estado);
    }

    @Override
    public Optional<Postulacion> getPostulacionById(Long id) {
        return postulacionRepository.findById(id);
    }

    @Override
    public void deletePostulacion(Long id) {
        postulacionRepository.findById(id)
                .ifPresentOrElse(postulacionRepository::delete,
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulacion no encontrada con ID: " + id);
                        });
    }
}
