package postulaciones.ms.posppp.Service;

import postulaciones.ms.posppp.Entity.Estado;
import postulaciones.ms.posppp.Entity.Postulacion;

import java.util.List;
import java.util.Optional;

public interface PostulacionServices {

    Postulacion save(Postulacion postulacion);
    List<Postulacion> listarPostulacion();
    Optional<Postulacion> findById(Long id);
    List<Postulacion> listByIdEstudiante(Long idEstudiante);
    List<Postulacion> listByIdOferta(Long idOferta);
//    void delete(Postulacion postulacion);
//    void deleteById(Long id);
    Postulacion updateEstadoAndDescripcion(Long id, Estado nuevEs, String descripcion);
}