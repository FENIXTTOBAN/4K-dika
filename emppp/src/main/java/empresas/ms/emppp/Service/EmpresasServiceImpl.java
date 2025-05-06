package empresas.ms.emppp.Service;

import empresas.ms.emppp.Entity.Empresas;
import empresas.ms.emppp.Entity.EstadoEm;
import empresas.ms.emppp.Repository.EmpresasRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Empresas save(Empresas empresa) {
        if (empresa.getFechaCreacion() == null) {
            empresa.setFechaCreacion(LocalDate.now());
        }
        if (empresa.getEstado() == null) {
            empresa.setEstado(EstadoEm.VIGENTE);
        }
        validarRuc(empresa.getRuc());
        return empresasRepository.save(empresa);
    }

    @Override
    public Empresas edit(Empresas empresas) {
        validarRuc(empresas.getRuc());
        return empresasRepository.save(empresas);
    }

    @Override
    public List<Empresas> listarEmpresas() {
        return empresasRepository.findAll();
    }

    @Override
    public Optional<Empresas> findById(Long id) {
        return empresasRepository.findById(id);
    }

    @Override
    public Optional<Empresas> findByRuc(String Ruc) {
        return empresasRepository.findByRuc(Ruc);
    }

    @Override
    public boolean existsByRuc(String Ruc) {
        return empresasRepository.existsByRuc(Ruc);
    }

    // MÉTODO PRIVADO DE VALIDACIÓN
    private void validarRuc(String ruc) {
        if (!ruc.matches("^(10|20)\\d{9}$")) {
            throw new IllegalArgumentException("El RUC debe iniciar con 10 o 20 y tener exactamente 11 dígitos.");
        }
    }
}
