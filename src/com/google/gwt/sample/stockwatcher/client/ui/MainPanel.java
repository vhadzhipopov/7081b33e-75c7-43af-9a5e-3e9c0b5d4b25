package com.google.gwt.sample.stockwatcher.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.sample.stockwatcher.client.event.AddCurrencyEvent;
import com.google.gwt.sample.stockwatcher.client.event.DeleteAllCurrencyEvent;
import com.google.gwt.sample.stockwatcher.client.event.LoadEvent;
import com.google.gwt.sample.stockwatcher.client.model.ModelHandler;
import com.google.gwt.sample.stockwatcher.client.ui.component.ImageButton;
import com.google.gwt.sample.stockwatcher.client.ui.schedule.ReloadCurrencyListCommand;
import com.google.gwt.sample.stockwatcher.shared.Currency;
import com.google.gwt.sample.stockwatcher.shared.FieldVerifier;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPanel extends Composite {

    private static MainPanelUiBinder uiBinder = GWT.create(MainPanelUiBinder.class);
    @UiField
    ImageButton addButton;
    @UiField
    ImageButton clearButton;
    @UiField
    ImageButton loadButton;
    @UiField
    TextBox textBox;
    @UiField
    Label errorLabel;
    @UiField
    FlowPanel currencyPanel;

    Map<String, CurrencyWidget> currencyWidgets;
    private SimpleEventBus eventBus;
    private ModelHandler modelHandler;

    @Inject
    public MainPanel(SimpleEventBus eventBus, ModelHandler modelHandler) {
        this.eventBus = eventBus;
        // init display
        initWidget(uiBinder.createAndBindUi(this));
        this.currencyWidgets = new HashMap<>();
        this.modelHandler = modelHandler;
    }

    @UiHandler("addButton")
    void onAddButtonClick(ClickEvent event) {
        // retrieve textbox text
        String currencyText = textBox.getText();
        // send it to controller for handle business event
        eventBus.fireEvent(new AddCurrencyEvent(currencyText));
    }

    @UiHandler("clearButton")
    void onClearButtonClick(ClickEvent event) {
        // ask controller for delete all event
        eventBus.fireEvent(new DeleteAllCurrencyEvent());
    }

    @UiHandler("loadButton")
    void onLoadButtonClick(ClickEvent event) {
        // ask controller for load event
        eventBus.fireEvent(new LoadEvent());
    }

    public void addCurrencyToPanel(Currency currency) {
        errorLabel.setText("");
        if (!FieldVerifier.isValidSymbol(currency.getSymbol())) {
            errorLabel.setText("Name must be max 4 characters long");
            return;
        }

        if (currencyWidgets.get(currency.getSymbol()) != null) {
            errorLabel.setText("Already existing Currency : " + currency.getSymbol());
            return;
        }

        // create a Currency
        CurrencyWidget w = new CurrencyWidget(currency, eventBus);
        // add it to panel
        currencyPanel.add(w);
        // keep a reference of the widget for later usage (see
        // removeCurrencyFromPanel)
        currencyWidgets.put(currency.getSymbol(), w);
    }

    public void removeCurrencyFromPanel(Currency currency) {
        // retrieve currency from the references
        CurrencyWidget currencyWidget = currencyWidgets.get(currency.getSymbol());
        // remove it from panel
        currencyPanel.remove(currencyWidget);

        currencyWidgets.remove(currency.getSymbol());
    }

    public void removeAllCurrency() {
        // clear currency panel
        currencyPanel.clear();
        // clear references
        currencyWidgets.clear();
    }

    public void reloadCurrencyList() {
        // clear all currency
        removeAllCurrency();
        // retrieve new model
        List<Currency> all = modelHandler.getAll();
        // usae defered command for incremental UI refresh
        if (all.size() > 0) {
            // create the command
            ReloadCurrencyListCommand reloadCommand = new ReloadCurrencyListCommand(all, this);
            // schedule the command call
            Scheduler.get().scheduleDeferred(reloadCommand);
        }
    }

    interface MainPanelUiBinder extends UiBinder<Widget, MainPanel> {
    }
}
