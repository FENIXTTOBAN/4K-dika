package www.sistemaspracticas.ofertapostulacion_ms.Services;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.*;
import www.sistemaspracticas.ofertapostulacion_ms.Feign.EstadoPractica;
import www.sistemaspracticas.ofertapostulacion_ms.Feign.Practica;
import www.sistemaspracticas.ofertapostulacion_ms.Feign.PracticaFeign;
import www.sistemaspracticas.ofertapostulacion_ms.Repository.PostulacionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostulacionServiceImpl implements PostulacionService {

    private final PostulacionRepository postulacionRepository;
    private final PracticaFeign practicaFeign;

    public PostulacionServiceImpl(PostulacionRepository postulacionRepository, PracticaFeign practicaFeign) {
        this.postulacionRepository = postulacionRepository;
        this.practicaFeign = practicaFeign;
    }

    @Override
    public Postulacion savePostulacion(Postulacion postulacion) {
        postulacion.setEstado(EstadoPostulacion.PENDIENTE);

        if(postulacion.getComentario() == null || postulacion.getComentario().isBlank()){
            postulacion.setComentario("En espera");
        }

        if(postulacion.getFechaPostulacion() == null){
            postulacion.setFechaPostulacion(LocalDate.now());
        }

        return postulacionRepository.save(postulacion);
    }

    @Override
    public Postulacion updatePostulacion(Long id, EstadoPostulacion newEstado, String comentario) {
        return postulacionRepository.findById(id)
                .map(postulacion -> {
                    EstadoPostulacion estadoAnterior = postulacion.getEstado();

                    switch (newEstado) {
                        case PENDIENTE -> {
                            postulacion.setComentario("En espera");
                        }
                        case ACEPTADA -> {
                            if (comentario == null || comentario.isBlank()) {
                                postulacion.setComentario("¡Has sido aceptado!");
                            } else {
                                postulacion.setComentario(comentario);
                            }
                        }
                        case RECHAZADA -> {
                            if (comentario == null || comentario.isBlank()) {
                                postulacion.setComentario("Sigue intentándolo");
                            } else {
                                postulacion.setComentario(comentario);
                            }
                        }
                    }

                    postulacion.setEstado(newEstado);
                    Postulacion actualizada = postulacionRepository.save(postulacion);

                    //Logica de registrar la practica al ser aceptado
                    if (estadoAnterior != EstadoPostulacion.ACEPTADA &&
                            newEstado == EstadoPostulacion.ACEPTADA) {

                        Practica practica = new Practica();
                        practica.setIdPostulacion(actualizada.getId());
                        practica.setIdPersona(actualizada.getPersonaId());
                        practica.setEstado(EstadoPractica.PROCESO);
                        practica.setFechaInicio(LocalDate.now());

                        practicaFeign.registrar(practica);
                    }

                    return actualizada;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Postulación no encontrada con ID: " + id));
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
    public void deletePostulacion(Long id) {
        postulacionRepository.findById(id)
                .ifPresentOrElse(postulacionRepository::delete,
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulacion no encontrada con ID: " + id);
                        });
    }
}
