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
                registry.addMapping("/**").
                        allowedOrigins(frontendUrl).
                        allowedMethods("GET","POST","PUT","DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true).maxAge(3600);
            }
        };
    }










}
