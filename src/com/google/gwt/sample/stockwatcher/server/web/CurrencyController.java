package com.google.gwt.sample.stockwatcher.server.web;

import com.google.gwt.sample.stockwatcher.server.jpa.CurrencyRepository;
import com.google.gwt.sample.stockwatcher.shared.Currency;
import com.google.gwt.sample.stockwatcher.shared.ReferenceRates;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by vhadzhipopov on 21.03.17.
 */
@RestController
public class CurrencyController {

    private final CurrencyRepository repository;

    public CurrencyController(CurrencyRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/currencies")
    public ResponseEntity<?> refresh() {
        RestTemplate restTemplate = new RestTemplate();

        ReferenceRates ratesToday = restTemplate.getForObject(getUri("latest"), ReferenceRates.class);
        ReferenceRates ratesLastMonth = restTemplate.getForObject(getUri(getAMonthAgo(ratesToday)), ReferenceRates.class);

        ratesToday.getRates().keySet().forEach(symbol -> {
            Currency currency = repository.getBySymbol(symbol).orElse(new Currency(symbol));
            currency.setLastUpdated(ratesToday.getDate());
            currency.setPrice(getPrice(ratesToday, symbol));
            currency.setPriceLastMonth(getPrice(ratesLastMonth, symbol));
            repository.save(currency);
        });

        return ResponseEntity.ok(repository.findAll());
    }

    private String getAMonthAgo(ReferenceRates ratesToday) {
        Date referenceDate = ratesToday.getDate();
        Calendar c = Calendar.getInstance();
        c.setTime(referenceDate);
        c.add(Calendar.MONTH, -1);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    // You can use "latest" or yyyy-MM-dd format
    private URI getUri(String date) {
        return UriComponentsBuilder
                .fromHttpUrl("http://api.fixer.io/{date}")
                .queryParam("base", "HKD")
                .buildAndExpand(date)
                .toUri();
    }

    private double getPrice(ReferenceRates ratesToday, String symbol) {
        return 1.0 / ratesToday.getRates().get(symbol);
    }
}


