package www.sistemaspracticas.ofertapostulacion_ms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.Oferta;
import java.util.List;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {
    List<Oferta> findByEstado(Boolean estado);
    List<Oferta> findByEmpresaId(Long empresaId);
}