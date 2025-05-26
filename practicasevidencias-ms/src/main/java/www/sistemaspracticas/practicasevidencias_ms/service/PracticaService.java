package www.sistemaspracticas.practicasevidencias_ms.service;

import www.sistemaspracticas.practicasevidencias_ms.entities.Practica;

import java.util.List;

public interface PracticaService{
    Practica create(Practica practica);
    Practica findById(Long id);
    Practica update(Practica practica, Long id);
    void delete(Long id);
    List<Practica> getAll();
}
