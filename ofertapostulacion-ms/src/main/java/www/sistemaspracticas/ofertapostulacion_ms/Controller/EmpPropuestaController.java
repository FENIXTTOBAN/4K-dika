package www.sistemaspracticas.ofertapostulacion_ms.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.Empresa;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.EmpresaPropuesta;
import www.sistemaspracticas.ofertapostulacion_ms.Services.EmpPropuestaService;


@RestController
@RequestMapping("/propuestas")
public class EmpPropuestaController {

    private final EmpPropuestaService empPropuestaService;

    public EmpPropuestaController(EmpPropuestaService empPropuestaService) {
        this.empPropuestaService = empPropuestaService;
    }

    @Operation(summary = "Actualiza si el practicador aprueba la solicitud del estudiante para su practica")
    @PutMapping("/{id}/aprobar")
    public ResponseEntity<EmpresaPropuesta> aprobar(@PathVariable Long id, @RequestBody Empresa empresa, @RequestHeader("idPracticador") Long idPracticador) {
        EmpresaPropuesta aprobada = empPropuestaService.aprobarPropuesta(id, empresa, idPracticador);
        return ResponseEntity.ok(aprobada);
    }

    @Operation(summary = "Registrar nueva propuesta de empresa por parte del estudiante")
    @PostMapping
    public ResponseEntity<EmpresaPropuesta> registrarPropuesta(@RequestBody EmpresaPropuesta propuesta) {
        EmpresaPropuesta nueva = empPropuestaService.registrarPropuesta(propuesta);
        return ResponseEntity.ok(nueva);
    }
}
