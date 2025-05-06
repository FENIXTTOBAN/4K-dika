package postulaciones.ms.posppp.Configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Postulacion CRUD", version = "1.0.0",
                description = "Este es el CRUD de datos de las Postulacion"
        )
)

public class OpenApiConfig {

}
