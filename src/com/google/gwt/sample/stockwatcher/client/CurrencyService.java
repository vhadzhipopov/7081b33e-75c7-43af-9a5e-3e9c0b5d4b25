package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("currency")
public interface CurrencyService extends RemoteService {
    String addCurrency(String symbol) throws IllegalArgumentException;

    String deleteCurrency(String symbol) throws IllegalArgumentException;

    void refresh() throws IllegalArgumentException;
}
