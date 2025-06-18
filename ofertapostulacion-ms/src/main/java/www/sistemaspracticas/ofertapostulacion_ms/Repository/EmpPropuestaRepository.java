package www.sistemaspracticas.ofertapostulacion_ms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.EmpresaPropuesta;

public interface EmpPropuestaRepository extends JpaRepository<EmpresaPropuesta, Long> {
}
