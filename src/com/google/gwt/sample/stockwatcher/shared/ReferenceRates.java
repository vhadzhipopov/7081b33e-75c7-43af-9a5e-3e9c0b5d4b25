package com.google.gwt.sample.stockwatcher.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.Map;

/**
 * Created by vhadzhipopov on 20.03.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferenceRates {
    private String base;
    private Date date;
    private Map<String, Double> rates;

    @Override
    public String toString() {
        return "ReferenceRates{" +
                "base='" + base + '\'' +
                ", date=" + date +
                ", rates=" + rates +
                '}';
    }

    public String getBase() {
        return base;
    }

    public ReferenceRates setBase(String base) {
        this.base = base;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public ReferenceRates setDate(Date date) {
        this.date = date;
        return this;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public ReferenceRates setRates(Map<String, Double> rates) {
        this.rates = rates;
        return this;
    }
}
