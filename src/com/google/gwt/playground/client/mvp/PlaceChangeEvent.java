package com.google.gwt.playground.client.mvp;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class PlaceChangeEvent<P extends Place> extends
        GwtEvent<PlaceChangeEvent.Handler<P>> {

    public interface Handler<P extends Place> extends EventHandler {

        void onPlaceChange(PlaceChangeEvent<P> event);

    }

    public static Type<Handler<?>> TYPE;

    static {
        TYPE = new Type<Handler<?>>();
    }

    private P place;

    PlaceChangeEvent(P place) {
        this.place = place;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Type<Handler<P>> getAssociatedType() {
        return (Type) TYPE;
    }

    public P getPlace() {
        return this.place;
    }

    @Override
    protected void dispatch(Handler<P> handler) {
        handler.onPlaceChange(this);
    }

}
