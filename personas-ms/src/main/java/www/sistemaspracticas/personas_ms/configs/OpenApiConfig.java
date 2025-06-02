package www.sistemaspracticas.personas_ms.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Gestión Personas",
                version = "1.0.0",
                description = "Este es el sector de la gestión de estudiantes y secretarias"
        )
)
public class OpenApiConfig {
}

