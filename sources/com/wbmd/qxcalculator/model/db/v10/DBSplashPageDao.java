package com.wbmd.qxcalculator.model.db.v10;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

public class DBSplashPageDao extends AbstractDao<DBSplashPage, Long> {
    public static final String TABLENAME = "DBSPLASH_PAGE";

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

    public DBSplashPageDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBSplashPageDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBSPLASH_PAGE' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'SOURCE' TEXT,'TYPE' TEXT);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBSPLASH_PAGE'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBSplashPage dBSplashPage) {
        sQLiteStatement.clearBindings();
        Long id = dBSplashPage.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBSplashPage.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String source = dBSplashPage.getSource();
        if (source != null) {
            sQLiteStatement.bindString(3, source);
        }
        String type = dBSplashPage.getType();
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

    public DBSplashPage readEntity(Cursor cursor, int i) {
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
        return new DBSplashPage(valueOf, string, string2, str);
    }

    public void readEntity(Cursor cursor, DBSplashPage dBSplashPage, int i) {
        int i2 = i + 0;
        String str = null;
        dBSplashPage.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBSplashPage.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBSplashPage.setSource(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            str = cursor.getString(i5);
        }
        dBSplashPage.setType(str);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBSplashPage dBSplashPage, long j) {
        dBSplashPage.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBSplashPage dBSplashPage) {
        if (dBSplashPage != null) {
            return dBSplashPage.getId();
        }
        return null;
    }
}
