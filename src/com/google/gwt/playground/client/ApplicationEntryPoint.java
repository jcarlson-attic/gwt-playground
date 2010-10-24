package com.google.gwt.playground.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.playground.client.mvp.ActivityManager;
import com.google.gwt.playground.client.mvp.ActivityMapper;
import com.google.gwt.playground.client.mvp.Display;
import com.google.gwt.playground.client.mvp.IsWidget;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ApplicationEntryPoint implements EntryPoint {

    public void onModuleLoad() {

        ApplicationInjector injector = GWT.create(ApplicationInjector.class);
        HandlerManager bus = injector.getEventBus();

        Display rootDisplay = new Display() {

            @Override
            public void showActivityWidget(IsWidget widget) {
                RootPanel.get().clear();
                RootPanel.get().add(widget.asWidget());
            }
        };

        ActivityMapper<ApplicationPlace> activities = injector
                .getApplicationActivities();

        new ActivityManager<ApplicationPlace>(bus, rootDisplay, activities);

        History.fireCurrentHistoryState();

    }

}
