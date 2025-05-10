package upeu.sistemas.practicaspp_ms.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.sistemas.practicaspp_ms.Entities.Evidencia;
import upeu.sistemas.practicaspp_ms.Entities.EstadoEvidencia;
import upeu.sistemas.practicaspp_ms.Services.EvidenciaService;

import java.util.List;

@RestController
@RequestMapping("/practicaspp/evidencias")
@CrossOrigin(origins = "*")
public class EvidenciaController {

    private final EvidenciaService evidenciaService;

    public EvidenciaController(EvidenciaService evidenciaService) {
        this.evidenciaService = evidenciaService;
    }

    @PostMapping
    public ResponseEntity<Evidencia> registrar(@RequestBody Evidencia evidencia) {
        return new ResponseEntity<>(evidenciaService.saveEvidencia(evidencia), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Evidencia>> listarTodos() {
        return ResponseEntity.ok(evidenciaService.getAllEvidencias());
    }

    @GetMapping("/practica/{practicaId}")
    public ResponseEntity<List<Evidencia>> listarPorPractica(@PathVariable Long practicaId) {
        return ResponseEntity.ok(evidenciaService.getEvidenciasByPracticaId(practicaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evidencia> buscarPorId(@PathVariable Long id) {
        return evidenciaService.getEvidenciaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evidencia> actualizarEstado(@PathVariable Long id, @RequestParam EstadoEvidencia estado) {
        return ResponseEntity.ok(evidenciaService.updateEstadoEvidencia(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        evidenciaService.deleteEvidencia(id);
        return ResponseEntity.noContent().build();
    }
}
