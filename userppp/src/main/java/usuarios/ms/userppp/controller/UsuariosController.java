package usuarios.ms.userppp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usuarios.ms.userppp.entity.Usuarios;
import usuarios.ms.userppp.services.UsuariosService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/usuarios")
@Tag(name = "Usuarios Resources", description = "CRUD de Usuarios (Estudiantes y Secretarias)")
public class UsuariosController {

    private final UsuariosService usuariosService;
    private final Logger log = LoggerFactory.getLogger(UsuariosController.class);

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @Operation(summary = "Registrar un nuevo usuario")
    @PostMapping
    public ResponseEntity<Usuarios> save(@RequestBody Usuarios usuarios) {
        log.info("Registrar un nuevo usuario");
        Usuarios nuev = usuariosService.save(usuarios);
        return ResponseEntity.created(URI.create("/usuarios/" + nuev.getId())).body(nuev);
    }

    @Operation(summary = "Actualizar solo el estado de un usuario")
    @PutMapping("/{id}/estado")
    public ResponseEntity<Usuarios> editar(
            @PathVariable Long id,
            @RequestParam Boolean estado
    ) {
        return usuariosService.findById(id)
                .map(usuario -> {
                    usuario.setEstado(estado);
                    Usuarios actualizado = usuariosService.edit(usuario);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping
    public ResponseEntity<List<Usuarios>> listar() {
        log.info("Listar todos los usuarios");
        return ResponseEntity.ok(usuariosService.listarUsuarios());
    }

    @Operation(summary = "Buscar un usuario por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> findById(@PathVariable Long id) {
        log.info("Buscar usuario con id {}", id);
        return usuariosService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar un usuario por correo")
    @GetMapping("/correo/{correo}")
    public ResponseEntity<Usuarios> findByCorreo(@PathVariable String correo) {
        log.info("Buscar usuario con correo {}", correo);
        return usuariosService.findByCorreo(correo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar un usuario por código")
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Usuarios> findByCodigo(@PathVariable int codigo) {
        log.info("Buscar usuario con código {}", codigo);
        return usuariosService.findByCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
