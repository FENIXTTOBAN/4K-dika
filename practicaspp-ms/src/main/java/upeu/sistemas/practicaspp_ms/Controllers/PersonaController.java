package upeu.sistemas.practicaspp_ms.Controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.sistemas.practicaspp_ms.Entities.Persona;
import upeu.sistemas.practicaspp_ms.Entities.TipoPersona;
import upeu.sistemas.practicaspp_ms.Services.PersonaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/personas")
@Tag(name = "Persona Resource", description = "CRUD de Personas (Estudiantes y Secretarias)")
public class PersonaController {

    private final PersonaService personaService;
    private static final Logger log = LoggerFactory.getLogger(PersonaController.class);

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @Operation(summary = "Registrar nueva persona")
    @PostMapping
    public ResponseEntity<Persona> registrar(@RequestBody Persona persona) {
        log.info("Registrando persona: {}", persona.getDni());
        Persona nueva = personaService.guardar(persona);
        return ResponseEntity.created(URI.create("/personas/" + nueva.getId())).body(nueva);
    }

    @Operation(summary = "Actualizar persona por ID")
    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizar(@PathVariable Long id, @RequestBody Persona persona) {
        log.info("Actualizando persona con ID: {}", id);
        Persona actualizada = personaService.actualizar(id, persona);
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "Listar todas las personas")
    @GetMapping
    public ResponseEntity<List<Persona>> listarTodo() {
        return ResponseEntity.ok(personaService.listarTodo());
    }

    @Operation(summary = "Listar personas por tipo")
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Persona>> listarPorTipo(@PathVariable TipoPersona tipo) {
        return ResponseEntity.ok(personaService.listarPorTipo(tipo));
    }

    @Operation(summary = "Buscar persona por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Persona> buscarPorId(@PathVariable Long id) {
        return personaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
