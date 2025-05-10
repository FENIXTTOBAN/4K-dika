package upeu.sistemas.practicaspp_ms.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.sistemas.practicaspp_ms.Entities.Practica;
import upeu.sistemas.practicaspp_ms.Entities.EstadoPractica;
import upeu.sistemas.practicaspp_ms.Services.PracticaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/practicas")
@Tag(name = "Practica Resource", description = "CRUD de Prácticas de prácticas")
public class PracticaController {

    private final PracticaService practicaService;
    private static final Logger log = LoggerFactory.getLogger(PracticaController.class);

    public PracticaController(PracticaService practicaService) {
        this.practicaService = practicaService;
    }

    @Operation(summary = "Registrar nueva práctica")
    @PostMapping
    public ResponseEntity<Practica> registrar(@RequestBody Practica practica) {
        log.info("Registrando nueva práctica para la postulación ID: {}", practica.getPostulacion().getId());
        Practica nueva = practicaService.savePractica(practica);
        return ResponseEntity.created(URI.create("/practicas/" + nueva.getId())).body(nueva);
    }

    @Operation(summary = "Actualizar estado de una práctica por ID")
    @PutMapping("/{id}/estado")
    public ResponseEntity<Practica> update(@PathVariable Long id, @RequestParam EstadoPractica estado) {
        log.info("Actualizando estado de práctica ID {} a {}", id, estado);
        Practica actualizada = practicaService.updatePractica(id, estado);
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "Listar todas las prácticas")
    @GetMapping
    public ResponseEntity<List<Practica>> listarTodo() {
        return ResponseEntity.ok(practicaService.getAllPracticas());
    }

    @Operation(summary = "Listar prácticas por estado")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Practica>> listarPorEstado(@PathVariable EstadoPractica estado) {
        return ResponseEntity.ok(practicaService.getPracticasByEstado(estado));
    }

    @Operation(summary = "Buscar práctica por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Practica> buscarPorId(@PathVariable Long id) {
        return practicaService.getPracticaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar práctica por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        practicaService.deletePractica(id);
        return ResponseEntity.noContent().build();
    }
}
