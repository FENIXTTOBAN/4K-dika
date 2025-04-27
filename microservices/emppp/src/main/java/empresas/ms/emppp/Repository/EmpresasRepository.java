package empresas.ms.emppp.Repository;


import empresas.ms.emppp.Entity.Empresas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresasRepository extends JpaRepository<Empresas, Long> {

    Optional<Empresas> findByRuc(String Ruc);
    boolean existsByRuc(String Ruc);
}
