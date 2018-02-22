package com.vikram.mvpsample.storage.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;

import com.vikram.mvpsample.storage.local.entity.User;
import com.vikram.mvpsample.utils.Constants;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
import static com.vikram.mvpsample.utils.Constants.TableUser.USER_EMAIL;
import static com.vikram.mvpsample.utils.Constants.TableUser.USER_NAME;
import static com.vikram.mvpsample.utils.Constants.TableUser.USER_TABLE;

/**
 * Created by VIKRAM R on 20/02/2018.
 */
@Dao
public interface UserDAO {
    @Insert(onConflict = REPLACE)
    long insertUser(User user);

    @Query("SELECT * FROM " + USER_TABLE + " WHERE " + USER_EMAIL + " = :emailId")
    User getUser(@NonNull String emailId);

    @Query("Select * FROM " + USER_TABLE + " WHERE " + USER_NAME + " = :username AND " + Constants.TableUser.USER_PASSWORD + " = :password")
    User authenticateUser(@NonNull String username, String password);

    @Query("DELETE FROM " + USER_TABLE + " WHERE " + USER_EMAIL + " =:emailId")
    int deleteUser(@NonNull String emailId);
}
