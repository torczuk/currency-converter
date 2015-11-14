package com.github.torczuk.domain.service;

import java.math.BigDecimal;

public interface CurrencyConverter {
    BigDecimal convert(String base, String target);
}
