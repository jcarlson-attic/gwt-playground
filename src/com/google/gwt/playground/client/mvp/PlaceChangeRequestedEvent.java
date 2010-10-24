package com.google.gwt.playground.client.mvp;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class PlaceChangeRequestedEvent<P extends Place> extends
        GwtEvent<PlaceChangeRequestedEvent.Handler<P>> {

    public interface Handler<P extends Place> extends EventHandler {

        void onPlaceChangeRequested(PlaceChangeRequestedEvent<P> event);

    }

    public static Type<Handler<?>> TYPE;
    static {
        TYPE = new Type<Handler<?>>();
    }

    private String warning;

    private P place;

    PlaceChangeRequestedEvent(P place) {
        this.place = place;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Type<Handler<P>> getAssociatedType() {
        return (Type) TYPE;
    }

    public P getRequestedPlace() {
        return this.place;
    }

    public String getWarning() {
        return this.warning;
    }

    public void setWarning(String warning) {
        if (this.warning == null) {
            this.warning = warning;
        }
    }

    @Override
    protected void dispatch(Handler<P> handler) {
        handler.onPlaceChangeRequested(this);
    }

}
