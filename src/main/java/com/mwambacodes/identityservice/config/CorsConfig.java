package com.mwambacodes.identityservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
//public class CorsConfig implements WebMvcConfigurer {
public class CorsConfig {

//    @Value("#{'${cors.allowed-origins}'}")
//    private String allowedOrigins;
//    @Value("#{'${cors.allowed-methods}'}")
//    private String allowedMethods;
//    @Value("#{'${cors.allowed-headers}'}")
//    private String allowedHeaders;
//    @Value("#{'${cors.exposed-headers}'}")
//    private String exposedHeaders;
//
    @Value("#{'${cors.allowed-origins}'.split(',')}")
    private List<String> allowedOrigins;
    @Value("#{'${cors.allowed-methods}'.split(',')}")
    private List<String> allowedMethods;
    @Value("#{'${cors.allowed-headers}'.split(',')}")
    private List<String> allowedHeaders;
    @Value("#{'${cors.exposed-headers}'.split(',')}")
    private List<String> exposedHeaders;

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
//        CorsRegistration corsRegistration = registry.addMapping("/api/**");
//
//        corsRegistration.allowedOrigins(allowedOrigins);
//        corsRegistration.allowedMethods(allowedMethods);
//        corsRegistration.allowedHeaders(allowedHeaders);
//        corsRegistration.exposedHeaders(exposedHeaders);

//        allowedOrigins.forEach(corsRegistration::allowedOrigins);
//        allowedMethods.forEach(corsRegistration::allowedMethods);
//        allowedHeaders.forEach(corsRegistration::allowedHeaders);
//        exposedHeaders.forEach(corsRegistration::exposedHeaders);

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(allowedOrigins);
        configuration.setAllowedMethods(allowedMethods);
        configuration.setAllowedHeaders(allowedHeaders);
        configuration.setExposedHeaders(exposedHeaders);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }
}
