package www.sistemaspracticas.auth_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import www.sistemaspracticas.auth_server.entities.UsuarioEntity;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsuario(String usuario);
    boolean existsByUsuario(String usuario);
}
