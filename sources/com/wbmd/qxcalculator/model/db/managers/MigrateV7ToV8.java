package com.wbmd.qxcalculator.model.db.managers;

import android.database.sqlite.SQLiteDatabase;

public class MigrateV7ToV8 extends MigrationImpl {
    public int getMigratedVersion() {
        return 8;
    }

    public int getTargetVersion() {
        return 7;
    }

    public int applyMigration(SQLiteDatabase sQLiteDatabase, int i) {
        prepareMigration(sQLiteDatabase, i);
        sQLiteDatabase.execSQL("ALTER TABLE DBFEATURED_CONTENT_AD ADD COLUMN DISCLAIMER TEXT");
        return getMigratedVersion();
    }

    public Migration getPreviousMigration() {
        return new MigrateV6ToV7();
    }
}
