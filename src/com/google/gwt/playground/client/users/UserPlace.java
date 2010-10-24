package com.google.gwt.playground.client.users;

import com.google.gwt.playground.client.ApplicationPlace;

public class UserPlace extends ApplicationPlace {

    public enum Operation {
        LIST, VIEW;
    }

    private Operation operation;

    public UserPlace(String token, Operation operation) {
        super(token);
        this.operation = operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        UserPlace other = (UserPlace) o;
        return toToken().equals(other.toToken());
    }

    @Override
    public <T> T filter(Filter<T> filter) {
        return filter.filter(this);
    }

    public Operation getOperation() {
        return this.operation;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + toToken().hashCode();
        return result;
    }

}
