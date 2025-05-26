package www.sistemaspracticas.practicasevidencias_ms.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import www.sistemaspracticas.practicasevidencias_ms.entities.Evidencia;
import www.sistemaspracticas.practicasevidencias_ms.repository.EvidenciaRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class EvidenciaServiceImpl implements EvidenciaService {

    private EvidenciaRepository evidenciaRepository;

    public EvidenciaServiceImpl(EvidenciaRepository evidenciaRepository) {
        this.evidenciaRepository = evidenciaRepository;
    }


    @Override
    public Evidencia create(Evidencia evidencia) {
        return this.evidenciaRepository.save(evidencia);
    }

    @Override
    public Evidencia findById(Long id) {
        return this.evidenciaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Evidencia no encontrada"));
    }

    @Override
    public Evidencia update(Evidencia evidencia, Long id) {
        Evidencia evidenciaToUpdate = this.evidenciaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Evidencia no encontrada"));

        evidenciaToUpdate.setNombreArchivo(evidencia.getNombreArchivo());
        evidenciaToUpdate.setUrlArchivo(evidencia.getUrlArchivo());
        evidenciaToUpdate.setDescripcion(evidencia.getDescripcion());
        evidenciaToUpdate.setFechaSubida(evidencia.getFechaSubida());
        evidenciaToUpdate.setEstado(evidencia.getEstado());

        return this.evidenciaRepository.save(evidenciaToUpdate);    }

    @Override
    public void delete(Long id) {
        Evidencia evidenciaToDelete = this.evidenciaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Evidencia no encontrada"));
        this.evidenciaRepository.delete(evidenciaToDelete);
    }

    @Override
    public List<Evidencia> getAll() {
        return this.evidenciaRepository.findAll();
    }

    @Override
    public List<Evidencia> getByPracticaId(Long idPractica) {
        return this.evidenciaRepository.findByIdPractica(idPractica);
    }
}
