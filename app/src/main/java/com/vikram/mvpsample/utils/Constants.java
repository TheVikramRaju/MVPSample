package com.vikram.mvpsample.utils;

/**
 * Created by VIKRAM R on 20/02/2018.
 */

public interface Constants {
    /**********************Shared Preference Keys******************/
    interface PreferenceKeys {
        String PREF_KEY_LOGGED_IN = "prefLoggedIn";
        String PREF_KEY_USER_ID = "prefUserId";
        String PREF_KEY_USER_NAME = "prefUserName";
        String PREF_KEY_PASSWORD = "prefPasswd";
    }

    /**********************Database Keys**********************/
    interface TableUser {
        String DATABASE_NAME = "sample_mvp.db";
        int DATABASE_VERSION = 1;

        //Table User
        String USER_TABLE = "users";
        String USER_ID = "userId";
        String USER_NAME = "userName";
        String USER_EMAIL = "userEmail";
        String USER_PASSWORD = "userPassword";
    }
}
