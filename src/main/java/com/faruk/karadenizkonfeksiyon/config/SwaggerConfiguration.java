/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.config;

import com.google.common.base.Predicates;
import io.swagger.annotations.SwaggerDefinition;
import java.awt.print.Pageable;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Faruk
// */
//@Configuration
//@EnableSwagger2
//public class SwaggerConfiguration {
//        
//    private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);
//    
//    @Bean
//    public Docket swaggerSpringMvcPlugin() {
//        log.debug("Starting Swagger");
//        return new Docket(DocumentationType.SWAGGER_2)
//                .useDefaultResponseMessages(false)
//                .apiInfo(apiInfo())
//                .select()
//                .paths(Predicates.not(PathSelectors.regex("/error.*")))
//                .build();
//    }
//    
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//            .title("Spring Swagger test")
//            .description("SwaggerTest")
//            .contact(new Contact("Faruk", "http://farukkaradeniz.com", "karadenizfaruk28@gmail.com"))
//            .license("Apache License Version 2.0")
//            .licenseUrl("http://farukkaradeniz.com")
//            .version("2.0")
//            .build();
//    }
//    
//}
@SwaggerDefinition()
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

   private final String controllerPackage = "com.faruk.karadenizkonfeksiyon.web.rest";
   private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

   private Integer port=3428;

   @Bean
   public Docket swaggerSpringfoxDocket() {

       log.debug("Starting Swagger");
       StopWatch watch = new StopWatch();
       watch.start();

       Docket docket = new Docket(DocumentationType.SWAGGER_2);
       docket.forCodeGeneration(true);
       docket.apiInfo(createApiInfo());
       docket.genericModelSubstitutes(ResponseEntity.class);
       docket.ignoredParameterTypes(Pageable.class);
       docket.directModelSubstitute(java.time.OffsetDateTime.class, java.sql.Date.class);
       docket.directModelSubstitute(UUID.class, UUID.class);

       ApiSelectorBuilder asp = docket.select();
       asp.apis(RequestHandlerSelectors.basePackage(controllerPackage));
       asp.paths(PathSelectors.any());

       docket = asp.build();

       watch.stop();
       log.debug("http://localhost:{}/swagger-ui.html", port);
       log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
       return docket;
   }

   private ApiInfo createApiInfo() {

       ApiInfoBuilder builder = new ApiInfoBuilder();
       builder.contact(new Contact("Şahap Aşcı", "", "sahapasci@bulutyazilim.com"));
       builder.description("Backend of Websites");
       builder.license("Apache License Version 2.0"); // TODO license?
       builder.termsOfServiceUrl("http://contentfarm.io/terms");
       builder.title("The ContentFarm Product API");
       builder.version("1.2");

       return builder.build();
   }
}