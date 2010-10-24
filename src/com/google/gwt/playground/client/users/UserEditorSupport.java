package com.google.gwt.playground.client.users;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public class UserEditorSupport implements ValueChangeHandler<String> {

    private UserEditView view;
    private User user;

    UserEditorSupport() {
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {

    }

    void init(UserEditView view) {
        this.view = view;
        this.view.firstName
                .addValueChangeHandler(new ValueChangeHandler<String>() {

                    @Override
                    public void onValueChange(ValueChangeEvent<String> event) {
                        UserEditorSupport.this.user.getDetails().setFirstName(
                                event.getValue());
                    }
                });
        this.view.lastName
                .addValueChangeHandler(new ValueChangeHandler<String>() {

                    @Override
                    public void onValueChange(ValueChangeEvent<String> event) {
                        UserEditorSupport.this.user.getDetails().setLastName(
                                event.getValue());
                    }
                });
        this.view.email.addValueChangeHandler(new ValueChangeHandler<String>() {

            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                UserEditorSupport.this.user.getDetails().setEmail(
                        event.getValue());
            }
        });
    }

    void setValue(User user) {
        this.view.firstName.setValue(user.getDetails().getFirstName());
        this.view.lastName.setValue(user.getDetails().getLastName());
        this.view.email.setValue(user.getDetails().getEmail());
    }

}
