package com.qxmd.eventssdkandroid.managers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.qxmd.eventssdkandroid.model.db.DaoMaster;
import com.qxmd.eventssdkandroid.model.db.DaoSession;
import com.qxmd.eventssdkandroid.model.db.migrations.DaoUpgradeHelper;

public class DatabaseManager {
    private static final String DB_NAME = "qxevents-db";
    private static DatabaseManager instance;
    private DaoSession daoSession;
    private SQLiteDatabase database;

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private DatabaseManager() {
    }

    public DaoSession loadDatabase(Context context) {
        if (this.database != null) {
            unloadDatabase();
        }
        this.database = new DaoUpgradeHelper(context, DB_NAME, (SQLiteDatabase.CursorFactory) null).getWritableDatabase();
        DaoSession newSession = new DaoMaster(this.database).newSession();
        this.daoSession = newSession;
        return newSession;
    }

    public void unloadDatabase() {
        SQLiteDatabase sQLiteDatabase = this.database;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
            this.database = null;
            this.daoSession = null;
        }
    }

    public boolean hasOpenedSession() {
        SQLiteDatabase sQLiteDatabase = this.database;
        return sQLiteDatabase != null && sQLiteDatabase.isOpen();
    }

    public DaoSession getDaoSession() {
        return this.daoSession;
    }
}
