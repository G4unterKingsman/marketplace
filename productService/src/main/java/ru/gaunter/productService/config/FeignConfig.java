package ru.gaunter.productService.config;

import feign.Response;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }


    @Bean
    public Decoder feignDecoder() {
        ObjectFactory<HttpMessageConverters> messageConverters = () ->
                new HttpMessageConverters(new MappingJackson2HttpMessageConverter() {{
                    setSupportedMediaTypes(List.of(
                            MediaType.APPLICATION_JSON,
                            MediaType.valueOf("application/javascript")
                    ));
                }});

        return new ResponseEntityDecoder(new SpringDecoder(messageConverters));
    }

    static class CustomErrorDecoder implements ErrorDecoder {
        @Override
        public Exception decode(String methodKey, Response response) {
            return new RuntimeException("Error from CBR API: " + response.status());
        }

    }
}
