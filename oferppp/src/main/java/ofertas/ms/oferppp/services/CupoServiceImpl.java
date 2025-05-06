package ofertas.ms.oferppp.services;

import jakarta.transaction.Transactional;
import ofertas.ms.oferppp.entity.CupoEntity;
import ofertas.ms.oferppp.repository.CupoRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class CupoServiceImpl implements CupoService {

    private final CupoRepository cupoRepository;

    public CupoServiceImpl(CupoRepository cupoRepository) {
        this.cupoRepository = cupoRepository;
    }

    @Override
    public CupoEntity create(CupoEntity cupo) {
        cupo.setSalidas(0);
        cupo.setBalance(cupo.getEntradas());
        return cupoRepository.save(cupo);
    }

    @Override
    public CupoEntity update(CupoEntity cupo, Long id) {
        CupoEntity cupoToUpdate = cupoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cupo no encontrado"));

        cupoToUpdate.setEntradas(cupo.getEntradas());
        cupoToUpdate.setSalidas(cupo.getSalidas());
        cupoToUpdate.setBalance(cupo.getEntradas() - cupo.getSalidas());

        return cupoRepository.save(cupoToUpdate);
    }

    @Override
    public CupoEntity readById(Long id) {
        return cupoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cupo no encontrado"));
    }

    @Override
    public CupoEntity readByOfertaId(Long idOferta) {
        return this.cupoRepository.findByOferta_Id(idOferta)
                .orElseThrow(() -> new NoSuchElementException("No se encontró un cupo para esta oferta"));
    }

    @Override
    public void delete(Long id) {
        CupoEntity cupoToDelete = cupoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cupo no encontrado"));
        cupoRepository.delete(cupoToDelete);
    }

    @Override
    public void consumirCupo(Long idOferta) {
        CupoEntity cupo = cupoRepository.findByOferta_Id(idOferta)
                .orElseThrow(() -> new NoSuchElementException("Cupo no encontrado para la oferta"));

        if (cupo.getBalance() <= 0) {
            throw new IllegalStateException("No hay cupos disponibles para esta oferta");
        }

        cupo.setSalidas(cupo.getSalidas() + 1);
        cupo.setBalance(cupo.getEntradas() - cupo.getSalidas());

        cupoRepository.save(cupo);
    }
}