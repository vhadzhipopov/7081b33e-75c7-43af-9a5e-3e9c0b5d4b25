package com.google.gwt.sample.stockwatcher.client.controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.sample.stockwatcher.client.event.AddCurrencyEvent;
import com.google.gwt.sample.stockwatcher.client.event.DeleteAllCurrencyEvent;
import com.google.gwt.sample.stockwatcher.client.event.DeleteCurrencyEvent;
import com.google.gwt.sample.stockwatcher.client.event.LoadEvent;
import com.google.gwt.sample.stockwatcher.client.json.JsCurrency;
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
    private String hostPageBaseURL;

    @Inject
    public WebAppController(SimpleEventBus eventBus, ModelHandler modelHandler, MainPanel mainPanel) {
        this.eventBus = eventBus;
        this.modelHandler = modelHandler;
        this.mainPanel = mainPanel;
        hostPageBaseURL = GWT.getHostPageBaseURL();
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
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, hostPageBaseURL + "/api/currencies");
        builder.setCallback(new RequestCallback() {

            public void onError(Request request, Throwable e) {
                // some error handling code here
                Window.alert("error = " + e.getMessage());
            }

            public void onResponseReceived(Request request, Response response) {
                if (200 == response.getStatusCode()) {
                    List<Currency> currencyList = JsCurrency.parseDataList(response.getText());
                    reloadList(currencyList);
                }
            }
        });
        try {
            builder.send();
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
        currency.setVisible(false);
        sendVisibilityUpdate(currency);

    }

    /**
     * create and add a currency with given label
     *
     * @param currencySymbol currency symbol
     */
    private void addCurrency(String currencySymbol) {
        Currency currency = new Currency(currencySymbol, true, null, null, null);
        sendVisibilityUpdate(currency);
    }

    private void sendVisibilityUpdate(final Currency currency) {
        RequestBuilder builder = new RequestBuilder(RequestBuilder.PUT, hostPageBaseURL + "/api/currencies");

        //FIXME
        JSONObject requestData = new JSONObject();
        requestData.put("symbol", new JSONString(currency.getSymbol()));
        requestData.put("visible", new JSONString(currency.getVisible().toString()));
        builder.setRequestData(requestData.toString());
        builder.setHeader("Content-Type", "application/json");


        builder.setCallback(new RequestCallback() {

            public void onError(Request request, Throwable e) {
                // some error handling code here
                Window.alert("error = " + e.getMessage());
            }

            public void onResponseReceived(Request request, Response response) {
                Currency parsedCurrency = JsCurrency.parseData(response.getText());
                if (200 == response.getStatusCode()) {
                    if (parsedCurrency.getVisible()) {
                        modelHandler.add(parsedCurrency);
                        mainPanel.addCurrencyToPanel(parsedCurrency);
                    } else {
                        modelHandler.remove(parsedCurrency);
                        mainPanel.removeCurrencyFromPanel(parsedCurrency);
                    }
                }
            }
        });
        try {
            builder.send();
        } catch (RequestException e) {
            e.printStackTrace();
            Window.alert("error = " + e.getMessage());
        }
    }
}
