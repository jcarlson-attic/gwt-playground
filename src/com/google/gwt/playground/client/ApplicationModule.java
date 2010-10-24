package com.google.gwt.playground.client;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.playground.client.mvp.ActivityMapper;
import com.google.gwt.playground.client.users.UserTokenMapper;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;

public class ApplicationModule extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(UserTokenMapper.class).asEagerSingleton();

        bind(new TypeLiteral<ActivityMapper<ApplicationPlace>>() {
        }).to(ApplicationActivities.class);
    }

    @Provides
    @Singleton
    HandlerManager getEventBus() {
        return new HandlerManager(null);
    }

}
