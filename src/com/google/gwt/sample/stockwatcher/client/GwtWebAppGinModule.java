package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.sample.stockwatcher.client.controller.WebAppController;
import com.google.gwt.sample.stockwatcher.client.model.ModelHandler;
import com.google.gwt.sample.stockwatcher.client.resource.ApplicationResources;
import com.google.gwt.sample.stockwatcher.client.ui.MainPanel;
import com.google.inject.Singleton;

/**
 * Google gin module configuration
 *
 * @author AGI
 */
public class GwtWebAppGinModule extends AbstractGinModule {

    @Override
    protected void configure() {
        // Resources
        bind(ApplicationResources.class).in(Singleton.class);

        // Core
        bind(SimpleEventBus.class).in(Singleton.class);
        bind(WebAppController.class).in(Singleton.class);
        bind(ModelHandler.class).in(Singleton.class);

        // UI
        bind(MainPanel.class).in(Singleton.class);
    }

}
