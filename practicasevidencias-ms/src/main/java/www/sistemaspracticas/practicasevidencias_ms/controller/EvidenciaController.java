package www.sistemaspracticas.practicasevidencias_ms.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import www.sistemaspracticas.practicasevidencias_ms.entities.Evidencia;
import www.sistemaspracticas.practicasevidencias_ms.service.EvidenciaService;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "evidencia")
public class EvidenciaController {

    private final EvidenciaService evidenciaService;
    private final Logger log = LoggerFactory.getLogger(EvidenciaController.class);

    public EvidenciaController(EvidenciaService evidenciaService) {
        this.evidenciaService = evidenciaService;
    }

    @GetMapping
    public ResponseEntity<List<Evidencia>> getAll() {
        log.info("GET todas las evidencias");
        return ResponseEntity.ok(this.evidenciaService.getAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Evidencia> getById(@PathVariable Long id) {
        log.info("GET evidencia con id {}", id);
        return ResponseEntity.ok(this.evidenciaService.findById(id));
    }

    @GetMapping(path = "practica/{idPractica}")
    public ResponseEntity<List<Evidencia>> getByPractica(@PathVariable Long idPractica) {
        log.info("GET evidencias de la práctica {}", idPractica);
        return ResponseEntity.ok(this.evidenciaService.getByPracticaId(idPractica));
    }

    @PostMapping
    public ResponseEntity<Evidencia> create(@RequestBody Evidencia evidencia) {
        log.info("POST nueva evidencia {}", evidencia);
        Evidencia created = this.evidenciaService.create(evidencia);
        return ResponseEntity.created(URI.create("/evidencia/" + created.getId())).body(created);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Evidencia> update(@RequestBody Evidencia evidencia, @PathVariable Long id) {
        log.info("PUT evidencia con id {}", id);
        return ResponseEntity.ok(this.evidenciaService.update(evidencia, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE evidencia con id {}", id);
        this.evidenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
