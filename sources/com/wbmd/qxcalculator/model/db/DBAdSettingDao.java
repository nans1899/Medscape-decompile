package com.wbmd.qxcalculator.model.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

public class DBAdSettingDao extends AbstractDao<DBAdSetting, Long> {
    public static final String TABLENAME = "DBAD_SETTING";
    private DaoSession daoSession;

    public static class Properties {
        public static final Property AdUnitId = new Property(3, String.class, "adUnitId", false, "AD_UNIT_ID");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property Type = new Property(2, String.class, "type", false, "TYPE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBAdSettingDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBAdSettingDao(DaoConfig daoConfig, DaoSession daoSession2) {
        super(daoConfig, daoSession2);
        this.daoSession = daoSession2;
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBAD_SETTING' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'TYPE' TEXT,'AD_UNIT_ID' TEXT);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBAD_SETTING'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBAdSetting dBAdSetting) {
        sQLiteStatement.clearBindings();
        Long id = dBAdSetting.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBAdSetting.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String type = dBAdSetting.getType();
        if (type != null) {
            sQLiteStatement.bindString(3, type);
        }
        String adUnitId = dBAdSetting.getAdUnitId();
        if (adUnitId != null) {
            sQLiteStatement.bindString(4, adUnitId);
        }
    }

    /* access modifiers changed from: protected */
    public void attachEntity(DBAdSetting dBAdSetting) {
        super.attachEntity(dBAdSetting);
        dBAdSetting.__setDaoSession(this.daoSession);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBAdSetting readEntity(Cursor cursor, int i) {
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
        return new DBAdSetting(valueOf, string, string2, str);
    }

    public void readEntity(Cursor cursor, DBAdSetting dBAdSetting, int i) {
        int i2 = i + 0;
        String str = null;
        dBAdSetting.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBAdSetting.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBAdSetting.setType(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            str = cursor.getString(i5);
        }
        dBAdSetting.setAdUnitId(str);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBAdSetting dBAdSetting, long j) {
        dBAdSetting.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBAdSetting dBAdSetting) {
        if (dBAdSetting != null) {
            return dBAdSetting.getId();
        }
        return null;
    }
}
