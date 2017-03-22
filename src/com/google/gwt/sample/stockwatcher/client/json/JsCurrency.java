package com.google.gwt.sample.stockwatcher.client.json;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.sample.stockwatcher.shared.Currency;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsCurrency extends JavaScriptObject {

    protected JsCurrency() {
    }

    public static List<Currency> parseDataList(String json) {
        JsArray<JsCurrency> array = JsonUtils.safeEval(json);
        List<Currency> currencyList = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            currencyList.add(array.get(i).toPojo());
        }
        return currencyList;
    }

    public native final String symbol() /*-{
        return this.symbol;
    }-*/;

    public native final Boolean visible() /*-{
        return this.visible;
    }-*/;

    public native final Double price() /*-{
        return this.price;
    }-*/;

    public native final Double priceLastMonth() /*-{
        return this.priceLastMonth;
    }-*/;

    public native final Double lastUpdatedNative() /*-{
        return this.lastUpdated;
    }-*/;


    public final Date lastUpdated() {
        return new Date(lastUpdatedNative().longValue());
    }

    private Currency toPojo() {
        return new Currency(symbol(), visible(), price(), priceLastMonth(), lastUpdated());
    }

}
