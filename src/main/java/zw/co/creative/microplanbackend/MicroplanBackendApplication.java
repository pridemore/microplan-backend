package zw.co.creative.microplanbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MicroplanBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroplanBackendApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
