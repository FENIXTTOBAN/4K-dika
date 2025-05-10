package upeu.sistemas.practicaspp_ms.Services;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import upeu.sistemas.practicaspp_ms.Entities.Evidencia;
import upeu.sistemas.practicaspp_ms.Entities.EstadoEvidencia;
import upeu.sistemas.practicaspp_ms.Repository.EvidenciaRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EvidenciaServiceImpl implements EvidenciaService {

    private final EvidenciaRepository evidenciaRepository;

    public EvidenciaServiceImpl(EvidenciaRepository evidenciaRepository) {
        this.evidenciaRepository = evidenciaRepository;
    }

    @Override
    public Evidencia saveEvidencia(Evidencia evidencia) {
        if (evidencia.getEstado() == null) {
            evidencia.setEstado(EstadoEvidencia.PENDIENTE);
        }
        return evidenciaRepository.save(evidencia);
    }

    @Override
    public Evidencia updateEstadoEvidencia(Long id, EstadoEvidencia estado) {
        return evidenciaRepository.findById(id)
                .map(existingEvidencia -> {
                    existingEvidencia.setEstado(estado);
                    return evidenciaRepository.save(existingEvidencia);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evidencia no encontrada con ID: " + id));
    }

    @Override
    public List<Evidencia> getAllEvidencias() {
        return evidenciaRepository.findAll();
    }

    @Override
    public List<Evidencia> getEvidenciasByPracticaId(Long practicaId) {
        return evidenciaRepository.findByPracticaId(practicaId);
    }

    @Override
    public Optional<Evidencia> getEvidenciaById(Long id) {
        return evidenciaRepository.findById(id);
    }

    @Override
    public void deleteEvidencia(Long id) {
        evidenciaRepository.findById(id)
                .ifPresentOrElse(evidenciaRepository::delete,
                        () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evidencia no encontrada con ID: " + id); });
    }
}
