package com.wbmd.qxcalculator.model.db.v5;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

public class DBCalculatorDao extends AbstractDao<DBCalculator, Long> {
    public static final String TABLENAME = "DBCALCULATOR";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBCalculatorDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBCalculatorDao(DaoConfig daoConfig, DaoSession daoSession2) {
        super(daoConfig, daoSession2);
        this.daoSession = daoSession2;
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBCALCULATOR' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBCALCULATOR'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBCalculator dBCalculator) {
        sQLiteStatement.clearBindings();
        Long id = dBCalculator.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBCalculator.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
    }

    /* access modifiers changed from: protected */
    public void attachEntity(DBCalculator dBCalculator) {
        super.attachEntity(dBCalculator);
        dBCalculator.__setDaoSession(this.daoSession);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBCalculator readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        String str = null;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        if (!cursor.isNull(i3)) {
            str = cursor.getString(i3);
        }
        return new DBCalculator(valueOf, str);
    }

    public void readEntity(Cursor cursor, DBCalculator dBCalculator, int i) {
        int i2 = i + 0;
        String str = null;
        dBCalculator.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        if (!cursor.isNull(i3)) {
            str = cursor.getString(i3);
        }
        dBCalculator.setIdentifier(str);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBCalculator dBCalculator, long j) {
        dBCalculator.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBCalculator dBCalculator) {
        if (dBCalculator != null) {
            return dBCalculator.getId();
        }
        return null;
    }
}
