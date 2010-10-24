package com.google.gwt.playground.client;

import com.google.gwt.playground.client.mvp.Place;
import com.google.gwt.playground.client.users.UserPlace;

public abstract class ApplicationPlace extends Place {

    public interface Filter<T> {

        T filter(Place place);

        T filter(UserPlace place);

    }

    protected ApplicationPlace(String token) {
        super(token);
    }

    public abstract <T> T filter(Filter<T> filter);

}
