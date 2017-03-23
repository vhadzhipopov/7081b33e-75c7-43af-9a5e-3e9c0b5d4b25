package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.sample.stockwatcher.client.controller.WebAppController;
import com.google.gwt.sample.stockwatcher.client.event.LoadEvent;
import com.google.gwt.sample.stockwatcher.client.resource.ApplicationResources;
import com.google.gwt.sample.stockwatcher.client.ui.MainPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * <p>
 * Point d'entrée du module GWT
 */
public class GwtWebApp implements EntryPoint {

    /**
     * gin injector
     */
    private final GwtWebAppGinjector injector = GWT.create(GwtWebAppGinjector.class);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        // ensure resources are injected
        ApplicationResources.INSTANCE.style().ensureInjected();
        // get controler from gin jector
        WebAppController controller = injector.getWebAppController();
        // bind event handlers
        controller.bindHandlers();
        // get main panel
        MainPanel mainPanel = injector.getMainPanel();
        // add for display
        RootLayoutPanel.get().add(mainPanel);
        // load init data
        injector.getEventBus().fireEvent(new LoadEvent());
    }
}
