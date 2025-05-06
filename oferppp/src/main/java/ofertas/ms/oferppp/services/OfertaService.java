package ofertas.ms.oferppp.services;

import ofertas.ms.oferppp.entity.OfertaEntity;
import java.util.List;

public interface OfertaService {
    OfertaEntity create(OfertaEntity oferta);
    OfertaEntity update(OfertaEntity oferta, Long id);
    OfertaEntity readById(Long id);
    List<OfertaEntity> readAll();
    void delete(Long id);
}