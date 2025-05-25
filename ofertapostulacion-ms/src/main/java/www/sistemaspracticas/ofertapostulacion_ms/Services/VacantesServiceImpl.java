package www.sistemaspracticas.ofertapostulacion_ms.Services;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import www.sistemaspracticas.ofertapostulacion_ms.Entities.Vacantes;
import www.sistemaspracticas.ofertapostulacion_ms.Repository.VacantesRepository;

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
        // Inicializar cupos disponibles como total - ocupados
        vacantes.setDisponibles(vacantes.getTotal() - vacantes.getOcupados());
        return vacantesRepository.save(vacantes);
    }

    @Override
    public Vacantes updateVacantes(Long id, int ocupados) {
        return vacantesRepository.findById(id)
                .map(v -> {
                    if (ocupados > v.getTotal()) {
                        throw new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Los cupos ocupados no pueden superar el total."
                        );
                    }
                    v.setOcupados(ocupados);
                    v.setDisponibles(v.getTotal() - ocupados);
                    return vacantesRepository.save(v);
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Vacantes no encontrada con ID: " + id));
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