package com.google.gwt.sample.stockwatcher.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddCurrencyEvent extends GwtEvent<AddCurrencyEventHandler> {

    public static Type<AddCurrencyEventHandler> TYPE = new Type<>();

    private String currencySymbol;

    public AddCurrencyEvent(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getCurrencySymbol() {
        return this.currencySymbol;
    }

    @Override
    protected void dispatch(AddCurrencyEventHandler handler) {
        handler.onAddCurrencyEventHandler(this);
    }

    @Override
    public Type<AddCurrencyEventHandler> getAssociatedType() {
        return TYPE;
    }

}
