package com.github.torczuk.domain.model;

import javax.persistence.Entity;

@Entity
public class Conversion extends AbstractEntity {

    private Long timestamp;
    private Long userId;
    private String amount;
    private String baseCurrency;
    private String targetCurrency;

    public Conversion() {
    }

    public Conversion(Long timestamp, Long userId, String amount, String baseCurrency, String targetCurrency) {
        this.timestamp = timestamp;
        this.userId = userId;
        this.amount = amount;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
}
