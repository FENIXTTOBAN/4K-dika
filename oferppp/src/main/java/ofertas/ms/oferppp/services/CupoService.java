package ofertas.ms.oferppp.services;

import ofertas.ms.oferppp.entity.CupoEntity;

public interface CupoService {
    CupoEntity create(CupoEntity cupo);
    CupoEntity update(CupoEntity cupo, Long id);
    CupoEntity readById(Long id);
    CupoEntity readByOfertaId(Long idOferta);
    void delete(Long id);
    void consumirCupo(Long idOferta);
}