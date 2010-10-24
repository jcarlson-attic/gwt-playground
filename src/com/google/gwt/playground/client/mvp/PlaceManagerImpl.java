package com.google.gwt.playground.client.mvp;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class PlaceManagerImpl implements PlaceManager {

    public static class DefaultDelegate implements Delegate {

        @Override
        public HandlerRegistration addWindowClosingHandler(
                ClosingHandler handler) {
            return Window.addWindowClosingHandler(handler);
        }

        @Override
        public boolean confirm(String message) {
            return Window.confirm(message);
        }
    }

    private HandlerManager bus;
    private Delegate delegate;
    private Place place;

    @Inject
    PlaceManagerImpl(HandlerManager bus) {
        this(bus, new DefaultDelegate());
    }

    PlaceManagerImpl(HandlerManager bus, Delegate delegate) {
        this.bus = bus;
        this.delegate = delegate;
        delegate.addWindowClosingHandler(new ClosingHandler() {

            @Override
            public void onWindowClosing(ClosingEvent event) {
                String warning = maybeGoTo(null);
                if (warning != null) {
                    event.setMessage(warning);
                }
            }
        });
    }

    @Override
    public Place getCurrentPlace() {
        return this.place;
    }

    @Override
    public <P extends Place> void request(P place) {
        String warning = maybeGoTo(place);
        if (warning == null || this.delegate.confirm(warning)) {
            update(place);
        }
    }

    @Override
    public <P extends Place> void update(P place) {
        if (place != null && !place.equals(getCurrentPlace())) {
            this.bus.fireEvent(new PlaceChangeEvent<P>(place));
        }

        this.place = place;
        History.newItem(this.place.toToken(), false);
    }

    protected <P extends Place> String maybeGoTo(P place) {
        PlaceChangeRequestedEvent<P> event = new PlaceChangeRequestedEvent<P>(
                place);
        this.bus.fireEvent(event);
        return event.getWarning();
    }
}
