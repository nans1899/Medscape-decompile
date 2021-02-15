package com.medscape.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.helper.FileHelper;
import java.io.IOException;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DB_NAME = "Medscape.sql";
    private static String DB_PATH;
    private String mDBName;
    private SQLiteDatabase myDataBase;

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        DB_PATH = FileHelper.getDataDirectory(context) + "/Medscape/";
        this.mDBName = DB_NAME;
    }

    public DatabaseHelper(Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        DB_PATH = FileHelper.getDataDirectory(MedscapeApplication.get()) + "/Medscape/";
        this.mDBName = str;
    }

    private void createDataBase() throws IOException {
        if (!checkDataBase()) {
            getReadableDatabase();
        }
    }

    public boolean checkDataBase() {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH + this.mDBName, (SQLiteDatabase.CursorFactory) null, 17);
        } catch (SQLiteException unused) {
        }
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
        return sQLiteDatabase != null;
    }

    public static boolean checkDataBase(String str) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = SQLiteDatabase.openDatabase(str, (SQLiteDatabase.CursorFactory) null, 17);
        } catch (SQLiteException unused) {
        }
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
        return sQLiteDatabase != null;
    }

    public void openDataBase() throws Exception {
        createDataBase();
        this.myDataBase = SQLiteDatabase.openDatabase(DB_PATH + this.mDBName, (SQLiteDatabase.CursorFactory) null, 17);
    }

    public SQLiteDatabase getDatabase() throws Exception {
        SQLiteDatabase sQLiteDatabase = this.myDataBase;
        if (sQLiteDatabase == null) {
            openDataBase();
        } else if (!sQLiteDatabase.isOpen()) {
            openDataBase();
        }
        return this.myDataBase;
    }

    public synchronized void close() {
        if (this.myDataBase != null) {
            this.myDataBase.close();
        }
        super.close();
    }
}
