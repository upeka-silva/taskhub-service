package lk.project.taskhub.secuirty.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCrosConfig implements WebMvcConfigurer {

    @Value("${frontend.url}")
    private String frontendUrl;

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // This is a boilerplate code for configuring CORS.
                // I initially tried using a more general mapping like `addMapping("/api/v1/**")`, but it didn't work as expected.
                // Specifically, it didn't handle the CORS headers and methods correctly,
                // so I had to explicitly define mappings for each endpoint (tasks, users, auth).
                // This ensures proper handling of allowed origins, methods, and headers like
                // "Authorization" and "Content-Type", which are critical for frontend-backend communication.
                registry.addMapping("/api/v1/tasks/**")
                        .allowedOrigins(frontendUrl)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowedHeaders("Authorization", "Content-Type")
                        .allowCredentials(true);
                registry.addMapping("/api/v1/users/**")
                        .allowedOrigins(frontendUrl)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowedHeaders("Authorization", "Content-Type")
                        .allowCredentials(true);

                registry.addMapping("/api/v1/auth/**")
                        .allowedOrigins(frontendUrl)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowedHeaders("Authorization", "Content-Type")
                        .allowCredentials(true);
            }
        };
    }

}
