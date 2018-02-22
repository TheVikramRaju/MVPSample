package com.vikram.mvpsample.ui_presentation.home;

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

public class HomePresenter implements HomeContract.Presenter {
    private final String TAG = HomePresenter.class.getSimpleName();
    private final HomeContract.View mView;
    private final Context mContext;
    private AppExecutors mAppExecutors;

    public HomePresenter(@NonNull HomeContract.View view, @NonNull Context context) {
        this.mView = view;
        this.mContext = context;
        mAppExecutors = new AppExecutors();
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getUser(@NonNull String emailId) {
        UserLocalDataSource.getInstance(mAppExecutors, MVPDataBase.getInstance(mContext).userDao()).getUser(emailId, new UserLocalDataSource.GetUserCallback() {

            @Override
            public void onUserLoaded(User user) {
                mView.onGetUserDetailsSuccess(user);
            }

            @Override
            public void onDataNotAvailable() {
                mView.onGetUserDetailsFailed();
            }
        });
    }

    @Override
    public void deleteUser(@NonNull String emailId) {
        UserLocalDataSource.getInstance(mAppExecutors, MVPDataBase.getInstance(mContext).userDao()).deleteUser(emailId, new UserDataSource.DeleteUserCallback() {
            @Override
            public void onDeleteSuccess(String emailId) {
                mView.onUserDeletedSuccess(emailId);
            }

            @Override
            public void onDeleteFailed(String emailId) {
                mView.onGetUserDetailsFailed();
            }
        });
    }
}
