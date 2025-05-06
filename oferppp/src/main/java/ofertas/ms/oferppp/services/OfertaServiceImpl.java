package ofertas.ms.oferppp.services;

import jakarta.transaction.Transactional;
import ofertas.ms.oferppp.entity.OfertaEntity;
import ofertas.ms.oferppp.repository.OfertaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class OfertaServiceImpl implements OfertaService {

    private final OfertaRepository ofertaRepository;

    public OfertaServiceImpl(OfertaRepository ofertaRepository) {
        this.ofertaRepository = ofertaRepository;
    }

    @Override
    public OfertaEntity create(OfertaEntity oferta) {
        oferta.setFechaPublicacion(LocalDate.now());
        return ofertaRepository.save(oferta);
    }

    @Override
    public OfertaEntity update(OfertaEntity oferta, Long id) {
        OfertaEntity ofertaToUpdate = ofertaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Oferta no encontrada"));

        ofertaToUpdate.setNombrePuesto(oferta.getNombrePuesto());
        ofertaToUpdate.setDescripcionPuesto(oferta.getDescripcionPuesto());
        ofertaToUpdate.setRequisitos(oferta.getRequisitos());
        ofertaToUpdate.setModalidad(oferta.getModalidad());
        ofertaToUpdate.setEstado(oferta.getEstado());
        ofertaToUpdate.setIdEmpresa(oferta.getIdEmpresa());
        ofertaToUpdate.setIdAdmin(oferta.getIdAdmin());
        ofertaToUpdate.setFechaPublicacion(oferta.getFechaPublicacion()); // Si quieres permitir cambiarla

        return ofertaRepository.save(ofertaToUpdate);
    }

    @Override
    public OfertaEntity readById(Long id) {
        return ofertaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Oferta no encontrada"));
    }

    @Override
    public List<OfertaEntity> readAll() {
        return ofertaRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        OfertaEntity ofertaToDelete = ofertaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Oferta no encontrada"));
        ofertaRepository.delete(ofertaToDelete);
    }
}