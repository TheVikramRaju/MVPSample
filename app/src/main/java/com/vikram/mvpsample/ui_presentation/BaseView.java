package com.vikram.mvpsample.ui_presentation;

/**
 * Created by VIKRAM R on 20/02/2018.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
