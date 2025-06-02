package www.sistemaspracticas.ofertapostulacion_ms.Configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Gestión de Prácticas Preprofesionales",
                version = "1.0.0",
                description = "Documentación de endpoints para el microservicio ofertapostulacion-ms"
        )
)
public class OpenApiConfig {
}