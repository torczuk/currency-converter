package com.github.torczuk.infrastructure.facade;

import com.github.torczuk.Application;
import com.github.torczuk.TestApplicationConfig;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes =  {Application.class, TestApplicationConfig.class})
public class ApiLayerCurrencyConverterFacadeIntegrationTest {

    private @Autowired ApiLayerCurrencyConverterFacade facade;

    @Test
    public void shouldRequestAllCurrenciesAvailableUnderCityDictionary() {
        String supportedCurrencies = facade.getSupportedCurrencies();

        assertThat(supportedCurrencies).isEqualTo("GBP,CNY,EUR,INR,JPY,PLN,CHF,USD");
    }
}
