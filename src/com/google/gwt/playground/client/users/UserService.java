package com.google.gwt.playground.client.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;
import com.google.inject.Singleton;

@Singleton
public class UserService {

    interface Resources extends ClientBundle {
        @Source("users.txt")
        TextResource json();
    }

    private static Map<String, User> users;

    static {
        Resources resources = GWT.create(Resources.class);
        JsArray<User> records = JsonUtils
                .unsafeEval(resources.json().getText());
        users = new HashMap<String, User>();

        for (int i = 0; i < records.length(); i++) {
            User user = records.get(i);
            users.put(user.getUsername(), user);
        }
    }

    public List<User> findAllUsers() {
        ArrayList<User> list = new ArrayList<User>();
        list.addAll(users.values());
        return list;
    }

    public User findUserByUsername(String username) {
        return users.get(username);
    }

}
