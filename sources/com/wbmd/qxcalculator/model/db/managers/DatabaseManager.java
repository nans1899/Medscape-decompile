package com.wbmd.qxcalculator.model.db.managers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.wbmd.qxcalculator.model.db.DaoMaster;
import com.wbmd.qxcalculator.model.db.DaoSession;

public class DatabaseManager {
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

    public DaoSession loadDatabase(Context context, String str) {
        if (this.database != null) {
            unloadDatabase();
        }
        SQLiteDatabase writableDatabase = new DaoUpgradeHelper(context, str, (SQLiteDatabase.CursorFactory) null).getWritableDatabase();
        this.database = writableDatabase;
        DaoSession newSession = new DaoMaster(writableDatabase).newSession();
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
