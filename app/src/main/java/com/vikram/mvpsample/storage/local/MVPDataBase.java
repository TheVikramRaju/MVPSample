package com.vikram.mvpsample.storage.local;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.vikram.mvpsample.storage.local.dao.UserDAO;
import com.vikram.mvpsample.storage.local.entity.User;

import static com.vikram.mvpsample.utils.Constants.TableUser.DATABASE_NAME;
import static com.vikram.mvpsample.utils.Constants.TableUser.DATABASE_VERSION;

/**
 * Created by VIKRAM R on 20/02/2018.
 */

@Database(entities = {User.class}, version = DATABASE_VERSION)
public abstract class MVPDataBase extends RoomDatabase {
    private static MVPDataBase sInstance;

    public abstract UserDAO userDao();

    private static final Object sLock = new Object();

    public static MVPDataBase getInstance(@NonNull final Context context) {
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = buildDatabase(context.getApplicationContext());
            }
            return sInstance;
        }
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static MVPDataBase buildDatabase(final Context appContext) {
        return Room.databaseBuilder(appContext, MVPDataBase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                    }
                }).build();
    }
}
