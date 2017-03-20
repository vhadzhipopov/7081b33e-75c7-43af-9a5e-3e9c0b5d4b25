package com.google.gwt.sample.stockwatcher.client.ui.schedule;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.sample.stockwatcher.client.ui.MainPanel;
import com.google.gwt.sample.stockwatcher.shared.Currency;

import java.util.List;


/**
 * Deferred command to do incremental UI refresh
 * for reloading Currency in the UI
 *
 * @author AGI
 */
public class ReloadCurrencyListCommand implements ScheduledCommand {

    private List<Currency> currencyList;

    private MainPanel mainPanel;

    private int index;

    public ReloadCurrencyListCommand(List<Currency> list, MainPanel mainPanel) {
        this.currencyList = list;
        this.mainPanel = mainPanel;
        this.index = 0;
    }

    /**
     * incremental add currency to the panel
     * executed after each call of Scheduler.get().scheduleDeferred(this)
     */
    @Override
    public void execute() {
        if (index < currencyList.size()) {
            Currency currency = currencyList.get(index);
            mainPanel.addCurrencyToPanel(currency);
            // schedule for next iteration
            Scheduler.get().scheduleDeferred(this);
            index++;
        }
    }

}
