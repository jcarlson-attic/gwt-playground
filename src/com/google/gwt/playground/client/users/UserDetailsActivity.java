package com.google.gwt.playground.client.users;

import com.google.gwt.playground.client.mvp.Activity;
import com.google.gwt.playground.client.mvp.Display;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;

public class UserDetailsActivity implements Activity, UserDetailsView.Delegate {

    private UserDetailsView view;
    private UserService service;

    @Inject
    UserDetailsActivity(UserDetailsView view, UserService service) {
        this.view = view;
        this.service = service;
        view.setDelegate(this);
    }

    @Override
    public void deleteClicked() {
        if (this.view.confirm("Do you really want to delete this record?")) {
            Window.alert("I haven't implemented 'DeleteUser' functions yet");
        }
    }

    @Override
    public void editClicked() {
        Window.alert("I haven't implemented 'EditUser' functions yet");
    }

    @Override
    public String mayStop() {
        return null;
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onStop() {

    }

    public void setUsername(String username) {
        this.view.setValue(this.service.findUserByUsername(username));
    }

    @Override
    public void start(Display display) {
        display.showActivityWidget(this.view);
    }

}
