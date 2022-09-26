package zw.co.creative.microplanbackend.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;



@Configuration
public class SwaggerConfigs {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .apiInfo(metadata());
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("Creative Microplan")
                .description("Creative Microplan Form APIs")
                .version("1.0.0")
                .contact(new Contact("Creative", "https://www.creative.co.zw/", "info@creative.co.zw"))
                .license("API Licence")
                .build();
    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }

}
