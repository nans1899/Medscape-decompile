package com.qxmd.eventssdkandroid.model.db.v2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 2;

    public static void createAllTables(SQLiteDatabase sQLiteDatabase, boolean z) {
        EventDBDao.createTable(sQLiteDatabase, z);
    }

    public static void dropAllTables(SQLiteDatabase sQLiteDatabase, boolean z) {
        EventDBDao.dropTable(sQLiteDatabase, z);
    }

    public static abstract class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory, 2);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            Log.i("greenDAO", "Creating tables for schema version 2");
            DaoMaster.createAllTables(sQLiteDatabase, false);
        }
    }

    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Log.i("greenDAO", "Upgrading schema from version " + i + " to " + i2 + " by dropping all tables");
            DaoMaster.dropAllTables(sQLiteDatabase, true);
            onCreate(sQLiteDatabase);
        }
    }

    public DaoMaster(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase, 2);
        registerDaoClass(EventDBDao.class);
    }

    public DaoSession newSession() {
        return new DaoSession(this.db, IdentityScopeType.Session, this.daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType identityScopeType) {
        return new DaoSession(this.db, identityScopeType, this.daoConfigMap);
    }
}
