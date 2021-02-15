package com.wbmd.qxcalculator.model.db.managers;

import android.database.sqlite.SQLiteDatabase;

public class MigrateV10ToV11 extends MigrationImpl {
    public int getMigratedVersion() {
        return 11;
    }

    public int getTargetVersion() {
        return 10;
    }

    public int applyMigration(SQLiteDatabase sQLiteDatabase, int i) {
        prepareMigration(sQLiteDatabase, i);
        sQLiteDatabase.execSQL("ALTER TABLE DBCONTENT_ITEM ADD COLUMN LEAD_SPECIALTY TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE DBCONTENT_ITEM ADD COLUMN LEAD_CONCEPT TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE DBCONTENT_ITEM ADD COLUMN ALL_SPECIALTY TEXT");
        return getMigratedVersion();
    }

    public Migration getPreviousMigration() {
        return new MigrateV9ToV10();
    }
}
