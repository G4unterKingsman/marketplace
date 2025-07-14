package ru.gaunter.productService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gaunter.productService.controller.rest.feign.CbrFeignClient;
import ru.gaunter.productService.dto.CbrResponseDto;
import ru.gaunter.productService.dto.Currency;
import ru.gaunter.productService.service.interfaces.CurrencyService;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CbrFeignClient cbrFeignClient;
    @Override
    public BigDecimal getUsdRate() {
        CbrResponseDto response = cbrFeignClient.getCurrencyRates();
        Currency usd = response.getValute().get("USD");

        return usd.getValue();
    }

    @Override
    public BigDecimal getUsdPreviousRate() {
        CbrResponseDto response = cbrFeignClient.getCurrencyRates();
        Currency usd = response.getValute().get("USD");

        return usd.getPrevious();
    }
    @Override
    public BigDecimal getRubRate() {
        CbrResponseDto response = cbrFeignClient.getCurrencyRates();
        Currency rub = response.getValute().get("RUB");

        return rub.getValue().divide(BigDecimal.valueOf(rub.getNominal()), 4, RoundingMode.HALF_UP);
    }
}
