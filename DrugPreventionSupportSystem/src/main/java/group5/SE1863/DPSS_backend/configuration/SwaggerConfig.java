package group5.SE1863.DPSS_backend.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "BearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {

    private String appVersion = "v1.0.0";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Drug Prevention Support System API Documentation")
                .version(appVersion).description("API documentation for the Drug Prevention Support System project"))
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("BearerAuth"));

    }
}
