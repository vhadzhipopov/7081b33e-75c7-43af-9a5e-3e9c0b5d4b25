package com.google.gwt.sample.stockwatcher.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.sample.stockwatcher.client.event.AddCurrencyEvent;
import com.google.gwt.sample.stockwatcher.client.event.DeleteAllCurrencyEvent;
import com.google.gwt.sample.stockwatcher.client.event.LoadEvent;
import com.google.gwt.sample.stockwatcher.client.event.ReloadedEvent;
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
    @UiField(provided = true)
    SuggestBox suggestBox;
    @UiField
    Label errorLabel;
    @UiField
    FlowPanel currencyPanel;

    private Map<String, CurrencyWidget> currencyWidgets;
    private SimpleEventBus eventBus;
    private ModelHandler modelHandler;
    private MultiWordSuggestOracle oracle;

    @Inject
    public MainPanel(SimpleEventBus eventBus, ModelHandler modelHandler) {
        this.eventBus = eventBus;
        this.modelHandler = modelHandler;
        this.currencyWidgets = new HashMap<>();
        this.oracle = new MultiWordSuggestOracle();
        this.suggestBox = new SuggestBox(oracle);
        this.eventBus.addHandler(ReloadedEvent.TYPE, event -> {
            oracle.clear();
            event.getCurrencyList().forEach(currency -> oracle.add(currency.getSymbol()));
        });
        this.suggestBox.addSelectionHandler(event -> onAddButtonClick(null));
        // init display
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("addButton")
    void onAddButtonClick(ClickEvent event) {

        // retrieve textbox text
        String currencyText = suggestBox.getText();

        if (!FieldVerifier.isValidSymbol(currencyText)) {
            errorLabel.setText("Name must be 3 capital letters");
            return;
        }

        if (currencyWidgets.get(currencyText) != null) {
            errorLabel.setText("Already existing Currency : " + currencyText);
            return;
        }

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
        if (!currency.getVisible())
            return;

        // create a Currency
        CurrencyWidget widget = new CurrencyWidget(currency, eventBus);
        // add it to panel
        currencyPanel.add(widget);
        // keep a reference of the widget for later usage (see
        // removeCurrencyFromPanel)
        currencyWidgets.put(currency.getSymbol(), widget);
    }

    public void removeCurrencyFromPanel(Currency currency) {
        // retrieve currency from the references
        CurrencyWidget currencyWidget = currencyWidgets.get(currency.getSymbol());
        // remove it from panel
        currencyPanel.remove(currencyWidget);

        currencyWidgets.remove(currency.getSymbol());
    }

    public void reloadCurrencyList() {
        // clear currency panel
        currencyPanel.clear();
        // clear references
        currencyWidgets.clear();
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
