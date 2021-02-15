package com.wbmd.qxcalculator.model.db.managers;

import android.database.sqlite.SQLiteDatabase;

public abstract class MigrationImpl implements Migration {
    /* access modifiers changed from: protected */
    public void prepareMigration(SQLiteDatabase sQLiteDatabase, int i) {
        if (sQLiteDatabase == null) {
            throw new IllegalArgumentException("Database cannot be null");
        } else if (i < 1) {
            throw new IllegalArgumentException("Lowest suported schema version is 1, unable to prepare for migration from version: " + i);
        } else if (i < getTargetVersion()) {
            Migration previousMigration = getPreviousMigration();
            if (previousMigration == null && i != getTargetVersion()) {
                throw new IllegalStateException("Unable to apply migration as Version: " + i + " is not suitable for this Migration.");
            } else if (previousMigration != null && previousMigration.applyMigration(sQLiteDatabase, i) != getTargetVersion()) {
                throw new IllegalStateException("Error, expected migration parent to update database to appropriate version");
            }
        }
    }
}
