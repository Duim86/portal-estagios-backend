package com.portaldeestagios.api.configs;


import com.google.common.net.HttpHeaders;
import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//            .paths(PathSelectors.any())
            .build()
//            .useDefaultResponseMessages(false)
//            .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
            .securitySchemes(Collections.singletonList(new ApiKey("Token Access", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
            .securityContexts(Collections.singletonList(securityContext()))
            .apiInfo(createApiInfo())
            .tags(new Tag("Students", "Gerencia os estudantes"));
  }

//  private List<ResponseMessage> globalGetResponseMessages() {
//    return Arrays.asList(
//            new ResponseMessageBuilder()
//            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
//            .message("Erro interno do servidor")
//            .build(),
//            new ResponseMessageBuilder()
//                    .code(HttpStatus.NOT_ACCEPTABLE.value())
//                    .message("Recurso não possui representação que poderia ser aceita")
//                    .build()
//    );
//  }

  private SecurityContext securityContext(){
    return SecurityContext.builder()
              .securityReferences(defaultAuth())
              .forPaths(PathSelectors.any())
              .build();
  }

  private List<SecurityReference> defaultAuth(){
    AuthorizationScope authorizationScope
            = new AuthorizationScope("ADMIN", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Collections.singletonList(new SecurityReference("Token Access", authorizationScopes));
  }

  private ApiInfo createApiInfo(){
    return new ApiInfoBuilder()
            .title("Documentação Portal de Estágios - Spring Boot REST API")
            .description("API de gerenciamento do Portal de Estágios do ITAI")
            .version("1.0.0")
            .contact(new Contact("ITAI - Instituto de Tecnologia Aplicada e Inovação", "https://www.itai.org.br", "contato@itai.org.br"))
            .build();
  }
}