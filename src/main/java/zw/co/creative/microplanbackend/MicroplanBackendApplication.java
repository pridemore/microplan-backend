package zw.co.creative.microplanbackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.time.Duration;

@SpringBootApplication
public class MicroplanBackendApplication {
    @Value("${http.client.connectTimeout}")
    private Duration connectTimeout;
    @Value("${http.client.readTimeout}")
    private Duration readTimeout;

    public static void main(String[] args) {
        SpringApplication.run(MicroplanBackendApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.setConnectTimeout(connectTimeout)
           .setReadTimeout(readTimeout)
           .build();
    }

    @Bean
    public ClassLoaderTemplateResolver thymeleafTemplateResolver() {

        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        resolver.setCacheable(false);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

}
