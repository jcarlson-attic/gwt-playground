package com.google.gwt.playground.client.mvp;

public interface ActivityMapper<P extends Place> {

    Activity getActivity(P place);

}
