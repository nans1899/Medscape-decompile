package com.wbmd.qxcalculator.model.db.managers;

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
        sQLiteDatabase.execSQL("ALTER TABLE DBANSWER_CHOICE ADD COLUMN SUCCESS_MESSAGE TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE DBANSWER_CHOICE ADD COLUMN WARNING_MESSAGE TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE DBANSWER_CHOICE ADD COLUMN ERROR_MESSAGE TEXT");
        return getMigratedVersion();
    }
}
