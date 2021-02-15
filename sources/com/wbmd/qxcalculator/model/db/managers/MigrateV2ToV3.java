package com.wbmd.qxcalculator.model.db.managers;

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
        sQLiteDatabase.execSQL("ALTER TABLE DBUSER ADD COLUMN PN_ENABLED INTEGER DEFAULT 1");
        sQLiteDatabase.execSQL("ALTER TABLE DBUSER ADD COLUMN SHOW_ITEM_DESCRIPTION INTEGER DEFAULT 1");
        sQLiteDatabase.execSQL("ALTER TABLE DBUSER ADD COLUMN BANNER_ADS_ENABLED INTEGER DEFAULT 1");
        sQLiteDatabase.execSQL("ALTER TABLE DBUSER ADD COLUMN SHOULD_SEND_REMOVE_PN_TOKEN_REQUEST INTEGER");
        sQLiteDatabase.execSQL("ALTER TABLE DBCONTENT_ITEM ADD COLUMN DATE_ADDED INTEGER");
        sQLiteDatabase.execSQL("CREATE TABLE 'DBPUSH_NOTIFICATION' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'MESSAGE' TEXT,'READ_DATE' INTEGER,'RECEIVED_DATE' INTEGER);");
        return getMigratedVersion();
    }

    public Migration getPreviousMigration() {
        return new MigrateV1ToV2();
    }
}
