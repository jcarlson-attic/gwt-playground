package com.google.gwt.playground.client.users;

import com.google.gwt.core.client.JavaScriptObject;

public class UserDetails extends JavaScriptObject {

    protected UserDetails() {

    }

    public native final String getEmail() /*-{
        return this.email;
    }-*/;

    public native final String getFirstName() /*-{
        return this.firstName;
    }-*/;

    public native final String getLastName() /*-{
        return this.lastName;
    }-*/;

    public native final void setEmail(String email) /*-{
        this.email = email;
    }-*/;

    public native final void setFirstName(String firstName) /*-{
        this.firstName = firstName;
    }-*/;

    public native final void setLastName(String lastName) /*-{
        this.lastName = lastName;
    }-*/;

}
