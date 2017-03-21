package com.google.gwt.sample.stockwatcher.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by vhadzhipopov on 20.03.17.
 */

@Entity
public class Currency implements IsSerializable {
    @Id
    private String symbol;
    private Boolean visible;
    private Double price;
    private Double priceLastMonth;
    private Date lastUpdated;
    public Currency() {
    }

    public Currency(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "symbol='" + symbol + '\'' +
                ", visible=" + visible +
                ", price=" + price +
                ", priceLastMonth=" + priceLastMonth +
                ", lastUpdated=" + lastUpdated +
                '}';
    }

    public String getSymbol() {
        return symbol;
    }

    public Currency setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Boolean getVisible() {
        return visible;
    }

    public Currency setVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Currency setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getPriceLastMonth() {
        return priceLastMonth;
    }

    public Currency setPriceLastMonth(Double priceLastMonth) {
        this.priceLastMonth = priceLastMonth;
        return this;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public Currency setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }
}
