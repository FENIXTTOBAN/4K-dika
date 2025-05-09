package upeu.sistemas.practicaspp_ms.Services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import upeu.sistemas.practicaspp_ms.Entities.Oferta;
import upeu.sistemas.practicaspp_ms.Repository.OfertaRepository;

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
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada con ID: " + id));
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
