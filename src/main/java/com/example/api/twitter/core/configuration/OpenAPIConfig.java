package com.example.api.twitter.core.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Gloria R. Leyva Jerez
 */
@Configuration
public class OpenAPIConfig {

    public static final Contact DEFAULT_CONTACT = new Contact()
            .name("Gloria Raquel Leyva Jerez")
            .email("leyvajerezgr@gmail.com")
            .url("https://www.linkedin.com/in/gloria-raquel-leyva-jerez/");

    public static final Info DEFAULT_API_INFO = new Info()
            .title("Tweets API")
            .description("APIs REST que permite consultar tweets previamente consumidos de la API de Twitter empleando Twitter4j y almacenados en memoria empleando Redis")
            .version("1.0")
            .contact(DEFAULT_CONTACT);

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(DEFAULT_API_INFO);
    }
}
