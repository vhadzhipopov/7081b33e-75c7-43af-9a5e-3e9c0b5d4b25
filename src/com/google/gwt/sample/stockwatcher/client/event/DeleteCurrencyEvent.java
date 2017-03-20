package com.google.gwt.sample.stockwatcher.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.sample.stockwatcher.shared.Currency;

public class DeleteCurrencyEvent extends GwtEvent<DeleteCurrencyEventHandler> {

    public static Type<DeleteCurrencyEventHandler> TYPE = new Type<>();

    Currency currency;

    public DeleteCurrencyEvent(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    protected void dispatch(DeleteCurrencyEventHandler handler) {
        handler.onDeleteCurrencyEventHandler(this);
    }

    @Override
    public Type<DeleteCurrencyEventHandler> getAssociatedType() {
        return TYPE;
    }

}
