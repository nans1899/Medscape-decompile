package com.wbmd.qxcalculator.model.db.managers;

import android.database.sqlite.SQLiteDatabase;

public class MigrateV8ToV9 extends MigrationImpl {
    public int getMigratedVersion() {
        return 9;
    }

    public int getTargetVersion() {
        return 8;
    }

    public int applyMigration(SQLiteDatabase sQLiteDatabase, int i) {
        prepareMigration(sQLiteDatabase, i);
        sQLiteDatabase.execSQL("CREATE TABLE 'DBPROMOTION' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'TITLE' TEXT,'FOOTER' TEXT,'CAMPAIGN_AD_ID' INTEGER,'CONTENT_ITEM_ID' INTEGER);");
        sQLiteDatabase.execSQL("ALTER TABLE DBFEATURED_CONTENT_AD ADD COLUMN PROMOTION_ID INTEGER");
        sQLiteDatabase.execSQL("ALTER TABLE DBFILTER ADD COLUMN PROMOTION_ID INTEGER");
        sQLiteDatabase.execSQL("ALTER TABLE DBPLATFORM ADD COLUMN PROMOTION_ID INTEGER");
        sQLiteDatabase.execSQL("ALTER TABLE DBPROMOTION ADD COLUMN PROMOTION_ID INTEGER");
        return getMigratedVersion();
    }

    public Migration getPreviousMigration() {
        return new MigrateV7ToV8();
    }
}
