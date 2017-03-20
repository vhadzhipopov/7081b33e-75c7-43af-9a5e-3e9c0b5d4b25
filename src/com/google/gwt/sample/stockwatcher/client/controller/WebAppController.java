package com.google.gwt.sample.stockwatcher.client.controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.*;
import com.google.gwt.sample.stockwatcher.client.event.AddCurrencyEvent;
import com.google.gwt.sample.stockwatcher.client.event.DeleteAllCurrencyEvent;
import com.google.gwt.sample.stockwatcher.client.event.DeleteCurrencyEvent;
import com.google.gwt.sample.stockwatcher.client.event.LoadEvent;
import com.google.gwt.sample.stockwatcher.client.json.JsonHelper;
import com.google.gwt.sample.stockwatcher.client.model.ModelHandler;
import com.google.gwt.sample.stockwatcher.client.ui.MainPanel;
import com.google.gwt.sample.stockwatcher.shared.Currency;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;

import java.util.List;

/**
 * Web App Controller Manage all business events and communicate with server
 * services
 */
public class WebAppController {

    /**
     * Event Bus
     */
    private SimpleEventBus eventBus;

    /**
     * Model Handler
     */
    private ModelHandler modelHandler;

    /**
     * main panel UI
     */
    private MainPanel mainPanel;

    @Inject
    public WebAppController(SimpleEventBus eventBus, ModelHandler modelHandler, MainPanel mainPanel) {
        this.eventBus = eventBus;
        this.modelHandler = modelHandler;
        this.mainPanel = mainPanel;
    }

    /**
     * Bind all events handler
     */
    public void bindHandlers() {
        this.eventBus.addHandler(AddCurrencyEvent.TYPE, event -> addCurrency(event.getCurrencySymbol()));

        this.eventBus.addHandler(DeleteCurrencyEvent.TYPE, event -> deleteCurrency(event.getCurrency()));

        this.eventBus.addHandler(DeleteAllCurrencyEvent.TYPE, event -> deleteAll());

        this.eventBus.addHandler(LoadEvent.TYPE, event -> loadCurrencyList());
    }

    /**
     * get currency list from model and reload UI
     *
     * @param list list of currencies
     */
    private void reloadList(List<Currency> list) {
        this.modelHandler.reloadAll(list);
        this.mainPanel.reloadCurrencyList();
    }

    /**
     * ask server for stored Currency list
     */
    private void loadCurrencyList() {
        String pageBaseUrl = GWT.getHostPageBaseURL();
        RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, pageBaseUrl + "/api/currencies/");
        rb.setCallback(new RequestCallback() {

            public void onError(Request request, Throwable e) {
                // some error handling code here
                Window.alert("error = " + e.getMessage());
            }

            public void onResponseReceived(Request request, Response response) {
                if (200 == response.getStatusCode()) {
                    List<Currency> currencyList = JsonHelper.parseDataList(response.getText());
                    reloadList(currencyList);
                }
            }
        });
        try {
            rb.send();
        } catch (RequestException e) {
            e.printStackTrace();
            Window.alert("error = " + e.getMessage());
        }
    }

    /**
     * delete all currency from model and UI
     */
    private void deleteAll() {
        this.modelHandler.removeAll();
        this.mainPanel.removeAllCurrency();
    }

    /**
     * delete a currency (ui & model)
     *
     * @param currency currency
     */
    private void deleteCurrency(Currency currency) {
        this.modelHandler.remove(currency);
        this.mainPanel.removeCurrencyFromPanel(currency);
    }

    /**
     * create and add a currency with given label
     *
     * @param currencySymbol currency symbol
     */
    private void addCurrency(String currencySymbol) {
        Currency currency = new Currency(currencySymbol);
        this.modelHandler.add(currency);
        this.mainPanel.addCurrencyToPanel(currency);
    }
}
