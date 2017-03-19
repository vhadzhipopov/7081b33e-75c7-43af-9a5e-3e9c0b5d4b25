package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>CurrencyService</code>.
 */
public interface CurrencyServiceAsync {
    void addCurrency(String symbol, AsyncCallback<String> async);

    void deleteCurrency(String symbol, AsyncCallback<String> async);

    void refresh(AsyncCallback<Void> async);
}
