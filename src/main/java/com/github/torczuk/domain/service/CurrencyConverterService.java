package com.github.torczuk.domain.service;

import com.github.torczuk.domain.exception.CurrencyConversionException;

import java.math.BigDecimal;

public interface CurrencyConverterService {
    BigDecimal convert(BigDecimal amount, String baseCurrency, String targetCurrency) throws CurrencyConversionException;
}
