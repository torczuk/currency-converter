package com.github.torczuk.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Conversion extends AbstractEntity implements Comparable<Conversion> {

    @Column(nullable = false) private Long timestamp;
    @Column(nullable = false) private Long userId;
    @Column(nullable = false) private String amount;
    @Column(nullable = false) private String convertedAmount;
    @Column(nullable = false) private String baseCurrency;
    @Column(nullable = false) private String targetCurrency;

    public Conversion() {
    }

    public Conversion(Long timestamp, Long userId, String amount, String convertedAmount, String baseCurrency, String targetCurrency) {
        this.timestamp = timestamp;
        this.userId = userId;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
    }

    public Conversion(Long id, Long timestamp, Long userId, String amount, String convertedAmount, String baseCurrency, String targetCurrency) {
        this(timestamp, userId, amount, convertedAmount, baseCurrency, targetCurrency);
        setUserId(id);
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

    public String getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(String convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public String getDate() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(new Date(timestamp));
    }

    @Override public int compareTo(Conversion conversion) {
        return conversion.timestamp.compareTo(this.timestamp);
    }
}
