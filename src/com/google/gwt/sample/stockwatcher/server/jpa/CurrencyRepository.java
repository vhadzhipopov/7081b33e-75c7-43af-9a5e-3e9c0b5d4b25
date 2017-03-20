package com.google.gwt.sample.stockwatcher.server.jpa;

import com.google.gwt.sample.stockwatcher.shared.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by vhadzhipopov on 20.03.17.
 */
@RepositoryRestResource
public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
