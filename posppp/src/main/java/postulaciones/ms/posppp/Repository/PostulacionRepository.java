package postulaciones.ms.posppp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import postulaciones.ms.posppp.Entity.Postulacion;

import java.util.List;

@Repository
public interface PostulacionRepository extends JpaRepository<Postulacion,  Long> {

    List<Postulacion> findByIdEstudiante(Long idEstudiante);
    List<Postulacion> findByIdOferta(Long idOferta);}
