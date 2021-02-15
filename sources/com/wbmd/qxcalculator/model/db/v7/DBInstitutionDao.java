package com.wbmd.qxcalculator.model.db.v7;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.facebook.appevents.UserDataStore;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

public class DBInstitutionDao extends AbstractDao<DBInstitution, Long> {
    public static final String TABLENAME = "DBINSTITUTION";

    public static class Properties {
        public static final Property Country = new Property(1, String.class, UserDataStore.COUNTRY, false, "COUNTRY");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(2, Long.class, "identifier", false, "IDENTIFIER");
        public static final Property Name = new Property(3, String.class, "name", false, "NAME");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBInstitutionDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBInstitutionDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBINSTITUTION' ('_id' INTEGER PRIMARY KEY ,'COUNTRY' TEXT,'IDENTIFIER' INTEGER,'NAME' TEXT);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBINSTITUTION'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBInstitution dBInstitution) {
        sQLiteStatement.clearBindings();
        Long id = dBInstitution.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String country = dBInstitution.getCountry();
        if (country != null) {
            sQLiteStatement.bindString(2, country);
        }
        Long identifier = dBInstitution.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindLong(3, identifier.longValue());
        }
        String name = dBInstitution.getName();
        if (name != null) {
            sQLiteStatement.bindString(4, name);
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBInstitution readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        String str = null;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        Long valueOf2 = cursor.isNull(i4) ? null : Long.valueOf(cursor.getLong(i4));
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            str = cursor.getString(i5);
        }
        return new DBInstitution(valueOf, string, valueOf2, str);
    }

    public void readEntity(Cursor cursor, DBInstitution dBInstitution, int i) {
        int i2 = i + 0;
        String str = null;
        dBInstitution.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBInstitution.setCountry(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBInstitution.setIdentifier(cursor.isNull(i4) ? null : Long.valueOf(cursor.getLong(i4)));
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            str = cursor.getString(i5);
        }
        dBInstitution.setName(str);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBInstitution dBInstitution, long j) {
        dBInstitution.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBInstitution dBInstitution) {
        if (dBInstitution != null) {
            return dBInstitution.getId();
        }
        return null;
    }
}
