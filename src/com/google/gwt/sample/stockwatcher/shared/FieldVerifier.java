package com.google.gwt.sample.stockwatcher.shared;

import com.google.gwt.regexp.shared.RegExp;

/**
 * <p>
 * FieldVerifier validates that the symbol the user enters is valid.
 * </p>
 * <p>
 * This class is in the <code>shared</code> package because we use it in both
 * the client code and on the server. On the client, we verify that the symbol is
 * valid before sending an RPC request so the user doesn't have to wait for a
 * network round trip to get feedback. On the server, we verify that the symbol is
 * correct to ensure that the input is correct regardless of where the RPC
 * originates.
 * </p>
 * <p>
 * When creating a class that is used on both the client and the server, be sure
 * that all code is translatable and does not use native JavaScript. Code that
 * is not translatable (such as code that interacts with a database or the file
 * system) cannot be compiled into client-side JavaScript. Code that uses native
 * JavaScript (such as Widgets) cannot be run on the server.
 * </p>
 */
public class FieldVerifier {

    /**
     * Verifies that the specified symbol is valid for our service.
     * <p>
     * In this example, we only require that the symbol is at least four
     * characters. In your application, you can use more complex checks to ensure
     * that usernames, passwords, email addresses, URLs, and other fields have the
     * proper syntax.
     *
     * @param symbol the symbol to validate
     * @return true if valid, false if invalid
     */
    public static boolean isValidSymbol(String symbol) {
        return symbol != null && RegExp.compile("[A-Z]{3}").exec(symbol) != null;
    }
}
