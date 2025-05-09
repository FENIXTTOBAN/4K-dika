package upeu.sistemas.practicaspp_ms.Services;

import upeu.sistemas.practicaspp_ms.Entities.Empresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaService {
    Empresa guardar(Empresa empresa);
    Empresa update(Long id, Boolean estado);
    List<Empresa> listarTodo();
    List<Empresa> listarPorEstado(Boolean estado);
    Optional<Empresa> findById(Long id);
}
