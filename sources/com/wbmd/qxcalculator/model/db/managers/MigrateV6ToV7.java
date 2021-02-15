package com.wbmd.qxcalculator.model.db.managers;

import android.database.sqlite.SQLiteDatabase;

public class MigrateV6ToV7 extends MigrationImpl {
    public int getMigratedVersion() {
        return 7;
    }

    public int getTargetVersion() {
        return 6;
    }

    public int applyMigration(SQLiteDatabase sQLiteDatabase, int i) {
        prepareMigration(sQLiteDatabase, i);
        sQLiteDatabase.execSQL("ALTER TABLE DBUSER ADD COLUMN HAS_SUCCESSFULLY_INITIALIZED INTEGER");
        sQLiteDatabase.execSQL("ALTER TABLE DBUSER ADD COLUMN PN_TOKEN_SENT_TO_CALCULATE_SERVER TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE DBUSER ADD COLUMN PN_TOKEN_SENT_TO_LIST_SERVER TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE DBUSER ADD COLUMN SHOULD_VERIFY_PROFILE INTEGER");
        sQLiteDatabase.execSQL("ALTER TABLE DBUSER ADD COLUMN NB_ACCOUNT_UPGRADE_DEFERRALS INTEGER");
        sQLiteDatabase.execSQL("ALTER TABLE DBUSER ADD COLUMN DATE_OF_NEXT_ACCOUNT_UPGRADE_REQUEST INTEGER");
        sQLiteDatabase.execSQL("ALTER TABLE DBUSER ADD COLUMN DID_LINK_ACCOUNT INTEGER");
        sQLiteDatabase.execSQL("ALTER TABLE DBUSER ADD COLUMN NEEDS_TO_SYNC_WITH_CALCULATE_SERVER INTEGER");
        sQLiteDatabase.execSQL("ALTER TABLE DBUSER ADD COLUMN DEBUG_GROUPS TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE DBUSER ADD COLUMN ACCESS_PRIVILEGES INTEGER");
        sQLiteDatabase.execSQL("ALTER TABLE DBCONTENT_ITEM ADD COLUMN IS_RECENT INTEGER");
        sQLiteDatabase.execSQL("CREATE TABLE 'DBCONSENT_USER' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'LABEL' TEXT,'IS_OPT_IN' INTEGER,'IS_EXPLICIT' INTEGER,'USER_ID' INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE 'DBCONSENT_LOCATION' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'LABEL' TEXT,'TITLE' TEXT,'SUB_TITLE' TEXT,'SHORT_NAME' TEXT,'EXPLICIT_REQUIRED' INTEGER,'OPT_IN_REQUIRED' INTEGER,'IS_EDITABLE' INTEGER,'DEFAULT_OPT_IN' INTEGER,'USER_ID_CONSENT_LOCATIONS_REQUIRED' INTEGER,'USER_ID_CONSENT_LOCATIONS_TO_REQUEST' INTEGER);");
        return getMigratedVersion();
    }

    public Migration getPreviousMigration() {
        return new MigrateV5ToV6();
    }
}
