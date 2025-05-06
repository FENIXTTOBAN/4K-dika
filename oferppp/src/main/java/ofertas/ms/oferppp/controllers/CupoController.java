package ofertas.ms.oferppp.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ofertas.ms.oferppp.entity.CupoEntity;
import ofertas.ms.oferppp.services.CupoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping(path = "cupo")
@Tag(name= "Cupo Resources")
public class CupoController {

    private final CupoService cupoService;
    private final Logger log = LoggerFactory.getLogger(CupoController.class);

    public CupoController(CupoService cupoService) {
        this.cupoService = cupoService;
    }

    @Operation(summary = "GET un cupo por ID")
    @GetMapping(path = "{id}")
    public ResponseEntity<CupoEntity> get(@PathVariable Long id) {
        log.info("GET cupo {}", id);
        return ResponseEntity.ok(this.cupoService.readById(id));
    }

    @Operation(summary = "GET un cupo por ID de oferta")
    @GetMapping(path = "/oferta/{idOferta}")
    public ResponseEntity<CupoEntity> getByOfertaId(@PathVariable Long idOferta) {
        log.info("GET cupo by idOferta {}", idOferta);
        return ResponseEntity.ok(this.cupoService.readByOfertaId(idOferta));
    }

    @Operation(summary = "POST guarda un nuevo cupo en la BD")
    @PostMapping
    public ResponseEntity<CupoEntity> post(@RequestBody CupoEntity cupo) {
        log.info("POST cupo {}", cupo);
        CupoEntity created = this.cupoService.create(cupo);
        return ResponseEntity.created(URI.create("/cupo/" + created.getId())).build();
    }

    @Operation(summary = "PUT actualizar un cupo existente")
    @PutMapping(path = "{id}")
    public ResponseEntity<CupoEntity> put(@RequestBody CupoEntity cupo, @PathVariable Long id) {
        log.info("PUT cupo {}", cupo);
        return ResponseEntity.ok(this.cupoService.update(cupo, id));
    }

    @Operation(summary = "DELETE eliminar un cupo por ID")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE cupo {}", id);
        this.cupoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "PUT consumir un cupo al validar una postulación")
    @PutMapping(path = "consumir/{idOferta}")
    public ResponseEntity<Void> consumir(@PathVariable Long idOferta) {
        log.info("PUT consumir cupo para oferta {}", idOferta);
        this.cupoService.consumirCupo(idOferta);
        return ResponseEntity.noContent().build();
    }
}