package www.sistemaspracticas.ofertapostulacion_ms.Services;

import www.sistemaspracticas.ofertapostulacion_ms.Entities.Oferta;

import java.util.List;
import java.util.Optional;

public interface OfertaService {
    Oferta guardar(Oferta oferta);
    Oferta update(Long id, Boolean estado);
    List<Oferta> listarTodo();
    List<Oferta> listarPorEstado(Boolean estado);
    Optional<Oferta> findById(Long id);
}