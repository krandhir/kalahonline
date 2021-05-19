package com.kgc.nl.kalah.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/**
 * Swagger config. To see the generated YAML go to http://host:port/v3/api-docs
 * 
 */
@Configuration
public class SwaggerConfig {
  protected Info info = new Info();
  protected Components components = new Components();

  private static final String SWAGGER_TITLE = "Kalah Online";
  private static final String SWAGGER_DESCRIPTION =
      "All processes related to Kalah online game as described in the requirement document.";

  /**
   * Open API configuration
   * 
   * @return Custom OPen API bean
   */
  @Bean
  public OpenAPI api() {
    info.contact(new Contact().name("Randhir Kumar").email("randhir.raj@gmail.com"));
    return new OpenAPI().info(info.title(SWAGGER_TITLE).description(SWAGGER_DESCRIPTION).version("1.0.0"))
        .components(components);
  }

}
