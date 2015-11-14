package com.github.torczuk.infrastructure.facade;

import com.github.torczuk.domain.service.CurrencyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;

@Service
public class USDBasedCurrencyConverter implements CurrencyConverter {
    private static final String USD = "USD";

    private final CurrencyConverterFacade currencyConverterFacade;

    @Autowired
    public USDBasedCurrencyConverter(CurrencyConverterFacade currencyConverterFacade) {
        this.currencyConverterFacade = currencyConverterFacade;
    }

    @Override
    public BigDecimal convert(String base, String target) {
        JsonUsdCurrencyRates usdRates = currencyConverterFacade.latestUsdCurrencyRates();
        if(base.equals(USD)) {
            return usdRates.rateFor(target);
        } else if(target.equals(USD)) {
            BigDecimal convertedAmount = usdRates.rateFor(base);
            return ONE.divide(convertedAmount, 6, BigDecimal.ROUND_CEILING);
        } else {
            BigDecimal usdToBase = usdRates.rateFor(base);
            BigDecimal usdToTarget = usdRates.rateFor(target);
            return usdToTarget.divide(usdToBase, 6, BigDecimal.ROUND_CEILING);
        }
    }
}
