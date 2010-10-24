package com.google.gwt.playground.client.users;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.playground.client.mvp.IsWidget;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public class UserEditView extends Composite implements IsWidget {

    static interface Binder extends UiBinder<Widget, UserEditView> {
    };

    static interface Delegate {

        void cancelClicked();

        void saveClicked();

    }

    private static Binder binder = GWT.create(Binder.class);

    @UiField
    Element uri;

    @UiField
    Element username;

    @UiField
    HasValue<String> firstName;

    @UiField
    HasValue<String> lastName;

    @UiField
    HasValue<String> email;

    @UiField
    Button save;

    @UiField
    Button cancel;

    private Delegate delegate;
    private UserEditorSupport support;

    UserEditView() {
        initWidget(binder.createAndBindUi(this));
        this.support = GWT.create(UserEditorSupport.class);
        this.support.init(this);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }

    public void setValue(User user) {

    }

    @UiHandler("cancel")
    void onCancelClicked(ClickEvent event) {
        this.delegate.cancelClicked();
    }

    @UiHandler("save")
    void onSaveClicked(ClickEvent event) {
        this.delegate.saveClicked();
    }

}
