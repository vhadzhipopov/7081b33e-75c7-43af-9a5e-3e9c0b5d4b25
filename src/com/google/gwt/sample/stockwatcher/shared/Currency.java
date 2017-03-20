package com.google.gwt.sample.stockwatcher.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by vhadzhipopov on 20.03.17.
 */

@Entity
public class Currency implements IsSerializable {
    @Id
    private String symbol;
    private BigDecimal price;
    private BigDecimal priceLastMonth;

    public Currency() {
    }

    public Currency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public Currency setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Currency setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getPriceLastMonth() {
        return priceLastMonth;
    }

    public Currency setPriceLastMonth(BigDecimal priceLastMonth) {
        this.priceLastMonth = priceLastMonth;
        return this;
    }
}
