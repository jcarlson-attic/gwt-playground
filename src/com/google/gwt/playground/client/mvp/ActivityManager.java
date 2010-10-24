package com.google.gwt.playground.client.mvp;

import com.google.gwt.event.shared.HandlerManager;

public class ActivityManager<P extends Place> implements
        PlaceChangeEvent.Handler<P>, PlaceChangeRequestedEvent.Handler<P> {

    private class ProtectedDisplay implements Display {
        private final Activity activity;

        ProtectedDisplay(Activity activity) {
            this.activity = activity;
        }

        public void showActivityWidget(IsWidget view) {
            if (this.activity == ActivityManager.this.currentActivity) {
                ActivityManager.this.startupInProgress = false;
                ActivityManager.this.display.showActivityWidget(view);
            }
        }
    }

    private ActivityMapper<P> mapper;
    private Activity currentActivity;
    private Display display;
    private HandlerManager bus;

    private boolean startupInProgress = false;

    public ActivityManager(HandlerManager bus, Display display,
            ActivityMapper<P> mapper) {
        this.mapper = mapper;
        this.bus = bus;
        setDisplay(display);
    }

    @Override
    public void onPlaceChange(PlaceChangeEvent<P> event) {
        Activity nextActivity = this.mapper.getActivity(event.getPlace());

        if (this.currentActivity == nextActivity) {
            return;
        }

        if (this.startupInProgress) {
            this.currentActivity.onCancel();
            this.currentActivity = null;
            this.startupInProgress = false;
        } else if (this.currentActivity != null) {
            this.currentActivity.onStop();
        }

        if (nextActivity == null) {
            this.display.showActivityWidget(null);
            this.currentActivity = null;
            return;
        }

        this.currentActivity = nextActivity;
        this.startupInProgress = true;

        this.currentActivity.start(new ProtectedDisplay(this.currentActivity));
    }

    @Override
    public void onPlaceChangeRequested(PlaceChangeRequestedEvent<P> event) {
        if (this.currentActivity != null) {
            event.setWarning(this.currentActivity.mayStop());
        }
    }

    public void setDisplay(Display display) {
        boolean wasActive = (null != this.display);
        boolean willBeActive = (null != display);
        this.display = display;
        if (wasActive != willBeActive) {
            updateHandlers(willBeActive);
        }
    }

    private void updateHandlers(boolean activate) {
        if (activate) {
            this.bus.addHandler(PlaceChangeEvent.TYPE, this);
            this.bus.addHandler(PlaceChangeRequestedEvent.TYPE, this);
        } else {
            this.bus.removeHandler(PlaceChangeEvent.TYPE, this);
            this.bus.removeHandler(PlaceChangeRequestedEvent.TYPE, this);
        }
    }
}
