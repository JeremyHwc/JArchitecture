package com.jeremy.jarch;

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
