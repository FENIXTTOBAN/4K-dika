package www.sistemaspracticas.practicasevidencias_ms.service;

import org.springframework.web.multipart.MultipartFile;
import www.sistemaspracticas.practicasevidencias_ms.entities.Evidencia;
import java.io.IOException;
import java.util.List;


public interface EvidenciaService {
    List<Evidencia> listarPorPractica(Long idPractica);
    boolean puedeSubirEvidencia(Long idPractica);
    Evidencia subirEvidencia(Long idPractica, MultipartFile archivo, String descripcion) throws IOException;
    Evidencia aceptarEvidencia(Long id);
    Evidencia rechazarEvidencia(Long id);
}
