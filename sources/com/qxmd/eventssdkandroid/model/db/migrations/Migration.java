package com.qxmd.eventssdkandroid.model.db.migrations;

import android.database.sqlite.SQLiteDatabase;

public interface Migration {
    int applyMigration(SQLiteDatabase sQLiteDatabase, int i);

    int getMigratedVersion();

    Migration getPreviousMigration();

    int getTargetVersion();
}
