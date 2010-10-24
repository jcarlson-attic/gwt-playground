package com.google.gwt.playground.client.mvp;

public abstract class Place {

    private String token;

    protected Place(String token) {
        this.token = token;
    }

    /*
     * At some point, I will add helper methods for decoding and retrieving
     * parameters from the token. At that point, it is important to consider how
     * different parameter values affect equals(). The PlaceManager will only
     * initiate a PlaceChangeEvent if the new Place is not equal to the existing
     * Place. Generally, if the only difference between the Places are parameter
     * values, then the Place _is_ the same and the Activity is merely
     * requesting that the History token be updated for bookkeeping purposes.
     */
    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    public String toToken() {
        return this.token;
    }

}
