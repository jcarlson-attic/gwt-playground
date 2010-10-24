package com.google.gwt.playground.client.mvp;

public interface Activity {

    String mayStop();

    // TODO: Is a "cancel" method really necessary? Can't we just call "stop"?
    void onCancel();

    void onStop();

    void start(Display display);

}
