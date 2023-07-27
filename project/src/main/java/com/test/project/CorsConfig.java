package com.test.project;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Permitir todas las solicitudes desde cualquier origen (puedes especificar dominios permitidos aquí)
                .allowedMethods("*") // Permitir los métodos HTTP que desees
                .allowedHeaders("*"); // Permitir todas las cabeceras
    }
}
