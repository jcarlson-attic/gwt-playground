package com.google.gwt.playground.client.users;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.playground.client.mvp.IsWidget;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class UserDetailsView extends Composite implements IsWidget {

    static interface Binder extends UiBinder<Widget, UserDetailsView> {
    };

    static interface Delegate {

        void deleteClicked();

        void editClicked();
    }

    private static Binder binder = GWT.create(Binder.class);

    @UiField
    Element uri;

    @UiField
    Element username;

    @UiField
    Element firstName;

    @UiField
    Element lastName;

    @UiField
    Element email;

    @UiField
    Button edit;

    @UiField
    Button delete;

    private Delegate delegate;

    UserDetailsView() {
        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    public boolean confirm(String msg) {
        return Window.confirm(msg);
    }

    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }

    public void setValue(User value) {
        this.uri.setInnerText(value.getURI());
        this.username.setInnerText(value.getUsername());
        this.firstName.setInnerText(value.getDetails().getFirstName());
        this.lastName.setInnerText(value.getDetails().getLastName());
        this.email.setInnerText(value.getDetails().getEmail());
    }

    @UiHandler("delete")
    void onDeleteClicked(ClickEvent event) {
        this.delegate.deleteClicked();
    }

    @UiHandler("edit")
    void onEditClicked(ClickEvent event) {
        this.delegate.editClicked();
    }

}
