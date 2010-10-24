package com.google.gwt.playground.client.users;

import com.google.gwt.core.client.JavaScriptObject;

public class User extends JavaScriptObject {

    protected User() {

    }

    public native final UserDetails getDetails() /*-{
        return this.details || (this.details = {});
    }-*/;

    public native final String getURI() /*-{
        return this.uri;
    }-*/;

    public native final String getUsername() /*-{
        return this.username;
    }-*/;

    public native final void setUsername(String username) /*-{
        if (!this.username) {
        this.username = username;
        }
    }-*/;

}
