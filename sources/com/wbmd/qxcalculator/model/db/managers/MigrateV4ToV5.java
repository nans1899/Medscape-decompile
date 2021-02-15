package com.wbmd.qxcalculator.model.db.managers;

import android.database.sqlite.SQLiteDatabase;

public class MigrateV4ToV5 extends MigrationImpl {
    public int getMigratedVersion() {
        return 5;
    }

    public int getTargetVersion() {
        return 4;
    }

    public int applyMigration(SQLiteDatabase sQLiteDatabase, int i) {
        prepareMigration(sQLiteDatabase, i);
        sQLiteDatabase.execSQL("CREATE TABLE 'DBRESTRICTION' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'ALERT_TITLE' TEXT,'ALERT_MESSAGE' TEXT,'ALTERNATE_URL' TEXT,'CONTENT_ITEM_ID' INTEGER);");
        sQLiteDatabase.execSQL("ALTER TABLE DBFILTER ADD COLUMN RESTRICTION_ID INTEGER");
        sQLiteDatabase.execSQL("ALTER TABLE DBPLATFORM ADD COLUMN RESTRICTION_ID INTEGER");
        return getMigratedVersion();
    }

    public Migration getPreviousMigration() {
        return new MigrateV3ToV4();
    }
}
