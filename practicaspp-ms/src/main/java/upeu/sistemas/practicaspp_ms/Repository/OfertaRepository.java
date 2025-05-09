package upeu.sistemas.practicaspp_ms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upeu.sistemas.practicaspp_ms.Entities.Oferta;

import java.util.List;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {
    List<Oferta> findByEstado(Boolean estado);
}
