package com.ricky.application.base;

public abstract class BaseAbstractPresenter<V> implements IBasePresenter {

    protected V view;

    public void setView(V view) {
        this.view = view;
    }
}
