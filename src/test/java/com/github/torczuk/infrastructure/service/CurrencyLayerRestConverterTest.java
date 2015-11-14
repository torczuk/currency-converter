package com.github.torczuk.infrastructure.service;

import com.github.torczuk.domain.exception.CurrencyConversionException;
import com.github.torczuk.domain.service.CurrencyConverter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.UUID;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyLayerRestConverterTest {
    @Rule public  ExpectedException expectedException = ExpectedException.none();

    @Mock private CurrencyConverter currencyConverter;
    @InjectMocks private CurrencyLayerRestConverter currencyConverterService;

    @Before
    public void setUp() {
    }

    @Test
    public void shouldReturnAmountToConvertWhenSourceAndTargetCurrenciesAreTheSame() {
        String currency = "PLN";
        BigDecimal amount = someAmount();

        BigDecimal converted = currencyConverterService.convert(amount, currency, currency);

        assertThat(converted).isEqualTo(amount);
    }

    @Test
    public void shouldReturnZeroWhenAmountToConvertEqualsZero() {
        String sourceCurrency = someCurrency();
        String targetCurrency = someCurrency();
        BigDecimal amount = BigDecimal.ZERO;

        BigDecimal converted = currencyConverterService.convert(amount, sourceCurrency, targetCurrency);

        assertThat(converted).isEqualTo(amount);
    }

    @Test
    public void shouldThrowCurrencyConversionExceptionWhenAmountToConvertIsLessThanZero() {
        String sourceCurrency = someCurrency();
        String targetCurrency = someCurrency();
        BigDecimal amount = valueOf(-1);

        expectedException.expect(CurrencyConversionException.class);
        expectedException.expectMessage("Amount cannot be negative");
        currencyConverterService.convert(amount, sourceCurrency, targetCurrency);
    }

    @Test
    public void shouldReturnValidConversionForTwoCurrencies() {
        BigDecimal amount = BigDecimal.TEN;
        BigDecimal rate = BigDecimal.valueOf(5.124152);
        given(currencyConverter.convert("PLN", "USD")).willReturn(rate);

        BigDecimal converted = currencyConverterService.convert(amount, "PLN", "USD");

        assertThat(converted).isEqualTo(amount.multiply(rate));
    }

    private static BigDecimal someAmount() {
        return valueOf(UUID.randomUUID().hashCode()).abs();
    }

    private static String someCurrency() {
        return UUID.randomUUID().toString();
    }
}