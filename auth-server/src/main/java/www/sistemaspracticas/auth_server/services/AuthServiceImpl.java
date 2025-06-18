package www.sistemaspracticas.auth_server.services;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import www.sistemaspracticas.auth_server.dtos.TokenDto;
import www.sistemaspracticas.auth_server.dtos.UsuarioDto;
import www.sistemaspracticas.auth_server.entities.UsuarioEntity;
import www.sistemaspracticas.auth_server.helpers.JwtHelper;
import www.sistemaspracticas.auth_server.repositories.UsuarioRepository;

@Transactional
@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;
    private final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private static final String USER_EXCEPTION_MSG = "Error to auth user";

    public AuthServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtHelper jwtHelper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public TokenDto login(UsuarioDto usuario) {
        final var userFromDB = this.usuarioRepository.findByUsuario(usuario.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MSG));
        this.validPassword(usuario, userFromDB);
        return TokenDto.builder().accessToken(this.jwtHelper.createToken(userFromDB.getUsuario(), userFromDB.getRol())).build();
    }

    @Override
    public TokenDto validateToken(TokenDto token) {
        log.info("AuthServiceImpl:"+token);

        if(this.jwtHelper.validateToken(token.getAccessToken())){
            log.info("ingresa al if de AuthServiceImpl:"+token);
            return TokenDto.builder().accessToken(token.getAccessToken()).build();
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MSG);
    }

    @Override
    public void crearUsuario(UsuarioDto dto) {
        UsuarioEntity nuevo = new UsuarioEntity();
        nuevo.setUsuario(dto.getUsername());
        nuevo.setPassword(passwordEncoder.encode(dto.getPassword()));
        nuevo.setRol(dto.getRol());
        usuarioRepository.save(nuevo);
    }

    @Override
    public boolean usuarioExiste(String username) {
        return usuarioRepository.existsByUsuario(username);
    }

    private void validPassword(UsuarioDto usuarioDto, UsuarioEntity usuarioEntity) {
        if (!this.passwordEncoder.matches(usuarioDto.getPassword(), usuarioEntity.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MSG);
        }
    }
}
