package www.sistemaspracticas.practicasevidencias_ms.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import www.sistemaspracticas.practicasevidencias_ms.entities.Practica;
import www.sistemaspracticas.practicasevidencias_ms.repository.PracticaRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PracticaServiceImpl implements PracticaService {

    private final PracticaRepository practicaRepository;

    public PracticaServiceImpl(PracticaRepository practicaRepository) {
        this.practicaRepository = practicaRepository;
    }

    @Override
    public Practica create(Practica practica) {
        return this.practicaRepository.save(practica);
    }

    @Override
    public Practica findById(Long id) {
        return this.practicaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Práctica no encontrada"));
    }

    @Override
    public Practica update(Practica practica, Long id) {
        Practica practicaToUpdate = this.practicaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Práctica no encontrada"));

        practicaToUpdate.setFechaFin(practica.getFechaFin());
        practicaToUpdate.setEstado(practica.getEstado());

        return this.practicaRepository.save(practicaToUpdate);    }

    @Override
    public void delete(Long id) {
        Practica practicaToDelete = this.practicaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Práctica no encontrada"));
        this.practicaRepository.delete(practicaToDelete);
    }

    @Override
    public List<Practica> getAll() {
        return this.practicaRepository.findAll();
    }
}
