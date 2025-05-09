package upeu.sistemas.practicaspp_ms.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.sistemas.practicaspp_ms.Entities.Vacantes;
import upeu.sistemas.practicaspp_ms.Services.VacantesService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vacantes")
@Tag(name = "Vacantes Resource", description = "Gestión de cupos disponibles para cada oferta")
public class VacantesController {

    private final VacantesService vacantesService;
    private static final Logger log = LoggerFactory.getLogger(VacantesController.class);

    public VacantesController(VacantesService vacantesService) {
        this.vacantesService = vacantesService;
    }

    @Operation(summary = "Registrar cupos para una oferta")
    @PostMapping
    public ResponseEntity<Vacantes> registrar(@RequestBody Vacantes vacantes) {
        log.info("Registrando vacantes para oferta ID: {}",
                vacantes.getOferta() != null ? vacantes.getOferta().getId() : "desconocida");
        Vacantes nuevo = vacantesService.guardar(vacantes);
        return ResponseEntity.created(URI.create("/vacantes/" + nuevo.getId())).body(nuevo);
    }

    @Operation(summary = "Actualizar cantidad de ocupados (y recalcular disponibles)")
    @PutMapping("/{id}/ocupados")
    public ResponseEntity<Vacantes> actualizarCupos(
            @PathVariable Long id,
            @RequestParam int ocupados) {
        log.info("Actualizando cupos ocupados de vacante ID {} a {}", id, ocupados);
        Vacantes actualizada = vacantesService.updateCupos(id, ocupados);
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "Buscar vacantes por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Vacantes> buscarPorId(@PathVariable Long id) {
        return vacantesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar vacantes por ID de oferta")
    @GetMapping("/oferta/{ofertaId}")
    public ResponseEntity<Vacantes> buscarPorOferta(@PathVariable Long ofertaId) {
        return vacantesService.findByOfertaId(ofertaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar todas las vacantes")
    @GetMapping
    public ResponseEntity<List<Vacantes>> listarTodo() {
        return ResponseEntity.ok(vacantesService.listarTodo());
    }
}
