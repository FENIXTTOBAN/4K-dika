package upeu.sistemas.practicaspp_ms.Services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import upeu.sistemas.practicaspp_ms.Entities.Vacantes;
import upeu.sistemas.practicaspp_ms.Repository.VacantesRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VacantesServiceImpl implements VacantesService {

    private final VacantesRepository vacantesRepository;

    public VacantesServiceImpl(VacantesRepository vacantesRepository) {
        this.vacantesRepository = vacantesRepository;
    }

    @Override
    public Vacantes guardar(Vacantes vacantes) {
        return vacantesRepository.save(vacantes);
    }

    @Override
    public Vacantes updateCupos(Long id, int ocupados) {
        return vacantesRepository.findById(id).map(v -> {
            v.setOcupados(ocupados);
            v.setDisponibles(v.getTotal() - ocupados);
            return vacantesRepository.save(v);
        }).orElseThrow(() -> new RuntimeException("Vacantes no encontrada con ID: " + id));
    }

    @Override
    public Optional<Vacantes> findById(Long id) {
        return vacantesRepository.findById(id);
    }

    @Override
    public Optional<Vacantes> findByOfertaId(Long ofertaId) {
        return vacantesRepository.findByOfertaId(ofertaId);
    }

    @Override
    public List<Vacantes> listarTodo() {
        return vacantesRepository.findAll();
    }
}
