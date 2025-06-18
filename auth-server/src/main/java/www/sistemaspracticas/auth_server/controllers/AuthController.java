package www.sistemaspracticas.auth_server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import www.sistemaspracticas.auth_server.dtos.TokenDto;
import www.sistemaspracticas.auth_server.dtos.UsuarioDto;
import www.sistemaspracticas.auth_server.services.AuthService;

@RestController
@RequestMapping(path = "auth")
public class AuthController {

    private final AuthService authService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "login")
    public ResponseEntity<TokenDto> jwtCreate(@RequestBody UsuarioDto usuario) {
        return ResponseEntity.ok(authService.login(usuario));
    }

    @PostMapping(path = "jwt")
    public ResponseEntity<TokenDto> jwtValidate(@RequestBody String accessToken) {
        logger.info("Auth_controller: " + accessToken);
        return ResponseEntity.ok(this.authService.validateToken(TokenDto.builder().accessToken(accessToken).build()));
    }

    @PostMapping(path = "crear-usuario")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioDto dto) {
        if (authService.usuarioExiste(dto.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo ya está registrado");
        }
        authService.crearUsuario(dto);
        return ResponseEntity.ok("Usuario creado correctamente");
    }
}
