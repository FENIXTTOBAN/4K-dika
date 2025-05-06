package postulaciones.ms.posppp.Service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import postulaciones.ms.posppp.Entity.Estado;
import postulaciones.ms.posppp.Entity.Postulacion;
import postulaciones.ms.posppp.Repository.PostulacionRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostulacionServiceImpl implements PostulacionServices{

    private final PostulacionRepository postulacionRepository;

    public PostulacionServiceImpl(PostulacionRepository postulacionRepository) {
        this.postulacionRepository = postulacionRepository;
    }
    @Override
    public Postulacion save(Postulacion postulacion) {
        if (postulacion.getEstado() == null) {
            postulacion.setEstado(Estado.PENDIENTE);
        }
        return postulacionRepository.save(postulacion);
    }

    @Override
    public List<Postulacion> listarPostulacion() {
        return postulacionRepository.findAll();
    }

    @Override
    public Optional<Postulacion> findById(Long id) {
        return postulacionRepository.findById(id);
    }

    @Override
    public List<Postulacion> listByIdEstudiante(Long idEstudiante) {
        return postulacionRepository.findByIdEstudiante(idEstudiante);
    }

    @Override
    public List<Postulacion> listByIdOferta(Long idOferta) {
        return postulacionRepository.findByIdOferta(idOferta);
    }

//    @Override
//    public void delete(Postulacion postulacion) {
//        postulacionRepository.delete(postulacion);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        postulacionRepository.deleteById(id);
//    }

    @Override
    public Postulacion updateEstadoAndDescripcion(Long id, Estado nuevEs, String descripcion) {
        Postulacion postulacion = postulacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postulación no encontrada"));

        postulacion.setEstado(nuevEs);

        if (nuevEs == Estado.RECHAZADO || nuevEs == Estado.OBSERVACION) {
            postulacion.setDescripcion(descripcion);
        } else {
            postulacion.setDescripcion(null);
        }

        return postulacionRepository.save(postulacion);
    }
}