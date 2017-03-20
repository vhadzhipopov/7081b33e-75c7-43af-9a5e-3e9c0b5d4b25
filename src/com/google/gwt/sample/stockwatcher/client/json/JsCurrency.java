package com.google.gwt.sample.stockwatcher.client.json;

import com.google.gwt.core.client.JavaScriptObject;

public class JsCurrency extends JavaScriptObject {

    protected JsCurrency() {
    }

    public native final String symbol() /*-{
        return this.symbol;
    }-*/;
}
