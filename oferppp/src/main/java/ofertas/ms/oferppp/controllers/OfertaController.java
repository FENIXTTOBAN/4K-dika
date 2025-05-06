package ofertas.ms.oferppp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ofertas.ms.oferppp.entity.OfertaEntity;
import ofertas.ms.oferppp.services.OfertaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "oferta")
@Tag(name = "Oferta Resources")
public class OfertaController {

    private final OfertaService ofertaService;
    private final Logger log = LoggerFactory.getLogger(OfertaController.class);

    public OfertaController(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    @Operation(summary = "GET una oferta por ID")
    @GetMapping(path = "{id}")
    public ResponseEntity<OfertaEntity> get(@PathVariable Long id) {
        log.info("GET oferta {}", id);
        return ResponseEntity.ok(this.ofertaService.readById(id));
    }

    @Operation(summary = "POST guardar una nueva oferta en la BD")
    @PostMapping
    public ResponseEntity<OfertaEntity> post(@RequestBody OfertaEntity oferta) {
        log.info("POST oferta {}", oferta);
        OfertaEntity created = this.ofertaService.create(oferta);
        return ResponseEntity.created(URI.create("/oferta/" + created.getId())).build();
    }

    @Operation(summary = "PUT actualizar una oferta existente")
    @PutMapping(path = "{id}")
    public ResponseEntity<OfertaEntity> put(@RequestBody OfertaEntity oferta, @PathVariable Long id) {
        log.info("PUT oferta {}", oferta);
        return ResponseEntity.ok(this.ofertaService.update(oferta, id));
    }

    @Operation(summary = "DELETE eliminar una oferta por ID")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE oferta {}", id);
        this.ofertaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "GET todas las ofertas")
    @GetMapping
    public ResponseEntity<List<OfertaEntity>> getAll() {
        log.info("GET all ofertas");
        return ResponseEntity.ok(this.ofertaService.readAll());
    }
}