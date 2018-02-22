package com.vikram.mvpsample.ui_presentation.register;

import android.support.annotation.NonNull;

import com.vikram.mvpsample.storage.local.entity.User;
import com.vikram.mvpsample.ui_presentation.BasePresenter;
import com.vikram.mvpsample.ui_presentation.BaseView;

/**
 * Created by VIKRAM R on 20/02/2018.
 */

public interface RegisterUserContract {
    interface View extends BaseView<Presenter> {
        void showLoadingIndicator(boolean show);

        void onUserRegistrationSuccess(@NonNull User user);

        void onUserRegistrationFail(@NonNull User user);

        void usernameExists(@NonNull String emailId);

        void usernameNotExist(@NonNull String emailId);
    }

    interface Presenter extends BasePresenter {
        void registerUser(@NonNull User user);

        void checkUser(@NonNull String emailId);
    }
}
