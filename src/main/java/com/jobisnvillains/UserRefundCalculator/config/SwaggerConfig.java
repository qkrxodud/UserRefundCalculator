package com.jobisnvillains.UserRefundCalculator.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo())
                .components(certificationJwt())
                .addSecurityItem(addSecurityItem());
    }

    private Info apiInfo() {
        return new Info().title("사용자 환급금 조회서비스")
                .description("사용자의 환급금을 조회하는 서비스")
                .version("1.0.0");
    }

    private Components certificationJwt() {
        return new Components().addSecuritySchemes("bearer-jwt", new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .name("JWT Authentication")
                .scheme("bearer")
                .bearerFormat("JWT"));
    }

    private SecurityRequirement addSecurityItem() {
        return new SecurityRequirement().addList("bearer-jwt");
    }
}
