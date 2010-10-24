package com.google.gwt.playground.client.mvp;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.inject.ImplementedBy;

@ImplementedBy(PlaceManagerImpl.class)
public interface PlaceManager {

    interface Delegate {

        HandlerRegistration addWindowClosingHandler(ClosingHandler handler);

        boolean confirm(String message);
    }

    Place getCurrentPlace();

    /**
     * Used to request a new {Place}. If approved, a {PlaceChangedEvent} will
     * follow, which will actually cause the current {Place} to change.
     * 
     * @param place
     */
    <P extends Place> void request(P place);

    /**
     * Used when the current {Place} has already been updated; the caller just
     * needs a way to inform the Application that such a change has occurred.
     * 
     * @param place
     */
    // TODO: This method maybe shouldn't be exposed
    <P extends Place> void update(P place);

}