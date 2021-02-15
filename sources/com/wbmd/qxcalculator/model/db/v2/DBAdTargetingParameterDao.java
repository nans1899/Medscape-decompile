package com.wbmd.qxcalculator.model.db.v2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import java.util.List;

public class DBAdTargetingParameterDao extends AbstractDao<DBAdTargetingParameter, Long> {
    public static final String TABLENAME = "DBAD_TARGETING_PARAMETER";
    private Query<DBAdTargetingParameter> dBAdSetting_AdTargetingParametersQuery;

    public static class Properties {
        public static final Property AdSettingId = new Property(4, Long.class, "adSettingId", false, "AD_SETTING_ID");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property Key = new Property(2, String.class, "key", false, "KEY");
        public static final Property Value = new Property(3, String.class, "value", false, "VALUE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBAdTargetingParameterDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBAdTargetingParameterDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBAD_TARGETING_PARAMETER' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'KEY' TEXT,'VALUE' TEXT,'AD_SETTING_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBAD_TARGETING_PARAMETER'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBAdTargetingParameter dBAdTargetingParameter) {
        sQLiteStatement.clearBindings();
        Long id = dBAdTargetingParameter.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBAdTargetingParameter.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String key = dBAdTargetingParameter.getKey();
        if (key != null) {
            sQLiteStatement.bindString(3, key);
        }
        String value = dBAdTargetingParameter.getValue();
        if (value != null) {
            sQLiteStatement.bindString(4, value);
        }
        Long adSettingId = dBAdTargetingParameter.getAdSettingId();
        if (adSettingId != null) {
            sQLiteStatement.bindLong(5, adSettingId.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBAdTargetingParameter readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        int i6 = i + 4;
        return new DBAdTargetingParameter(valueOf, string, string2, cursor.isNull(i5) ? null : cursor.getString(i5), cursor.isNull(i6) ? null : Long.valueOf(cursor.getLong(i6)));
    }

    public void readEntity(Cursor cursor, DBAdTargetingParameter dBAdTargetingParameter, int i) {
        int i2 = i + 0;
        Long l = null;
        dBAdTargetingParameter.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBAdTargetingParameter.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBAdTargetingParameter.setKey(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBAdTargetingParameter.setValue(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        if (!cursor.isNull(i6)) {
            l = Long.valueOf(cursor.getLong(i6));
        }
        dBAdTargetingParameter.setAdSettingId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBAdTargetingParameter dBAdTargetingParameter, long j) {
        dBAdTargetingParameter.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBAdTargetingParameter dBAdTargetingParameter) {
        if (dBAdTargetingParameter != null) {
            return dBAdTargetingParameter.getId();
        }
        return null;
    }

    public List<DBAdTargetingParameter> _queryDBAdSetting_AdTargetingParameters(Long l) {
        synchronized (this) {
            if (this.dBAdSetting_AdTargetingParametersQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.AdSettingId.eq((Object) null), new WhereCondition[0]);
                this.dBAdSetting_AdTargetingParametersQuery = queryBuilder.build();
            }
        }
        Query<DBAdTargetingParameter> forCurrentThread = this.dBAdSetting_AdTargetingParametersQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
