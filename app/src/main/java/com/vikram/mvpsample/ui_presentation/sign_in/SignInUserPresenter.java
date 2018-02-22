package com.vikram.mvpsample.ui_presentation.sign_in;

import android.content.Context;
import android.support.annotation.NonNull;

import com.vikram.mvpsample.storage.local.MVPDataBase;
import com.vikram.mvpsample.storage.local.entity.User;
import com.vikram.mvpsample.storage.local.repository.UserDataSource;
import com.vikram.mvpsample.storage.local.user_data_source_impl.UserLocalDataSource;
import com.vikram.mvpsample.utils.AppExecutors;

/**
 * Created by VIKRAM R on 20/02/2018.
 */

public class SignInUserPresenter implements SignInUserContract.Presenter {
    private final String TAG = SignInUserPresenter.class.getSimpleName();
    private final SignInUserContract.View mView;
    private final Context mContext;
    private AppExecutors mAppExecutors;

    public SignInUserPresenter(SignInUserContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        mAppExecutors = new AppExecutors();
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void authendicateUser(@NonNull String userName, String password) {
        mView.showLoadingIndicator(true);
        UserLocalDataSource.getInstance(mAppExecutors, MVPDataBase.getInstance(mContext).userDao()).authenticateUser(userName, password, new UserDataSource.AuthenticateUserCallback() {
            @Override
            public void onSuccess(User user) {
                mView.showLoadingIndicator(false);
                mView.onAuthendicateSuccess(user);
            }

            @Override
            public void onFail(User user) {
                mView.showLoadingIndicator(false);
                mView.onAuthendicateFailure(user);
            }
        });
    }
}
