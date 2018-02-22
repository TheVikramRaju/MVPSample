package com.vikram.mvpsample.storage.local.repository;

import android.support.annotation.NonNull;

import com.vikram.mvpsample.storage.local.entity.User;

/**
 * Created by VIKRAM R on 20/02/2018.
 */

public interface UserDataSource {
    interface GetUserCallback {

        void onUserLoaded(User user);

        void onDataNotAvailable();
    }

    interface SaveUserCallback {

        void onUserSaved(User user);

        void onUserSaveFailed(User user);
    }

    interface UsernameExistsListener {

        void onUsernameExists(String string);

        void onUsernameNotExist(String string);
    }

    interface AuthenticateUserCallback {

        void onSuccess(User user);

        void onFail(User user);
    }

    interface DeleteUserCallback {
        void onDeleteSuccess(String emailId);

        void onDeleteFailed(String emailId);
    }

    void getUser(String emailId, @NonNull GetUserCallback callback);

    void saveUser(@NonNull User user, @NonNull SaveUserCallback callback);

    void checkUsername(@NonNull String emailId, @NonNull UsernameExistsListener callback);

    void authenticateUser(@NonNull String username, @NonNull String password, @NonNull AuthenticateUserCallback callback);

    void deleteUser(@NonNull String emailId, @NonNull DeleteUserCallback callback);
}
