package com.google.gwt.sample.stockwatcher.server;

import com.google.gwt.sample.stockwatcher.client.CurrencyService;
import com.google.gwt.sample.stockwatcher.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CurrencyServiceImpl extends RemoteServiceServlet implements
        CurrencyService {

    @Override
    public String addCurrency(String symbol) throws IllegalArgumentException {
        // Verify that the input is valid.
        if (!FieldVerifier.isValidName(symbol)) {
            // If the input is not valid, throw an IllegalArgumentException back to
            // the client.
            throw new IllegalArgumentException(
                    "Name must be max 4 characters long");
        }

        return null;
    }

    @Override
    public String deleteCurrency(String symbol) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void refresh() throws IllegalArgumentException {

    }

    /**
     * Escape an html string. Escaping data received from the client helps to
     * prevent cross-site script vulnerabilities.
     *
     * @param html the html string to escape
     * @return the escaped string
     */
    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
                ">", "&gt;");
    }
}