package www.sistemaspracticas.ofertapostulacion_ms.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.Oferta;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.Vacantes;
import www.sistemaspracticas.ofertapostulacion_ms.Services.OfertaService;
import www.sistemaspracticas.ofertapostulacion_ms.Services.VacantesService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ofertas")
@Tag(name = "Oferta Resource", description = "CRUD de Ofertas de prácticas")
public class OfertaController {

    private final OfertaService ofertaService;
    private final VacantesService vacantesService;
    private static final Logger log = LoggerFactory.getLogger(OfertaController.class);

    public OfertaController(OfertaService ofertaService, VacantesService vacantesService) {
        this.ofertaService = ofertaService;
        this.vacantesService = vacantesService;
    }

    @Operation(summary = "Registrar nueva oferta y su vacante")
    @PostMapping
    public ResponseEntity<Oferta> registrar(@RequestBody Oferta oferta, @RequestParam int cupos) {
        log.info("Registrando nueva oferta: {}", oferta.getTitulo());
        Oferta nueva = ofertaService.guardar(oferta);

        Vacantes vacante = new Vacantes();
        vacante.setOferta(nueva);
        vacante.setTotal(cupos);
        vacante.setOcupados(0);
        vacante.setDisponibles(cupos);
        vacantesService.guardar(vacante);

        return ResponseEntity.created(URI.create("/ofertas/" + nueva.getId())).body(nueva);
    }

    @Operation(summary = "Actualizar estado de una oferta por ID")
    @PutMapping("/{id}/estado")
    public ResponseEntity<Oferta> update(@PathVariable Long id, @RequestParam Boolean estado) {
        log.info("Actualizando estado de oferta ID {} a {}", id, estado);
        Oferta actualizada = ofertaService.update(id, estado);
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "Listar todas las ofertas")
    @GetMapping
    public ResponseEntity<List<Oferta>> listarTodo() {
        return ResponseEntity.ok(ofertaService.listarTodo());
    }

    @Operation(summary = "Listar ofertas por estado")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Oferta>> listarPorEstado(@PathVariable Boolean estado) {
        return ResponseEntity.ok(ofertaService.listarPorEstado(estado));
    }

    @Operation(summary = "Buscar oferta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Oferta> buscarPorId(@PathVariable Long id) {
        return ofertaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}