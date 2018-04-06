package quicktrack;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author rameve02
 */
@Component
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("quicktrack.controller"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "BULK DATA REST API",
                "Bulk Data Rest End points.",
                "API TOS",
                "Espark Services",
                "Bulk Data Team",
                "Nielsen Private Limited",
                "http://www.nielsen.com/in/en.html");
        return apiInfo;
    }

}
