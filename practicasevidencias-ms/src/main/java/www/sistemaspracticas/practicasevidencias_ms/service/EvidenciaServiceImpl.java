package www.sistemaspracticas.practicasevidencias_ms.service;


import jakarta.transaction.Transactional;
import www.sistemaspracticas.practicasevidencias_ms.entities.Evidencia;
import www.sistemaspracticas.practicasevidencias_ms.entities.EstadoEvidencia;
import www.sistemaspracticas.practicasevidencias_ms.entities.Practica;
import www.sistemaspracticas.practicasevidencias_ms.entities.EstadoPractica;
import www.sistemaspracticas.practicasevidencias_ms.repository.EvidenciaRepository;
import www.sistemaspracticas.practicasevidencias_ms.repository.PracticaRepository;
import www.sistemaspracticas.practicasevidencias_ms.service.EvidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class EvidenciaServiceImpl implements EvidenciaService {

    private final EvidenciaRepository evidenciaRepository;
    private final PracticaRepository practicaRepository;
    private final String uploadDir = "uploads";

    public EvidenciaServiceImpl(EvidenciaRepository evidenciaRepository, PracticaRepository practicaRepository) {
        this.evidenciaRepository = evidenciaRepository;
        this.practicaRepository = practicaRepository;
    }

    @Override
    public List<Evidencia> listarPorPractica(Long idPractica) {
        return evidenciaRepository.findAllByPracticaOrderByFechaDesc(idPractica);
    }

    @Override
    public boolean puedeSubirEvidencia(Long idPractica) {
        Optional<Practica> practicaOpt = practicaRepository.findById(idPractica);
        if (practicaOpt.isEmpty()) return false;

        Practica practica = practicaOpt.get();
        if (!practica.getEstado().equals(EstadoPractica.PROCESO)) {
            return false;
        }

        List<Evidencia> evidencias = evidenciaRepository.findAllByPracticaOrderByFechaDesc(idPractica);
        if (evidencias.isEmpty()) return true;

        Evidencia ultima = evidencias.get(0);
        if (ultima.getEstado() == EstadoEvidencia.PENDIENTE) return false;
        if (ultima.getEstado() == EstadoEvidencia.RECHAZADO) return true;

        return ultima.getFechaSubida().plusDays(7).isBefore(LocalDate.now());
    }

    @Override
    public Evidencia subirEvidencia(Long idPractica, MultipartFile archivo, String descripcion) throws IOException {
        if (!puedeSubirEvidencia(idPractica)) {
            throw new IllegalStateException("No se puede subir evidencia aún. Espere una semana o revise el estado anterior.");
        }

        Path dirPath = Paths.get(uploadDir);
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        String nombreArchivo = archivo.getOriginalFilename();
        Path filePath = dirPath.resolve(nombreArchivo);
        archivo.transferTo(filePath);

        Evidencia evidencia = new Evidencia();
        evidencia.setIdPractica(idPractica);
        evidencia.setNombreArchivo(nombreArchivo);
        evidencia.setUrlArchivo(filePath.toString());
        evidencia.setDescripcion(descripcion);
        evidencia.setFechaSubida(LocalDate.now());
        evidencia.setEstado(EstadoEvidencia.PENDIENTE);

        return evidenciaRepository.save(evidencia);
    }

    @Override
    public Evidencia aceptarEvidencia(Long id) {
        Evidencia evidencia = evidenciaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evidencia no encontrada"));
        evidencia.setEstado(EstadoEvidencia.ACEPTADO);
        return evidenciaRepository.save(evidencia);
    }

    @Override
    public Evidencia rechazarEvidencia(Long id) {
        Evidencia evidencia = evidenciaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evidencia no encontrada"));
        evidencia.setEstado(EstadoEvidencia.RECHAZADO);
        evidencia.setDescripcion("El archivo no es válido, suba uno correcto.");
        return evidenciaRepository.save(evidencia);
    }
}
