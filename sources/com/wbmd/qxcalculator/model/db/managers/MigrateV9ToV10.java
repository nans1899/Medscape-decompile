package com.wbmd.qxcalculator.model.db.managers;

import android.database.sqlite.SQLiteDatabase;

public class MigrateV9ToV10 extends MigrationImpl {
    public int getMigratedVersion() {
        return 10;
    }

    public int getTargetVersion() {
        return 9;
    }

    public int applyMigration(SQLiteDatabase sQLiteDatabase, int i) {
        prepareMigration(sQLiteDatabase, i);
        sQLiteDatabase.execSQL("ALTER TABLE DBUSER ADD COLUMN IDENTIFIER_FOR_LEGACY_USER INTEGER");
        return getMigratedVersion();
    }

    public Migration getPreviousMigration() {
        return new MigrateV8ToV9();
    }
}
