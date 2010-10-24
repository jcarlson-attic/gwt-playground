package com.google.gwt.playground.client;

import com.google.gwt.playground.client.ApplicationPlace.Filter;
import com.google.gwt.playground.client.mvp.Activity;
import com.google.gwt.playground.client.mvp.ActivityMapper;
import com.google.gwt.playground.client.mvp.Place;
import com.google.gwt.playground.client.users.UserActivities;
import com.google.gwt.playground.client.users.UserPlace;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ApplicationActivities implements ActivityMapper<ApplicationPlace> {

    private UserActivities users;

    @Inject
    ApplicationActivities(UserActivities users) {
        this.users = users;
    }

    @Override
    public Activity getActivity(ApplicationPlace place) {
        return place.filter(new Filter<Activity>() {

            @Override
            public Activity filter(Place place) {
                return null;
            }

            @Override
            public Activity filter(UserPlace place) {
                return ApplicationActivities.this.users.getActivity(place);
            }
        });
    }

}
