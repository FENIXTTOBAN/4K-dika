package ofertas.ms.oferppp.repository;

import ofertas.ms.oferppp.entity.CupoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CupoRepository extends JpaRepository<CupoEntity, Long> {
    Optional<CupoEntity> findByOferta_Id(Long idOferta);
}