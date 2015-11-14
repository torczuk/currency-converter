package com.github.torczuk.infrastructure.facade;

import com.github.torczuk.util.Fakes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class USDBasedCurrencyConverterTest {

    private @Mock CurrencyConverterFacade currencyConverterFacade;
    private @InjectMocks USDBasedCurrencyConverter usdBasedCurrencyConverter;

    @Before
    public void setUp() {
        given(currencyConverterFacade.latestUsdCurrencyRates()).willReturn(new JsonUsdCurrencyRates(Fakes.CURRENCY_LAYER_RESPONSE));
    }

    @Test
    public void shouldReturnValidConvertedAmountWhereBasicCurrencyIsUSD() {
        String sourceCurrency = "USD";
        String targetCurrency = "PLN";

        BigDecimal converted = usdBasedCurrencyConverter.convert(sourceCurrency, targetCurrency);

        assertThat(converted).isEqualTo(valueOf(3.94005));
    }

    @Test
    public void shouldReturnValidConvertedAmountWhenTargetCurrencyIsUSD() {
        String sourceCurrency = "PLN";
        String targetCurrency = "USD";

        BigDecimal converted = usdBasedCurrencyConverter.convert(sourceCurrency, targetCurrency);

        assertThat(converted).isEqualTo(valueOf(0.253804));
    }

    @Test
    public void shouldReturnValidConvertedAmountBetweenNonUSDCurrencies() {
        String sourceCurrency = "PLN";
        String targetCurrency = "EUR";

        BigDecimal converted = usdBasedCurrencyConverter.convert(sourceCurrency, targetCurrency);

        assertThat(converted).isEqualTo(valueOf(0.235681));
    }
}
