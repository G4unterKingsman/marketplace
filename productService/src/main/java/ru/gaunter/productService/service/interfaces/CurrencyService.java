package ru.gaunter.productService.service.interfaces;

import java.math.BigDecimal;

public interface CurrencyService {

    BigDecimal getUsdRate();

    BigDecimal getUsdPreviousRate();

    BigDecimal getRubRate();
}
