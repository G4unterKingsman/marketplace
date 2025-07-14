package ru.gaunter.productService.service.shedulers;


import com.fasterxml.jackson.core.io.BigDecimalParser;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.gaunter.productService.service.interfaces.CurrencyService;
import ru.gaunter.productService.service.interfaces.ProductService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class PriceUpdaterScheduler {

    private final CurrencyService currencyService;
    private final ProductService productService;

    @Scheduled(fixedDelay = 300_000)
    public void updatePrices() {
        BigDecimal usdCurrentRate = currencyService.getUsdRate();
        BigDecimal usdPreviousRate = currencyService.getUsdPreviousRate();
        BigDecimal rateRatio = usdCurrentRate.divide(usdPreviousRate, 4, RoundingMode.HALF_UP);
        productService.updateAllPrices(rateRatio);
    }
}
