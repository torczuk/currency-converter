package com.github.torczuk.domain.service;

import com.github.torczuk.domain.exception.CurrencyConversionException;
import com.github.torczuk.domain.model.Conversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class CurrencyConverterService {

    private final CurrencyConverter currencyConverter;
    private final ConversionHistoryService conversionHistoryService;

    @Autowired
    public CurrencyConverterService(CurrencyConverter currencyConverter, ConversionHistoryService conversionHistoryService) {
        this.currencyConverter = currencyConverter;
        this.conversionHistoryService = conversionHistoryService;
    }

    public BigDecimal convert(BigDecimal amount, String baseCurrency, String targetCurrency) {
        if (amount.signum() == -1) {
            throw new CurrencyConversionException("Amount cannot be negative");
        }
        BigDecimal converted;
        if (theSameOrZeroConversion(amount, baseCurrency, targetCurrency)) {
            converted = amount;
        } else {
            converted = amount.multiply(currencyConverter.convert(baseCurrency, targetCurrency));
        }
        Conversion conversion = new Conversion(new Date().getTime(), null, amount.toString(), converted.toString(), baseCurrency, targetCurrency);
        conversionHistoryService.saveUserConversion(conversion);
        return converted;
    }

    private static boolean theSameOrZeroConversion(BigDecimal amount, String baseCurrency, String targetCurrency) {
        return baseCurrency.equals(targetCurrency) || BigDecimal.ZERO.equals(amount);
    }
}
