package com.google.gwt.sample.stockwatcher.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.i18n.shared.DateTimeFormat;
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
        NumberFormat decimalFormat = NumberFormat.getFormat("0.##");
        NumberFormat percentFormat = NumberFormat.getFormat("0.##%");
        DateTimeFormat dateFormat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT);
        String price = decimalFormat.format(currency.getPrice());
        String percentChange = percentFormat.format((currency.getPrice() - currency.getPriceLastMonth()) / currency.getPriceLastMonth());
        String date = dateFormat.format(currency.getLastUpdated());
        textBox.setText(
                currency.getSymbol() + ": Current  " + price
                        + "HKD. Increase since last month " + percentChange
                        + " Last updated " + date);

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
