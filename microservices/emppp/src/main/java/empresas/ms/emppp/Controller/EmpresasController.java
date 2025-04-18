package empresas.ms.emppp.Controller;

import empresas.ms.emppp.Entity.EmpresasEntity;
import empresas.ms.emppp.Service.EmpresasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "empresas")
@Tag(name = "Empresas Resources")
public class EmpresasController {

    private final EmpresasService empresasService;
    private static final Logger log = LoggerFactory.getLogger(EmpresasController.class);

    public EmpresasController(EmpresasService empresasService) {
        this.empresasService = empresasService;
    }

    @Operation(summary = "Obtener todas las empresas")
    @GetMapping
    public ResponseEntity<List<EmpresasEntity>> getAll() {
        log.info("Obtener todas las empresas");
        return ResponseEntity.ok(empresasService.listarEmpresas());
    }

    @Operation(summary = "Buscar una empresa por su ID")
    @GetMapping(path = "{id}")
    public ResponseEntity<EmpresasEntity> getById(@PathVariable Long id) {
        log.info("Obtener un empresa con el id " + id);
        return empresasService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar una empresa por su RUC")
    @GetMapping(path = "ruc/{ruc}")
    public ResponseEntity<EmpresasEntity> getByRuc(@PathVariable String ruc) {
        log.info("Obtener un empresa con el RUC " + ruc);
        return empresasService.findByRuc(ruc)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar una nueva empresa")
    @PostMapping
    public ResponseEntity<EmpresasEntity> create(@RequestBody EmpresasEntity empresa) {
        log.info("Registrar una nueva empresa");
        EmpresasEntity nuevo = empresasService.guardarEmpresa(empresa);
        return ResponseEntity.created(URI.create("/empresas/" + nuevo.getId())).build();
    }

    @Operation(summary = "Eliminar una empresa por ID")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Eliminar una empresa con ID {}", id);
        empresasService.deleteEmpresa(id);
        return ResponseEntity.noContent().build();
    }
}
