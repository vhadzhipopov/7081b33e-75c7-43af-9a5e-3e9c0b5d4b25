package com.google.gwt.sample.stockwatcher.client.json;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.sample.stockwatcher.shared.Currency;

import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    @SuppressWarnings("unchecked")
    public static List<Currency> parseDataList(String json) {
        List<Currency> currencyList = new ArrayList<>();
        JSONValue jsonVal = JSONParser.parseStrict(json);
        JSONArray object = jsonVal.isArray();
        JsArray<JsCurrency> array = (JsArray<JsCurrency>) object.getJavaScriptObject();
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                JsCurrency jsCurrency = array.get(i);
                currencyList.add(new Currency(jsCurrency.symbol()));
            }
        }
        return currencyList;
    }
}
