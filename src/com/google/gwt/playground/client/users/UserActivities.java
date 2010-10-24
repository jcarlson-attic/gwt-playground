package com.google.gwt.playground.client.users;

import com.google.gwt.playground.client.mvp.Activity;
import com.google.gwt.playground.client.mvp.ActivityMapper;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class UserActivities implements ActivityMapper<UserPlace> {

    private Provider<UserDetailsActivity> userDetails;

    @Inject
    UserActivities(Provider<UserDetailsActivity> users) {
        this.userDetails = users;
    }

    @Override
    public Activity getActivity(UserPlace place) {
        switch (place.getOperation()) {
        case VIEW:
            UserDetailsActivity activity = this.userDetails.get();
            activity.setUsername(place.toToken().split("/")[1]);
            return activity;
        }
        return null;
    }

}
