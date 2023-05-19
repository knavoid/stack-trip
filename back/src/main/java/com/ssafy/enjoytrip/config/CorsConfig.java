package com.ssafy.enjoytrip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://70.12.50.47:3000", "http://70.12.50.48:8080").allowedMethods("*")
                .allowCredentials(true);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://70.12.50.47:3000");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("Authorization"); // CORS로 인해 프론트단에서 인식하지 못하는 Authrization 헤더를 노출

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}