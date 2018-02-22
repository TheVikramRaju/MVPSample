package com.vikram.mvpsample.ui_presentation.register;

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

public class RegisterUserPresenter implements RegisterUserContract.Presenter {
    private final String TAG = RegisterUserPresenter.class.getSimpleName();
    private final RegisterUserContract.View mView;
    private final Context mContext;
    private AppExecutors mAppExecutors;

    public RegisterUserPresenter(@NonNull RegisterUserContract.View view, @NonNull Context context) {
        this.mView = view;
        this.mContext = context;
        mAppExecutors = new AppExecutors();
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void registerUser(@NonNull User user) {
        mView.showLoadingIndicator(true);
        UserLocalDataSource.getInstance(mAppExecutors, MVPDataBase.getInstance(mContext).userDao()).saveUser(user, new UserDataSource.SaveUserCallback() {
            @Override
            public void onUserSaved(User user) {
                mView.showLoadingIndicator(false);
                mView.onUserRegistrationSuccess(user);
            }

            @Override
            public void onUserSaveFailed(User user) {
                mView.showLoadingIndicator(false);
                mView.onUserRegistrationFail(user);
            }
        });

    }

    @Override
    public void checkUser(@NonNull String emailId) {
        UserLocalDataSource.getInstance(mAppExecutors, MVPDataBase.getInstance(mContext).userDao()).checkUsername(emailId, new UserDataSource.UsernameExistsListener() {
            @Override
            public void onUsernameExists(String string) {
                mView.usernameExists(string);
            }

            @Override
            public void onUsernameNotExist(String string) {
                mView.usernameNotExist(string);
            }
        });
    }
}

