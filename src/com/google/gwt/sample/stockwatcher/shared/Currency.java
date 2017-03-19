package com.google.gwt.sample.stockwatcher.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.math.BigDecimal;

/**
 * Created by vhadzhipopov on 20.03.17.
 */
public class Currency implements IsSerializable {
    private String symbol;
    private BigDecimal price;
    private BigDecimal priceLastMonth;

    public Currency() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceLastMonth() {
        return priceLastMonth;
    }

    public void setPriceLastMonth(BigDecimal priceLastMonth) {
        this.priceLastMonth = priceLastMonth;
    }
}
