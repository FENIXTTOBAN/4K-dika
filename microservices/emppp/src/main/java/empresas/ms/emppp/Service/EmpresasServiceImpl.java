package empresas.ms.emppp.Service;

import empresas.ms.emppp.Entity.EmpresasEntity;
import empresas.ms.emppp.Repository.EmpresasRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpresasServiceImpl implements EmpresasService {

    private final EmpresasRepository empresasRepository;

    public EmpresasServiceImpl(EmpresasRepository empresasRepository) {
        this.empresasRepository = empresasRepository;
    }

    @Override
    public List<EmpresasEntity> listarEmpresas() {
        return empresasRepository.findAll();
    }

    @Override
    public EmpresasEntity guardarEmpresa(EmpresasEntity empresa) {
        return empresasRepository.save(empresa);
    }

    @Override
    public Optional<EmpresasEntity> findById(Long id) {
        return empresasRepository.findById(id);
    }

    @Override
    public Optional<EmpresasEntity> findByRuc(String ruc) {
        return empresasRepository.findByRuc(ruc);
    }

    @Override
    public void deleteEmpresa(Long id) {
        EmpresasEntity empresa = empresasRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("No existe la empresa con el id " + id));
        empresasRepository.delete(empresa);
    }
}
