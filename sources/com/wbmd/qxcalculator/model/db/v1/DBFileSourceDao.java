package com.wbmd.qxcalculator.model.db.v1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

public class DBFileSourceDao extends AbstractDao<DBFileSource, Long> {
    public static final String TABLENAME = "DBFILE_SOURCE";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property Source = new Property(2, String.class, "source", false, "SOURCE");
        public static final Property Type = new Property(3, String.class, "type", false, "TYPE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBFileSourceDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBFileSourceDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBFILE_SOURCE' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'SOURCE' TEXT,'TYPE' TEXT);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBFILE_SOURCE'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBFileSource dBFileSource) {
        sQLiteStatement.clearBindings();
        Long id = dBFileSource.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBFileSource.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String source = dBFileSource.getSource();
        if (source != null) {
            sQLiteStatement.bindString(3, source);
        }
        String type = dBFileSource.getType();
        if (type != null) {
            sQLiteStatement.bindString(4, type);
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBFileSource readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        String str = null;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            str = cursor.getString(i5);
        }
        return new DBFileSource(valueOf, string, string2, str);
    }

    public void readEntity(Cursor cursor, DBFileSource dBFileSource, int i) {
        int i2 = i + 0;
        String str = null;
        dBFileSource.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBFileSource.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBFileSource.setSource(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            str = cursor.getString(i5);
        }
        dBFileSource.setType(str);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBFileSource dBFileSource, long j) {
        dBFileSource.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBFileSource dBFileSource) {
        if (dBFileSource != null) {
            return dBFileSource.getId();
        }
        return null;
    }
}
