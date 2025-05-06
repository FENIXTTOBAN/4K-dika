package usuarios.ms.userppp.services;

import usuarios.ms.userppp.entity.Usuarios;

import java.util.List;
import java.util.Optional;

public interface UsuariosService {

    Usuarios save(Usuarios usuario);
    Usuarios edit(Usuarios usuario);
    List<Usuarios> listarUsuarios();
    Optional<Usuarios> findById(Long id);
    Optional<Usuarios> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
    boolean existsByDNI(int dni);
    Optional<Usuarios> findByCodigo(int codigo);
}
