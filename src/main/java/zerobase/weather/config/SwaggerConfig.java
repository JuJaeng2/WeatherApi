package zerobase.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("zerobase.weather"))
//                .paths(PathSelectors.none()) // 아무것도 표시 안함
                .paths(PathSelectors.any()) // 모든 api 표시
//                .paths(PathSelectors.ant("/read/**")) // api 중 read로 시작하는 것들만 표시
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("날씨 일기 프로젝트 :)")
                .description("날씨 일기를 CRUD 할 수 있는 백엔드 API 입니다.")
                .version("2.0")
                .build();
    }
}
