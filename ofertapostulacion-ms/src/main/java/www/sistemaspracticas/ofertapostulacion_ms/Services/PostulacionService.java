package www.sistemaspracticas.ofertapostulacion_ms.Services;

import www.sistemaspracticas.ofertapostulacion_ms.Entities.EstadoPostulacion;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.Postulacion;
import java.util.List;
import java.util.Optional;

public interface PostulacionService {
    Postulacion savePostulacion(Postulacion postulacion);
    Postulacion updatePostulacion(Long id, EstadoPostulacion newEstado, String comentario);
    List<Postulacion> getAllPostulaciones();
    List<Postulacion> getPostulacionesByEstado(EstadoPostulacion estado);
    List<Postulacion> getPostulacionesByPersonaId(Long personaId);
    List<Postulacion> getPostulacionesByOfertaId(Long ofertaId);
    Optional<Postulacion> getPostulacionById(Long id);
    void deletePostulacion(Long id);

}