package ofertas.ms.oferppp.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Ofertas Crud", version = "1.0.0",
                description = "Este es el CRUD de datos de las ofertas"
        )
)
public class OpenApiConfig {
}
