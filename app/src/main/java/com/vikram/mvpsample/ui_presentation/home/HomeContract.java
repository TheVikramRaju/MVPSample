package com.vikram.mvpsample.ui_presentation.home;

import android.support.annotation.NonNull;

import com.vikram.mvpsample.storage.local.entity.User;
import com.vikram.mvpsample.ui_presentation.BasePresenter;
import com.vikram.mvpsample.ui_presentation.BaseView;


/**
 * Created by VIKRAM R on 20/02/2018.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void showLoadingIndicator(boolean show);

        void onGetUserDetailsSuccess(@NonNull User user);

        void onGetUserDetailsFailed();

        void onUserDeletedSuccess(@NonNull String emailId);

        void onUserDeletedFailed();
    }

    interface Presenter extends BasePresenter {
        void getUser(@NonNull String emailId);

        void deleteUser(@NonNull String emailId);
    }
}
