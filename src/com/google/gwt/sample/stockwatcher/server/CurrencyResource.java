package com.google.gwt.sample.stockwatcher.server;

import com.google.gwt.sample.stockwatcher.shared.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vhadzhipopov on 18.03.17.
 */
@RestController
@RequestMapping("/api/currencies")
public class CurrencyResource {

    final static Logger logger = LoggerFactory.getLogger(CurrencyResource.class);

    List<Currency> currencyList = new ArrayList<>();

    public CurrencyResource() {
        currencyList.add(new Currency("HKD"));
        currencyList.add(new Currency("EUR"));
        currencyList.add(new Currency("USD"));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Currency> all() {
        return currencyList;
    }
}