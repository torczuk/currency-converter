package com.github.torczuk.domain.service;

import com.github.torczuk.domain.exception.CurrencyConversionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyConverterService {

    private final CurrencyConverter currencyConverter;

    @Autowired
    public CurrencyConverterService(CurrencyConverter currencyConverter) {
        this.currencyConverter = currencyConverter;
    }

    public BigDecimal convert(BigDecimal amount, String baseCurrency, String targetCurrency) {
        if(amount.signum() == -1) {
            throw new CurrencyConversionException("Amount cannot be negative");
        }
        if(baseCurrency.equals(targetCurrency)) {
            return amount;
        }
        if(BigDecimal.ZERO.equals(amount)) {
            return amount;
        }
        return amount.multiply(currencyConverter.convert(baseCurrency, targetCurrency));
    }
}
