package com.wbmd.qxcalculator.model.db.v5;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

public class DBCommonFileDao extends AbstractDao<DBCommonFile, Long> {
    public static final String TABLENAME = "DBCOMMON_FILE";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBCommonFileDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBCommonFileDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBCOMMON_FILE' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBCOMMON_FILE'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBCommonFile dBCommonFile) {
        sQLiteStatement.clearBindings();
        Long id = dBCommonFile.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBCommonFile.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBCommonFile readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        String str = null;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        if (!cursor.isNull(i3)) {
            str = cursor.getString(i3);
        }
        return new DBCommonFile(valueOf, str);
    }

    public void readEntity(Cursor cursor, DBCommonFile dBCommonFile, int i) {
        int i2 = i + 0;
        String str = null;
        dBCommonFile.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        if (!cursor.isNull(i3)) {
            str = cursor.getString(i3);
        }
        dBCommonFile.setIdentifier(str);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBCommonFile dBCommonFile, long j) {
        dBCommonFile.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBCommonFile dBCommonFile) {
        if (dBCommonFile != null) {
            return dBCommonFile.getId();
        }
        return null;
    }
}
