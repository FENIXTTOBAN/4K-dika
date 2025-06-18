package www.sistemaspracticas.ofertapostulacion_ms.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "personas-ms", path = "/personas-ms/personas")
public interface PersonaFeign {

    @GetMapping("/{id}")
    Persona obtenerPorId(@PathVariable("id") Long id);
}
