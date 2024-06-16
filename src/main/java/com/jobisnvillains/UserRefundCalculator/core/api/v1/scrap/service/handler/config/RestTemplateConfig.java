package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.service.handler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    private final static int READ_TIME_OUT_MILLISECONDS = 3000;
    private final static int CONNECT_TIME_OUT_MILLISECONDS = 3000;

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(READ_TIME_OUT_MILLISECONDS);
        factory.setReadTimeout(CONNECT_TIME_OUT_MILLISECONDS);
        return new RestTemplate(factory);
    }
}
