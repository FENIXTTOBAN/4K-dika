package postulaciones.ms.posppp.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import postulaciones.ms.posppp.Entity.Estado;
import postulaciones.ms.posppp.Entity.Postulacion;
import postulaciones.ms.posppp.Service.PostulacionServices;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/postulaciones")
@Tag(name = "Postulaciones Resources")
public class PostulacionController {

    private final PostulacionServices postulacionServices;
    private static final Logger log = LoggerFactory.getLogger(PostulacionController.class);

    public PostulacionController(PostulacionServices postulacionServices) {
        this.postulacionServices = postulacionServices;
    }

    @Operation(summary = "Registrar una nueva postulación")
    @PostMapping
    public ResponseEntity<Postulacion> save(@RequestBody Postulacion postulacion) {
        log.info("Registrar nueva postulación");
        Postulacion nuev = postulacionServices.save(postulacion);
        return ResponseEntity.created(URI.create("/postulaciones/" + nuev.getId())).body(nuev);
    }

    @Operation(summary = "Listar todas las postulaciones")
    @GetMapping
    public ResponseEntity<List<Postulacion>> listar() {
        log.info("Listar todas las postulaciones");
        return ResponseEntity.ok(postulacionServices.listarPostulacion());
    }

    @Operation(summary = "Buscar postulación por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Postulacion> findById(@PathVariable Long id) {
        log.info("Buscar postulación con id {}", id);
        return postulacionServices.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar postulaciones por ID de estudiante")
    @GetMapping("/estudiante/{idEstudiante}")
    public ResponseEntity<List<Postulacion>> listByEstudiante(@PathVariable Long idEstudiante) {
        log.info("Listar postulaciones por idEstudiante {}", idEstudiante);
        return ResponseEntity.ok(postulacionServices.listByIdEstudiante(idEstudiante));
    }

    @Operation(summary = "Listar postulaciones por ID de oferta")
    @GetMapping("/oferta/{idOferta}")
    public ResponseEntity<List<Postulacion>> listByOferta(@PathVariable Long idOferta) {
        log.info("Listar postulaciones por idOferta {}", idOferta);
        return ResponseEntity.ok(postulacionServices.listByIdOferta(idOferta));
    }
//    Eliminar
//    @Operation(summary = "Eliminar una postulación")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        log.info("Eliminar postulación con id {}", id);
//        postulacionServices.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }

    @Operation(summary = "Actualizar el estado y descripción de una postulación")
    @PutMapping("/{id}/estado")
    public ResponseEntity<Postulacion> actualizarEstado(
            @PathVariable Long id,
            @RequestParam Estado estado,
            @RequestParam(required = false) String descripcion
    ) {
        log.info("Actualizar estado de postulación con id {}", id);
        return postulacionServices.findById(id)
                .map(postulacion -> {
                    postulacion.setEstado(estado);

                    // Si el nuevo estado es RECHAZADO o OBSERVACION, actualiza la descripción
                    if (estado == Estado.RECHAZADO || estado == Estado.OBSERVACION) {
                        postulacion.setDescripcion(descripcion);
                    } else {
                        postulacion.setDescripcion(null); // Limpia descripción si no aplica
                    }

                    Postulacion actualizada = postulacionServices.save(postulacion);
                    return ResponseEntity.ok(actualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}