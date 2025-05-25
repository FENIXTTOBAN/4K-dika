package www.sistemaspracticas.ofertapostulacion_ms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.Empresa;
import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    List<Empresa> findByEstado(Boolean estado);
}