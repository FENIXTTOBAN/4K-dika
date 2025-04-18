package empresas.ms.emppp.Service;

import empresas.ms.emppp.Entity.EmpresasEntity;

import java.util.List;
import java.util.Optional;

public interface EmpresasService {
    List<EmpresasEntity> listarEmpresas();
    EmpresasEntity guardarEmpresa(EmpresasEntity empresa);
    Optional<EmpresasEntity> findById(Long id);
    Optional<EmpresasEntity> findByRuc(String ruc);
    void deleteEmpresa(Long id);
}
