package com.github.torczuk.infrastructure.service;

import com.github.torczuk.domain.exception.CurrencyConversionException;
import com.github.torczuk.domain.service.CurrencyConverterService;

import java.math.BigDecimal;

public class CurrencyLayerRestConverter implements CurrencyConverterService {
    @Override public BigDecimal convert(BigDecimal amount, String baseCurrency, String targetCurrency) {
        if(amount.signum() == -1) {
            throw new CurrencyConversionException("Amount cannot be negative");
        }
        if(baseCurrency.equals(targetCurrency)) {
            return amount;
        }
        if(BigDecimal.ZERO.equals(amount)) {
            return amount;
        }

        return BigDecimal.valueOf(-1);
    }
}
