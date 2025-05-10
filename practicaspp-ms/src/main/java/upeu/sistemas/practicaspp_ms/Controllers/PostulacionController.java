package upeu.sistemas.practicaspp_ms.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.sistemas.practicaspp_ms.Entities.Postulacion;
import upeu.sistemas.practicaspp_ms.Entities.EstadoPostulacion;
import upeu.sistemas.practicaspp_ms.Services.PostulacionService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/postulaciones")
@Tag(name = "Postulacion Resource", description = "CRUD de Postulaciones de prácticas")
public class PostulacionController {

    private final PostulacionService postulacionService;
    private static final Logger log = LoggerFactory.getLogger(PostulacionController.class);

    public PostulacionController(PostulacionService postulacionService) {
        this.postulacionService = postulacionService;
    }

    @Operation(summary = "Registrar nueva postulación")
    @PostMapping
    public ResponseEntity<Postulacion> registrar(@RequestBody Postulacion postulacion) {
        log.info("Registrando nueva postulación de: {}", postulacion.getPersona().getNombre());
        Postulacion nueva = postulacionService.savePostulacion(postulacion);
        return ResponseEntity.created(URI.create("/postulaciones/" + nueva.getId())).body(nueva);
    }

    @Operation(summary = "Actualizar estado de una postulación por ID")
    @PutMapping("/{id}/estado")
    public ResponseEntity<Postulacion> update(@PathVariable Long id, @RequestParam EstadoPostulacion estado) {
        log.info("Actualizando estado de postulación ID {} a {}", id, estado);
        Postulacion actualizada = postulacionService.updatePostulacion(id, estado);
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "Listar todas las postulaciones")
    @GetMapping
    public ResponseEntity<List<Postulacion>> listarTodo() {
        return ResponseEntity.ok(postulacionService.getAllPostulaciones());
    }

    @Operation(summary = "Listar postulaciones por estado")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Postulacion>> listarPorEstado(@PathVariable EstadoPostulacion estado) {
        return ResponseEntity.ok(postulacionService.getPostulacionesByEstado(estado));
    }

    @Operation(summary = "Buscar postulación por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Postulacion> buscarPorId(@PathVariable Long id) {
        return postulacionService.getPostulacionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar postulaciones por ID de oferta")
    @GetMapping("/oferta/{ofertaId}")
    public ResponseEntity<List<Postulacion>> listarPorOfertaId(@PathVariable Long ofertaId) {
        return ResponseEntity.ok(postulacionService.getPostulacionesByOfertaId(ofertaId));
    }

    @Operation(summary = "Eliminar postulación por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postulacionService.deletePostulacion(id);
        return ResponseEntity.noContent().build();
    }
}