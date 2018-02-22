package com.vikram.mvpsample.storage.local.user_data_source_impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.vikram.mvpsample.storage.local.dao.UserDAO;
import com.vikram.mvpsample.storage.local.entity.User;
import com.vikram.mvpsample.storage.local.repository.UserDataSource;
import com.vikram.mvpsample.utils.AppExecutors;

/**
 * Created by VIKRAM R on 20/02/2018.
 */

public class UserLocalDataSource implements UserDataSource {

    private static volatile UserLocalDataSource INSTANCE;

    private UserDAO mUserDao;

    private AppExecutors mAppExecutors;

    // Prevent direct instantiation.
    private UserLocalDataSource(@NonNull AppExecutors appExecutors, @NonNull UserDAO userDao) {
        mAppExecutors = appExecutors;
        mUserDao = userDao;
    }

    public static UserLocalDataSource getInstance(@NonNull AppExecutors appExecutors, @NonNull UserDAO userDao) {
        if (INSTANCE == null) {
            synchronized (UserLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserLocalDataSource(appExecutors, userDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getUser(String emailId, @NonNull GetUserCallback callback) {
        Runnable runnable = () -> {
            final User user = mUserDao.getUser(emailId);

            mAppExecutors.mainThread().execute(() -> {
                if (user != null) {
                    callback.onUserLoaded(user);
                } else {
                    callback.onDataNotAvailable();
                }
            });
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void saveUser(@NonNull User user, @NonNull SaveUserCallback callback) {
        mAppExecutors.diskIO().execute(() -> {
            long userId = mUserDao.insertUser(user);
            mAppExecutors.mainThread().execute(() -> {
                Log.i("UserId", userId + "");
                if (userId > 0) {
                    user.setUserId(userId);
                    callback.onUserSaved(user);
                } else {
                    callback.onUserSaveFailed(user);
                }
            });
        });
    }

    @Override
    public void checkUsername(@NonNull String emailId, @NonNull UsernameExistsListener callback) {
        Runnable runnable = () -> {
            final User user = mUserDao.getUser(emailId);

            mAppExecutors.mainThread().execute(() -> {
                if (user != null) {
                    callback.onUsernameExists(emailId);
                } else {
                    callback.onUsernameNotExist(emailId);
                }
            });
        };

        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void authenticateUser(@NonNull String username, @NonNull String password, @NonNull AuthenticateUserCallback callback) {
        Runnable runnable = () -> {
            final User user = mUserDao.authenticateUser(username, password);
            mAppExecutors.mainThread().execute(() -> {
                if (user != null) {
                    callback.onSuccess(user);
                } else {
                    callback.onFail(user);
                }
            });
        };

        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void deleteUser(@NonNull String emailId, @NonNull DeleteUserCallback callback) {
        Runnable runnable = () -> {
            final int count = mUserDao.deleteUser(emailId);
            mAppExecutors.mainThread().execute(() -> {
                if (count > 0) {
                    callback.onDeleteSuccess(emailId);
                } else {
                    callback.onDeleteSuccess(emailId);
                }
            });
        };
        mAppExecutors.diskIO().execute(runnable);
    }
}
