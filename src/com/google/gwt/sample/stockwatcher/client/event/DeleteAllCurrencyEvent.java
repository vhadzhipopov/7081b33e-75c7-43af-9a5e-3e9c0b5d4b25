package com.google.gwt.sample.stockwatcher.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class DeleteAllCurrencyEvent extends GwtEvent<DeleteAllCurrencyEventHandler> {

    public static Type<DeleteAllCurrencyEventHandler> TYPE = new Type<>();

    public DeleteAllCurrencyEvent() {

    }

    @Override
    protected void dispatch(DeleteAllCurrencyEventHandler handler) {
        handler.onDeleteAllCurrencyEventHandler(this);
    }

    @Override
    public Type<DeleteAllCurrencyEventHandler> getAssociatedType() {
        return TYPE;
    }


}
