package www.sistemaspracticas.practicasevidencias_ms.configs;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Practicas Evidencias CRUD", version = "1.0.0",
                description = "Este es el CRUD de Practicas Evidencias microservicios"

        )
)
public class OpenApiConfig {
}
