package zw.co.creative.microplanbackend.configs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.nio.file.Path;

@Configuration
@EnableWebMvc
class WebMvcConfiguration implements WebMvcConfigurer {
    @Value("${external.file.path}")
    private String  myExternalFilePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //String myExternalFilePath = "file:///C:/Users/pridemore.vhiriri/Creative_uploads/";
        //String myExternalFilePath = "file:///C:/Users/kafankalb/Creative_uploads/";

        registry.addResourceHandler("/Creative_uploads/**")
                .addResourceLocations(myExternalFilePath);
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("Creative_uploads")
                .addResourceLocations("/Users/pridemore.vhiriri/Creative_uploads")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }



}
