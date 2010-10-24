package com.google.gwt.playground.client.users;

import com.google.gwt.playground.client.mvp.Place;
import com.google.gwt.playground.client.mvp.PlaceManager;
import com.google.gwt.playground.client.mvp.TokenMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UserTokenMapper extends TokenMapper {

    @Inject
    public UserTokenMapper(PlaceManager placeManager) {
        super(placeManager);
    }

    @Override
    protected Place getPlaceForToken(String token) {

        if (token.matches("^users\\/.*$")) {
            return new UserPlace(token, UserPlace.Operation.VIEW);
        }

        return null;
    }

}
