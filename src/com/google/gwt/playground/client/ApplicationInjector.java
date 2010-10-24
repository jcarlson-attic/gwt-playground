package com.google.gwt.playground.client;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.playground.client.mvp.ActivityMapper;

@GinModules( { ApplicationModule.class })
public interface ApplicationInjector extends Ginjector {

    ActivityMapper<ApplicationPlace> getApplicationActivities();

    HandlerManager getEventBus();

}
