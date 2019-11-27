package com.ricky.application.base;

public abstract class BaseAbstractPresenter<V> {

    protected V view;

    public void setView(V view) {
        this.view = view;
    }
}
