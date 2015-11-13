package com.github.torczuk.infrastructure.service;

import com.github.torczuk.domain.exception.CurrencyConversionException;
import com.github.torczuk.domain.service.CurrencyConverterService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyLayerRestConverterTest {

    public @Rule ExpectedException expectedException = ExpectedException.none();
    private CurrencyConverterService currencyConverterService = new CurrencyLayerRestConverter();

    @Test
    public void shouldReturnAmountToConvertWhenSourceAndTargetCurrenciesAreTheSame() {
        String currency = "PLN";
        BigDecimal amount = someAmount();

        BigDecimal converted = currencyConverterService.convert(amount, currency, currency);

        assertThat(amount).isEqualTo(converted);
    }

    @Test
    public void shouldReturnZeroWhenAmountToConvertEqualsZero() {
        String sourceCurrency = someCurrency();
        String targetCurrency = someCurrency();
        BigDecimal amount = BigDecimal.ZERO;

        BigDecimal converted = currencyConverterService.convert(amount, sourceCurrency, targetCurrency);

        assertThat(amount).isEqualTo(converted);
    }

    @Test
    public void shouldThrowCurrencyConversionExceptionWhenAmountToConvertIsLessThanZero() {
        String sourceCurrency = someCurrency();
        String targetCurrency = someCurrency();
        BigDecimal amount = BigDecimal.valueOf(-1);

        expectedException.expect(CurrencyConversionException.class);
        expectedException.expectMessage("Amount cannot be negative");
        currencyConverterService.convert(amount, sourceCurrency, targetCurrency);
    }

    private static BigDecimal someAmount() {
        return BigDecimal.valueOf(UUID.randomUUID().hashCode());
    }

    private static String someCurrency() {
        return UUID.randomUUID().toString();
    }
}