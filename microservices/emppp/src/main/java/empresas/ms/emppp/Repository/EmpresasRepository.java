package empresas.ms.emppp.Repository;

import empresas.ms.emppp.Entity.EmpresasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresasRepository extends JpaRepository<EmpresasEntity, Long> {
    Optional<EmpresasEntity> findByRuc(String ruc);
}
