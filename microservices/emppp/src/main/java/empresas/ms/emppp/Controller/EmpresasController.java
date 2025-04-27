package empresas.ms.emppp.Controller;

import empresas.ms.emppp.Entity.Empresas;
import empresas.ms.emppp.Service.EmpresasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/empresas")
@Tag(name = "Empresas Resources", description = "CRUD de Empresas registradas para prácticas preprofesionales")
public class EmpresasController {

    private final EmpresasService empresasService;
    private static final Logger log = LoggerFactory.getLogger(EmpresasController.class);

    public EmpresasController(EmpresasService empresasService) {
        this.empresasService = empresasService;
    }

    @Operation(summary = "Registrar una nueva empresa")
    @PostMapping
    public ResponseEntity<Empresas> save(@RequestBody Empresas empresa) {
        log.info("Registrar nueva empresa");
        Empresas nuev = empresasService.save(empresa);
        return ResponseEntity.created(URI.create("/empresas/" + nuev.getId())).body(nuev);
    }

    @Operation(summary = "Editar una empresa existente")
    @PutMapping
    public ResponseEntity<Empresas> edit(@RequestBody Empresas empresa) {
        log.info("Editar empresa con id {}", empresa.getId());
        Empresas empresaActualizada = empresasService.edit(empresa);
        return ResponseEntity.ok(empresaActualizada);
    }

    @Operation(summary = "Listar todas las empresas registradas")
    @GetMapping
    public ResponseEntity<List<Empresas>> listar() {
        log.info("Listar todas las empresas");
        return ResponseEntity.ok(empresasService.listarEmpresas());
    }

    @Operation(summary = "Buscar una empresa por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Empresas> findById(@PathVariable Long id) {
        log.info("Buscar empresa con id {}", id);
        return empresasService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar una empresa por RUC")
    @GetMapping("/ruc/{ruc}")
    public ResponseEntity<Empresas> findByRuc(@PathVariable String ruc) {
        log.info("Buscar empresa con RUC {}", ruc);
        return empresasService.findByRuc(ruc)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
