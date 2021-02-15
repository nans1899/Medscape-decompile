package com.wbmd.qxcalculator.model.db.managers;

import android.database.sqlite.SQLiteDatabase;

public interface Migration {
    int applyMigration(SQLiteDatabase sQLiteDatabase, int i);

    int getMigratedVersion();

    Migration getPreviousMigration();

    int getTargetVersion();
}
