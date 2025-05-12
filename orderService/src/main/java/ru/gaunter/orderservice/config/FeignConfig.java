package ru.gaunter.orderservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gaunter.orderservice.feignclient.interceptor.FeignClientInterceptor;

@Configuration
public class FeignConfig {

    @Bean
    public FeignClientInterceptor feignClientInterceptor() {
        return new FeignClientInterceptor();
    }
}