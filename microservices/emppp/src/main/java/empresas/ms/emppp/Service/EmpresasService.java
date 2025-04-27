package empresas.ms.emppp.Service;

import empresas.ms.emppp.Entity.Empresas;

import java.util.List;
import java.util.Optional;

public interface EmpresasService {

    Empresas save(Empresas empresas);
    Empresas edit(Empresas empresas);
    List<Empresas> listarEmpresas();
    Optional<Empresas> findById(Long id);
    Optional<Empresas> findByRuc(String Ruc);
    boolean existsByRuc(String Ruc);
}
