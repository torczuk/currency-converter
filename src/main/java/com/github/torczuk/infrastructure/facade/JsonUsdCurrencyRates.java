package com.github.torczuk.infrastructure.facade;

import org.json.JSONObject;

import java.math.BigDecimal;

public class JsonUsdCurrencyRates {
    private String currencyRates;

    public JsonUsdCurrencyRates(String currencyRates) {
        this.currencyRates = currencyRates;
    }

    public BigDecimal rateFor(String currency) {
        JSONObject json = new JSONObject(currencyRates);
        JSONObject conversions = json.getJSONObject("quotes");
        String conversionFormat = String.format("USD%s", currency);
        BigDecimal convertedAmount = null;
        if(conversions.has(conversionFormat)) {
            convertedAmount = conversions.getBigDecimal(conversionFormat);
        }
        return convertedAmount;
    }
}
