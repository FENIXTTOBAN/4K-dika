package www.sistemaspracticas.ofertapostulacion_ms.Services;

import www.sistemaspracticas.ofertapostulacion_ms.Entities.Empresa;
import java.util.*;

public interface EmpresaService {
    Empresa guardar(Empresa empresa);
    Empresa update(Long id, Boolean estado);
    List<Empresa> listarTodo();
    List<Empresa> listarPorEstado(Boolean estado);
    Optional<Empresa> findById(Long id);
}
