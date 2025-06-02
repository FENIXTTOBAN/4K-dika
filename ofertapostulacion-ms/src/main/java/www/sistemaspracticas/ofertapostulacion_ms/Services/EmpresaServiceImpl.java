package www.sistemaspracticas.ofertapostulacion_ms.Services;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.Empresa;
import www.sistemaspracticas.ofertapostulacion_ms.Repository.EmpresaRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Override
    public Empresa guardar(Empresa empresa) {
        String ruc = empresa.getRuc();
        if (!ruc.matches("^(10|20)\\d{9}$")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El RUC debe empezar con 10 o 20 y tener 11 dígitos numéricos."
            );
        }
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa update(Long id, Boolean estado) {
        return empresaRepository.findById(id)
                .map(empresa -> {
                    empresa.setEstado(estado);
                    return empresaRepository.save(empresa);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa no encontrada con ID: " + id));
    }

    @Override
    public List<Empresa> listarTodo() {
        return empresaRepository.findAll();
    }

    @Override
    public List<Empresa> listarPorEstado(Boolean estado) {
        return empresaRepository.findByEstado(estado);
    }

    @Override
    public Optional<Empresa> findById(Long id) {
        return empresaRepository.findById(id);
    }
}