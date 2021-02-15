package com.qxmd.eventssdkandroid.model.db.migrations;

import android.database.sqlite.SQLiteDatabase;

public class MigrateV1ToV2 extends MigrationImpl {
    public int getMigratedVersion() {
        return 2;
    }

    public Migration getPreviousMigration() {
        return null;
    }

    public int getTargetVersion() {
        return 1;
    }

    public int applyMigration(SQLiteDatabase sQLiteDatabase, int i) {
        prepareMigration(sQLiteDatabase, i);
        sQLiteDatabase.execSQL("ALTER TABLE EVENT_DB ADD COLUMN CATEGORY TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE EVENT_DB ADD COLUMN LABEL TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE EVENT_DB ADD COLUMN URL TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE EVENT_DB ADD COLUMN CAMPAIGN_AD_ID INTEGER");
        return getMigratedVersion();
    }
}
