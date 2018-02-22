package com.vikram.mvpsample.ui_presentation.sign_in;

import android.support.annotation.NonNull;

import com.vikram.mvpsample.storage.local.entity.User;
import com.vikram.mvpsample.ui_presentation.BasePresenter;
import com.vikram.mvpsample.ui_presentation.BaseView;

/**
 * Created by VIKRAM R on 20/02/2018.
 */

public interface SignInUserContract {
    interface View extends BaseView<Presenter> {
        void showLoadingIndicator(boolean show);

        void onAuthendicateSuccess(@NonNull User user);

        void onAuthendicateFailure(@NonNull User user);
    }

    interface Presenter extends BasePresenter {
        void authendicateUser(@NonNull String userName, String password);
    }
}
