package www.sistemaspracticas.ofertapostulacion_ms.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.Empresa;
import www.sistemaspracticas.ofertapostulacion_ms.Services.EmpresaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/empresas")
@Tag(name = "Empresa Resource", description = "CRUD de Empresas y validación de propuestas")
public class EmpresaController {

    private final EmpresaService empresaService;
    private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @Operation(summary = "Registrar una nueva empresa")
    @PostMapping
    public ResponseEntity<Empresa> registrar(@RequestBody Empresa empresa) {
        log.info("Registrando empresa: {}", empresa.getNombre());
        Empresa nueva = empresaService.guardar(empresa);
        return ResponseEntity.created(URI.create("/empresas/" + nueva.getId())).body(nueva);
    }

    @Operation(summary = "Actualizar estado de una empresa (por ID)")
    @PutMapping("/{id}/estado")
    public ResponseEntity<Empresa> update(@PathVariable Long id, @RequestParam Boolean estado) {
        log.info("Cambiando estado de empresa ID {} a {}", id, estado);
        Empresa actualizada = empresaService.update(id, estado);
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "Listar todas las empresas")
    @GetMapping
    public ResponseEntity<List<Empresa>> listarTodas() {
        return ResponseEntity.ok(empresaService.listarTodo());
    }

    @Operation(summary = "Listar empresas por estado")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Empresa>> listarPorEstado(@PathVariable Boolean estado) {
        return ResponseEntity.ok(empresaService.listarPorEstado(estado));
    }

    @Operation(summary = "Buscar una empresa por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarPorId(@PathVariable Long id) {
        return empresaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}