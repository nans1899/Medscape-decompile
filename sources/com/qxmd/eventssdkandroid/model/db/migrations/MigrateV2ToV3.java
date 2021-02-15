package com.qxmd.eventssdkandroid.model.db.migrations;

import android.database.sqlite.SQLiteDatabase;

public class MigrateV2ToV3 extends MigrationImpl {
    public int getMigratedVersion() {
        return 3;
    }

    public int getTargetVersion() {
        return 2;
    }

    public int applyMigration(SQLiteDatabase sQLiteDatabase, int i) {
        prepareMigration(sQLiteDatabase, i);
        sQLiteDatabase.execSQL("ALTER TABLE EVENT_DB ADD COLUMN SOURCE TEXT");
        return getMigratedVersion();
    }

    public Migration getPreviousMigration() {
        return new MigrateV1ToV2();
    }
}
