package www.sistemaspracticas.practicasevidencias_ms.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import www.sistemaspracticas.practicasevidencias_ms.entities.Evidencia;
import www.sistemaspracticas.practicasevidencias_ms.service.EvidenciaService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "evidencia")
public class EvidenciaController {

    private final EvidenciaService evidenciaService;
    private final Logger log = LoggerFactory.getLogger(EvidenciaController.class);

    public EvidenciaController(EvidenciaService evidenciaService) {
        this.evidenciaService = evidenciaService;
    }

    @Operation(summary = "Listar evidencias por ID de práctica")
    @GetMapping("/practica/{idPractica}")
    public ResponseEntity<List<Evidencia>> listarPorPractica(@PathVariable Long idPractica) {
        return ResponseEntity.ok(evidenciaService.listarPorPractica(idPractica));
    }

    @Operation(summary = "Validar si puede subir nueva evidencia")
    @GetMapping("/puede-subir/{idPractica}")
    public ResponseEntity<Boolean> puedeSubir(@PathVariable Long idPractica) {
        return ResponseEntity.ok(evidenciaService.puedeSubirEvidencia(idPractica));
    }

    @PostMapping("/subir")
    @Operation(summary = "Subir nueva evidencia con archivo")
    public ResponseEntity<Evidencia> subirEvidencia(
            @RequestParam Long idPractica,
            @RequestParam MultipartFile archivo,
            @RequestParam String descripcion) throws IOException {
        Evidencia nueva = evidenciaService.subirEvidencia(idPractica, archivo, descripcion);
        return ResponseEntity.ok(nueva);
    }

    @Operation(summary = "Aceptar evidencia por ID")
    @PutMapping("/{id}/aceptar")
    public ResponseEntity<Evidencia> aceptar(@PathVariable Long id) {
        return ResponseEntity.ok(evidenciaService.aceptarEvidencia(id));
    }

    @Operation(summary = "Rechazar evidencia por ID")
    @PutMapping("/{id}/rechazar")
    public ResponseEntity<Evidencia> rechazar(@PathVariable Long id) {
        return ResponseEntity.ok(evidenciaService.rechazarEvidencia(id));
    }
}
