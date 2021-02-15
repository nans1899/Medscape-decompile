package com.wbmd.qxcalculator.model.db.v8;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

public class DBReferenceBookDao extends AbstractDao<DBReferenceBook, Long> {
    public static final String TABLENAME = "DBREFERENCE_BOOK";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property TitlePageBackgroundColor = new Property(3, String.class, "titlePageBackgroundColor", false, "TITLE_PAGE_BACKGROUND_COLOR");
        public static final Property TitlePageSource = new Property(2, String.class, "titlePageSource", false, "TITLE_PAGE_SOURCE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBReferenceBookDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBReferenceBookDao(DaoConfig daoConfig, DaoSession daoSession2) {
        super(daoConfig, daoSession2);
        this.daoSession = daoSession2;
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBREFERENCE_BOOK' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'TITLE_PAGE_SOURCE' TEXT,'TITLE_PAGE_BACKGROUND_COLOR' TEXT);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBREFERENCE_BOOK'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBReferenceBook dBReferenceBook) {
        sQLiteStatement.clearBindings();
        Long id = dBReferenceBook.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBReferenceBook.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String titlePageSource = dBReferenceBook.getTitlePageSource();
        if (titlePageSource != null) {
            sQLiteStatement.bindString(3, titlePageSource);
        }
        String titlePageBackgroundColor = dBReferenceBook.getTitlePageBackgroundColor();
        if (titlePageBackgroundColor != null) {
            sQLiteStatement.bindString(4, titlePageBackgroundColor);
        }
    }

    /* access modifiers changed from: protected */
    public void attachEntity(DBReferenceBook dBReferenceBook) {
        super.attachEntity(dBReferenceBook);
        dBReferenceBook.__setDaoSession(this.daoSession);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBReferenceBook readEntity(Cursor cursor, int i) {
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
        return new DBReferenceBook(valueOf, string, string2, str);
    }

    public void readEntity(Cursor cursor, DBReferenceBook dBReferenceBook, int i) {
        int i2 = i + 0;
        String str = null;
        dBReferenceBook.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBReferenceBook.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBReferenceBook.setTitlePageSource(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            str = cursor.getString(i5);
        }
        dBReferenceBook.setTitlePageBackgroundColor(str);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBReferenceBook dBReferenceBook, long j) {
        dBReferenceBook.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBReferenceBook dBReferenceBook) {
        if (dBReferenceBook != null) {
            return dBReferenceBook.getId();
        }
        return null;
    }
}
