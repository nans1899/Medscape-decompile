package com.wbmd.qxcalculator.model.db.managers;

import android.database.sqlite.SQLiteDatabase;

public class MigrateV3ToV4 extends MigrationImpl {
    public int getMigratedVersion() {
        return 4;
    }

    public int getTargetVersion() {
        return 3;
    }

    public int applyMigration(SQLiteDatabase sQLiteDatabase, int i) {
        prepareMigration(sQLiteDatabase, i);
        sQLiteDatabase.execSQL("ALTER TABLE DBQUESTION ADD COLUMN ALLOW_NEGATIVE_ANSWER INTEGER");
        return getMigratedVersion();
    }

    public Migration getPreviousMigration() {
        return new MigrateV2ToV3();
    }
}
