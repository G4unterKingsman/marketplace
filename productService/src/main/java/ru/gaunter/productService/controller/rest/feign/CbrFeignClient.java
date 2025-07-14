package ru.gaunter.productService.controller.rest.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gaunter.productService.config.FeignConfig;
import ru.gaunter.productService.dto.CbrResponseDto;

@FeignClient(
        name = "cbr-client",
        url = "https://www.cbr-xml-daily.ru",
        configuration = FeignConfig.class)
public interface CbrFeignClient {

    @GetMapping("/daily_json.js")
    CbrResponseDto getCurrencyRates();
}
