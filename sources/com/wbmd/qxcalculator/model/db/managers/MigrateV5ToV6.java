package com.wbmd.qxcalculator.model.db.managers;

import android.database.sqlite.SQLiteDatabase;

public class MigrateV5ToV6 extends MigrationImpl {
    public int getMigratedVersion() {
        return 6;
    }

    public int getTargetVersion() {
        return 5;
    }

    public int applyMigration(SQLiteDatabase sQLiteDatabase, int i) {
        prepareMigration(sQLiteDatabase, i);
        sQLiteDatabase.execSQL("CREATE TABLE 'DBSPLASH_PAGE' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'SOURCE' TEXT,'TYPE' TEXT);");
        sQLiteDatabase.execSQL("ALTER TABLE DBCONTENT_ITEM ADD COLUMN SPLASH_PAGE_ID INTEGER");
        sQLiteDatabase.execSQL("ALTER TABLE DBCONTENT_ITEM ADD COLUMN COPIED_FROM_CONTENT_ITEM_ID TEXT");
        return getMigratedVersion();
    }

    public Migration getPreviousMigration() {
        return new MigrateV4ToV5();
    }
}
