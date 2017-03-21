package com.google.gwt.sample.stockwatcher.server.jpa;

import com.google.gwt.sample.stockwatcher.shared.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by vhadzhipopov on 20.03.17.
 */
public interface CurrencyRepository extends JpaRepository<Currency, String> {
    Optional<Currency> getBySymbol(String id);
}
