package www.sistemaspracticas.ofertapostulacion_ms.Services;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.Oferta;
import www.sistemaspracticas.ofertapostulacion_ms.Repository.OfertaRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OfertaServiceImpl implements OfertaService {

    private final OfertaRepository ofertaRepository;

    public OfertaServiceImpl(OfertaRepository ofertaRepository) {
        this.ofertaRepository = ofertaRepository;
    }

    @Override
    public Oferta guardar(Oferta oferta) {
        return ofertaRepository.save(oferta);
    }

    @Override
    public Oferta update(Long id, Boolean estado) {
        return ofertaRepository.findById(id)
                .map(oferta -> {
                    oferta.setEstado(estado);
                    return ofertaRepository.save(oferta);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Oferta no encontrada con ID: " + id));
    }

    @Override
    public List<Oferta> listarTodo() {
        return ofertaRepository.findAll();
    }

    @Override
    public List<Oferta> listarPorEstado(Boolean estado) {
        return ofertaRepository.findByEstado(estado);
    }

    @Override
    public Optional<Oferta> findById(Long id) {
        return ofertaRepository.findById(id);
    }
}