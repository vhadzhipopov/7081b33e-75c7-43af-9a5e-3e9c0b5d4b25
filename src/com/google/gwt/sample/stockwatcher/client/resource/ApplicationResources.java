package com.google.gwt.sample.stockwatcher.client.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

/**
 * Resource bundle
 * Gather images & css
 *
 * @author AGI
 */
public interface ApplicationResources extends ClientBundle {
    ApplicationResources INSTANCE = GWT.create(ApplicationResources.class);

    @Source("com/google/gwt/sample/stockwatcher/client/resource/GwtWebAppStyles.css")
    GwtWebAppStyles style();

    @Source("delete.png")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource deleteIcon();

    @Source("add.png")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource addIcon();

    @Source("clear.png")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource clearIcon();

    @Source("store.png")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource storeIcon();

    @Source("load.png")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource loadIcon();

    @Source("warning.png")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource warningIcon();
}
