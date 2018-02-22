package com.vikram.mvpsample.storage.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.ColorInt;

import static com.vikram.mvpsample.utils.Constants.TableUser.USER_EMAIL;
import static com.vikram.mvpsample.utils.Constants.TableUser.USER_ID;
import static com.vikram.mvpsample.utils.Constants.TableUser.USER_NAME;
import static com.vikram.mvpsample.utils.Constants.TableUser.USER_PASSWORD;
import static com.vikram.mvpsample.utils.Constants.TableUser.USER_TABLE;

/**
 * Created by VIKRAM R on 20/02/2018.
 */

@Entity(tableName = USER_TABLE)
public final class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = USER_ID)
    public long userId;

    @ColumnInfo(name = USER_NAME)
    public String userName;

    @ColumnInfo(name = USER_EMAIL)
    public String userEmail;

    @ColumnInfo(name = USER_PASSWORD)
    public String userPassword;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
