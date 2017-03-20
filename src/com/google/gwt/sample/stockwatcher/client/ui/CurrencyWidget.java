package com.google.gwt.sample.stockwatcher.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.sample.stockwatcher.client.event.DeleteCurrencyEvent;
import com.google.gwt.sample.stockwatcher.client.ui.component.ImageButton;
import com.google.gwt.sample.stockwatcher.shared.Currency;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

public class CurrencyWidget extends Composite {

    private static CurrencyWidgetUiBinder uiBinder = GWT
            .create(CurrencyWidgetUiBinder.class);
    /*
     * UI components
     */
    @UiField
    ImageButton deleteButton;
    @UiField
    InlineLabel textBox;
    /**
     * current currency
     */
    private Currency currentCurrency;
    /**
     * event bus
     */
    private SimpleEventBus eventBus;

    public CurrencyWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public CurrencyWidget(Currency currency, SimpleEventBus eventBus) {
        this.eventBus = eventBus;
        // init display
        initWidget(uiBinder.createAndBindUi(this));
        this.currentCurrency = currency;
        // format display
        textBox.setText(currency.getSymbol());
    }

    @UiHandler("deleteButton")
    void onAddButtonClick(ClickEvent event) {
        // show confirm popup
        boolean confirm = Window.confirm("Delete " + currentCurrency.getSymbol() + " ?");
        if (confirm) {
            // ask controller for delete
            eventBus.fireEvent(new DeleteCurrencyEvent(currentCurrency));
        }
    }

    interface CurrencyWidgetUiBinder extends UiBinder<Widget, CurrencyWidget> {
    }

}
