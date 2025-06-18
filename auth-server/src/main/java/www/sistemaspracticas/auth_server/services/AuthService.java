package www.sistemaspracticas.auth_server.services;

import www.sistemaspracticas.auth_server.dtos.TokenDto;
import www.sistemaspracticas.auth_server.dtos.UsuarioDto;

public interface AuthService {
    TokenDto login(UsuarioDto usuario);
    TokenDto validateToken(TokenDto token);
    void crearUsuario(UsuarioDto dto);
    boolean usuarioExiste(String username);
}
