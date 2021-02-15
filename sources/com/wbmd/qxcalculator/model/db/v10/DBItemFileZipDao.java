package com.wbmd.qxcalculator.model.db.v10;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.medscape.android.db.FeedDetail;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

public class DBItemFileZipDao extends AbstractDao<DBItemFileZip, Long> {
    public static final String TABLENAME = "DBITEM_FILE_ZIP";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property Size = new Property(3, Long.class, "size", false, "SIZE");
        public static final Property Url = new Property(2, String.class, "url", false, FeedDetail.F_URL);
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBItemFileZipDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBItemFileZipDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBITEM_FILE_ZIP' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'URL' TEXT,'SIZE' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBITEM_FILE_ZIP'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBItemFileZip dBItemFileZip) {
        sQLiteStatement.clearBindings();
        Long id = dBItemFileZip.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBItemFileZip.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String url = dBItemFileZip.getUrl();
        if (url != null) {
            sQLiteStatement.bindString(3, url);
        }
        Long size = dBItemFileZip.getSize();
        if (size != null) {
            sQLiteStatement.bindLong(4, size.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBItemFileZip readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long l = null;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            l = Long.valueOf(cursor.getLong(i5));
        }
        return new DBItemFileZip(valueOf, string, string2, l);
    }

    public void readEntity(Cursor cursor, DBItemFileZip dBItemFileZip, int i) {
        int i2 = i + 0;
        Long l = null;
        dBItemFileZip.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBItemFileZip.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBItemFileZip.setUrl(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            l = Long.valueOf(cursor.getLong(i5));
        }
        dBItemFileZip.setSize(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBItemFileZip dBItemFileZip, long j) {
        dBItemFileZip.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBItemFileZip dBItemFileZip) {
        if (dBItemFileZip != null) {
            return dBItemFileZip.getId();
        }
        return null;
    }
}
