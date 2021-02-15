package com.adobe.mobile;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;

abstract class AbstractDatabaseBacking {
    private File _dbFile = null;
    protected SQLiteDatabase database;
    protected DatabaseStatus databaseStatus;
    protected final Object dbMutex = new Object();
    protected String fileName;
    protected String logPrefix;

    /* access modifiers changed from: protected */
    public void postMigrate() {
    }

    /* access modifiers changed from: protected */
    public void postReset() {
    }

    /* access modifiers changed from: protected */
    public void preMigrate() {
    }

    AbstractDatabaseBacking() {
    }

    enum DatabaseStatus {
        OK(0),
        FATALERROR(1);
        
        public final int id;

        private DatabaseStatus(int i) {
            this.id = i;
        }
    }

    /* access modifiers changed from: protected */
    public void initializeDatabase() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("initializeDatabase must be overwritten");
    }

    /* access modifiers changed from: protected */
    public void prepareStatements() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("prepareStatements must be overwritten");
    }

    private void openOrCreateDatabase() {
        try {
            closeDataBase();
            this.database = SQLiteDatabase.openDatabase(this._dbFile.getPath(), (SQLiteDatabase.CursorFactory) null, 268435472);
            this.databaseStatus = DatabaseStatus.OK;
        } catch (SQLException e) {
            this.databaseStatus = DatabaseStatus.FATALERROR;
            StaticMethods.logErrorFormat("%s - Unable to open database (%s).", this.logPrefix, e.getLocalizedMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void closeDataBase() {
        SQLiteDatabase sQLiteDatabase = this.database;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }

    /* access modifiers changed from: protected */
    public void initDatabaseBacking(File file) {
        this._dbFile = file;
        synchronized (this.dbMutex) {
            preMigrate();
            openOrCreateDatabase();
            if (this.database != null) {
                postMigrate();
                initializeDatabase();
                prepareStatements();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void resetDatabase(Exception exc) {
        StaticMethods.logErrorFormat("%s - Database in unrecoverable state (%s), resetting.", this.logPrefix, exc.getLocalizedMessage());
        synchronized (this.dbMutex) {
            if (!this._dbFile.exists() || this._dbFile.delete()) {
                StaticMethods.logDebugFormat("%s - Database file(%s) was corrupt and had to be deleted.", this.logPrefix, this._dbFile.getAbsolutePath());
                openOrCreateDatabase();
                initializeDatabase();
                prepareStatements();
                postReset();
                return;
            }
            StaticMethods.logErrorFormat("%s - Failed to delete database file(%s).", this.logPrefix, this._dbFile.getAbsolutePath());
            this.databaseStatus = DatabaseStatus.FATALERROR;
        }
    }

    protected static class CorruptedDatabaseException extends Exception {
        public CorruptedDatabaseException(String str) {
            super(str);
        }
    }
}
