package br.com.neurotech.challenge.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Neurotech Crédito API",
                version = "1.0",
                description = "API para avaliação de modalidades de crédito para clientes pessoa física.",
                contact = @Contact(
                        name = "Caio Amorim",
                        email = "caioamorim_@live.com"
                )
        ),
        servers = {
                @Server(url = "/api", description = "Ambiente local")
        }
)
public class OpenApiConfig {
}
