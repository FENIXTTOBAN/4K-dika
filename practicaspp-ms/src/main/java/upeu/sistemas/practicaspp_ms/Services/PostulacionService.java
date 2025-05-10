package upeu.sistemas.practicaspp_ms.Services;


import upeu.sistemas.practicaspp_ms.Entities.Postulacion;
import upeu.sistemas.practicaspp_ms.Entities.EstadoPostulacion;

import java.util.List;
import java.util.Optional;

public interface PostulacionService {

    Postulacion savePostulacion(Postulacion postulacion);
    Postulacion updatePostulacion(Long id, EstadoPostulacion estado);
    List<Postulacion> getAllPostulaciones();
    List<Postulacion> getPostulacionesByEstado(EstadoPostulacion estado);
    List<Postulacion> getPostulacionesByPersonaId(Long personaId);
    List<Postulacion> getPostulacionesByOfertaId(Long ofertaId);
    Optional<Postulacion> getPostulacionById(Long id);
    void deletePostulacion(Long id);
}