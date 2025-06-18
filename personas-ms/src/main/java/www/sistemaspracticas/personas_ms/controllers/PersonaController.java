package www.sistemaspracticas.personas_ms.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import www.sistemaspracticas.personas_ms.entities.Persona;
import www.sistemaspracticas.personas_ms.entities.TipoPersona;
import www.sistemaspracticas.personas_ms.services.PersonaService;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import www.sistemaspracticas.personas_ms.dtos.RegistroCompleto;

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
    @PreAuthorize("hasAuthority('PRACTICADOR')")
    @PostMapping("/registro-completo")
    public ResponseEntity<?> registrarCompleto(@RequestBody RegistroCompleto dto) {
        String authUrl = "http://localhost:4040/auth-server/auth/crear-usuario"; // Deberás crear este endpoint en auth-server

        // Construir el cuerpo de la petición para auth-server
        var usuarioJson = new java.util.HashMap<String, Object>();
        usuarioJson.put("username", dto.getUsername());
        usuarioJson.put("password", dto.getPassword());
        usuarioJson.put("rol", dto.getRol());
        usuarioJson.put("estado", dto.getEstado());

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> request = new HttpEntity<>(usuarioJson, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(authUrl, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                // Si fue exitoso el registro en auth-server, guardar en personas-ms
                Persona persona = new Persona();
                persona.setNombres(dto.getNombres());
                persona.setApellidos(dto.getApellidos());
                persona.setDni(dto.getDni());
                persona.setTelefono(dto.getTelefono());
                persona.setEstado(dto.getEstado());
                persona.setTipoPersona(dto.getTipoPersona());


                Persona nueva = personaService.guardar(persona);
                return ResponseEntity.created(URI.create("/personas/" + nueva.getId())).body(nueva);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error al registrar usuario en auth-server.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error durante el registro completo: " + e.getMessage());
        }
    }
}
