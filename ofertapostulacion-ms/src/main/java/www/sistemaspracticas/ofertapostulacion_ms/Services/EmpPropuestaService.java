package www.sistemaspracticas.ofertapostulacion_ms.Services;

import www.sistemaspracticas.ofertapostulacion_ms.Entities.Empresa;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.EmpresaPropuesta;

public interface EmpPropuestaService {
    EmpresaPropuesta aprobarPropuesta(Long idPropuesta, Empresa datosEmpresa, Long idPracticador);
    EmpresaPropuesta registrarPropuesta(EmpresaPropuesta propuesta);
}
