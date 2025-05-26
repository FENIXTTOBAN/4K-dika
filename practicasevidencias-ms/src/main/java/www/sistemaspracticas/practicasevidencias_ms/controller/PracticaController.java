package www.sistemaspracticas.practicasevidencias_ms.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import www.sistemaspracticas.practicasevidencias_ms.entities.Practica;
import www.sistemaspracticas.practicasevidencias_ms.service.PracticaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "practica")
public class PracticaController  {

    private final PracticaService practicaService;
    private final Logger log = LoggerFactory.getLogger(PracticaController.class);

    public PracticaController(PracticaService practicaService) {
        this.practicaService = practicaService;
    }

    @GetMapping
    public ResponseEntity<List<Practica>> getAll() {
        log.info("GET todas las prácticas");
        return ResponseEntity.ok(this.practicaService.getAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Practica> getById(@PathVariable Long id) {
        log.info("GET práctica con id {}", id);
        return ResponseEntity.ok(this.practicaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Practica> create(@RequestBody Practica practica) {
        log.info("POST nueva práctica {}", practica);
        Practica created = this.practicaService.create(practica);
        return ResponseEntity.created(URI.create("/practica/" + created.getId())).body(created);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Practica> update(@RequestBody Practica practica, @PathVariable Long id) {
        log.info("PUT práctica con id {}", id);
        return ResponseEntity.ok(this.practicaService.update(practica, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE práctica con id {}", id);
        this.practicaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
