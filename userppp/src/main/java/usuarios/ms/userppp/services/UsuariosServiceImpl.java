package usuarios.ms.userppp.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import usuarios.ms.userppp.entity.Usuarios;
import usuarios.ms.userppp.repository.UsuariosRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuariosServiceImpl implements UsuariosService {

    private final UsuariosRepository usuariosRepository;

    public UsuariosServiceImpl(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public Usuarios save(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    @Override
    public Usuarios edit(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    @Override
    public List<Usuarios> listarUsuarios() {
        return usuariosRepository.findAll();
    }

    @Override
    public Optional<Usuarios> findById(Long id) {
        return usuariosRepository.findById(id);
    }

    @Override
    public Optional<Usuarios> findByCorreo(String correo) {
        return usuariosRepository.findByCorreo(correo);
    }

    @Override
    public boolean existsByCorreo(String correo) {
        return usuariosRepository.existsByCorreo(correo);
    }

    @Override
    public boolean existsByDNI(int dni) {
        return usuariosRepository.existsByDNI(dni);
    }

    @Override
    public Optional<Usuarios> findByCodigo(int codigo) {
        return usuariosRepository.findByCodigo(codigo);
    }
}
