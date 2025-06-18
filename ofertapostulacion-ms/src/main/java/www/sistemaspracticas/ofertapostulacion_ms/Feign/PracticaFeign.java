package www.sistemaspracticas.ofertapostulacion_ms.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "practicasevidencias-ms", path = "/practicasevidencias-ms/practica")
public interface PracticaFeign {

    @PostMapping
    Practica registrar(@RequestBody Practica practica);
}
