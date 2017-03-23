package com.google.gwt.sample.stockwatcher.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.sample.stockwatcher.shared.Currency;

import java.util.List;

public class ReloadedEvent extends GwtEvent<ReloadedEventHandler> {

    public static Type<ReloadedEventHandler> TYPE = new Type<>();
    private List<Currency> currencyList;

    public ReloadedEvent(List<Currency> currencyList) {

        this.currencyList = currencyList;
    }

    @Override
    public Type<ReloadedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ReloadedEventHandler handler) {
        handler.onReloadedEventHandler(this);
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }
}
