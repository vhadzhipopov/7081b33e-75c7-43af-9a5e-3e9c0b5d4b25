package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.sample.stockwatcher.client.controller.WebAppController;
import com.google.gwt.sample.stockwatcher.client.model.ModelHandler;
import com.google.gwt.sample.stockwatcher.client.resource.ApplicationResources;
import com.google.gwt.sample.stockwatcher.client.ui.MainPanel;

/**
 * Google gin injector
 * <p>
 * all components to inject
 *
 * @author AGI
 */
@GinModules(GwtWebAppGinModule.class)
public interface GwtWebAppGinjector extends Ginjector {

    SimpleEventBus getEventBus();

    ApplicationResources getApplicationResources();

    WebAppController getWebAppController();

    ModelHandler getModelHandler();

    MainPanel getMainPanel();
}
