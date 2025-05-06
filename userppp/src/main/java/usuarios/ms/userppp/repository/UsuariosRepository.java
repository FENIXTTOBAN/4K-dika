package usuarios.ms.userppp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usuarios.ms.userppp.entity.Usuarios;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

    Optional<Usuarios> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
    boolean existsByDNI(int dni);
    Optional<Usuarios> findByCodigo(int codigo);
}
