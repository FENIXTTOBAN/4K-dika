package upeu.sistemas.practicaspp_ms.Configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Gestion Practicas", version = "1.0.0",
                description = "Este es el sector de la gestion de los CRUDS"
        )
)
public class OpenApiConfig {
}
