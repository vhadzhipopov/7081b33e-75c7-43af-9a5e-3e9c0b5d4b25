package com.google.gwt.sample.stockwatcher.client.model;

import com.google.gwt.sample.stockwatcher.shared.Currency;

import java.util.ArrayList;
import java.util.List;

public class ModelHandler {

    List<Currency> currencyList;

    public ModelHandler() {
        currencyList = new ArrayList<>();
    }

    public void add(Currency currency) {
        currencyList.add(currency);
    }

    public void remove(Currency currency) {
        currencyList.remove(currency);
    }

    public void removeAll() {
        currencyList.clear();
    }

    public List<Currency> getAll() {
        return currencyList;
    }

    public void reloadAll(List<Currency> list) {
        currencyList.clear();
        for (Currency currency : list) {
            add(currency);
        }
    }

}
