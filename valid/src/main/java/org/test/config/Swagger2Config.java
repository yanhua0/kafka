package org.test.config;

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
@EnableSwagger2 //swagger注解，，开启
public class Swagger2Config {
    @Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.test.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder()
                .title("接口文档")
//                .description("springboot swagger2")
                /*.termsOfServiceUrl("http://blog.csdn.net/u014231523网址链接")
                .contact(new Contact("diaoxingguo", "http://blog.csdn.net/u014231523", "diaoxingguo@163.com"))*/
                .build();

    }
    //2.0以后使用ApiInfoBuilder().build();
    @Bean
    public ApiInfo buildApiInfo(){
        return new ApiInfoBuilder().build();
    }
}

