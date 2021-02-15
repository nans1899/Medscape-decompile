package com.wbmd.qxcalculator.model.db.v4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.medscape.android.BI.BIParameters;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import java.util.List;

public class DBPlatformDao extends AbstractDao<DBPlatform, Long> {
    public static final String TABLENAME = "DBPLATFORM";
    private Query<DBPlatform> dBContentItem_PlatformsQuery;
    private Query<DBPlatform> dBFeaturedContentAd_PlatformsQuery;

    public static class Properties {
        public static final Property ContentItemId = new Property(6, Long.class, "contentItemId", false, "CONTENT_ITEM_ID");
        public static final Property DeviceType = new Property(3, String.class, "deviceType", false, "DEVICE_TYPE");
        public static final Property FeaturedContentAdId = new Property(7, Long.class, "featuredContentAdId", false, "FEATURED_CONTENT_AD_ID");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property MaxVersion = new Property(5, String.class, "maxVersion", false, "MAX_VERSION");
        public static final Property MinVersion = new Property(4, String.class, "minVersion", false, "MIN_VERSION");
        public static final Property Os = new Property(2, String.class, AdParameterKeys.OS, false, BIParameters.OS);
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBPlatformDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBPlatformDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBPLATFORM' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'OS' TEXT,'DEVICE_TYPE' TEXT,'MIN_VERSION' TEXT,'MAX_VERSION' TEXT,'CONTENT_ITEM_ID' INTEGER,'FEATURED_CONTENT_AD_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBPLATFORM'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBPlatform dBPlatform) {
        sQLiteStatement.clearBindings();
        Long id = dBPlatform.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBPlatform.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String os = dBPlatform.getOs();
        if (os != null) {
            sQLiteStatement.bindString(3, os);
        }
        String deviceType = dBPlatform.getDeviceType();
        if (deviceType != null) {
            sQLiteStatement.bindString(4, deviceType);
        }
        String minVersion = dBPlatform.getMinVersion();
        if (minVersion != null) {
            sQLiteStatement.bindString(5, minVersion);
        }
        String maxVersion = dBPlatform.getMaxVersion();
        if (maxVersion != null) {
            sQLiteStatement.bindString(6, maxVersion);
        }
        Long contentItemId = dBPlatform.getContentItemId();
        if (contentItemId != null) {
            sQLiteStatement.bindLong(7, contentItemId.longValue());
        }
        Long featuredContentAdId = dBPlatform.getFeaturedContentAdId();
        if (featuredContentAdId != null) {
            sQLiteStatement.bindLong(8, featuredContentAdId.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBPlatform readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        String string3 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        String string4 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 5;
        String string5 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = i + 6;
        int i9 = i + 7;
        return new DBPlatform(valueOf, string, string2, string3, string4, string5, cursor.isNull(i8) ? null : Long.valueOf(cursor.getLong(i8)), cursor.isNull(i9) ? null : Long.valueOf(cursor.getLong(i9)));
    }

    public void readEntity(Cursor cursor, DBPlatform dBPlatform, int i) {
        int i2 = i + 0;
        Long l = null;
        dBPlatform.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBPlatform.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBPlatform.setOs(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBPlatform.setDeviceType(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBPlatform.setMinVersion(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        dBPlatform.setMaxVersion(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 6;
        dBPlatform.setContentItemId(cursor.isNull(i8) ? null : Long.valueOf(cursor.getLong(i8)));
        int i9 = i + 7;
        if (!cursor.isNull(i9)) {
            l = Long.valueOf(cursor.getLong(i9));
        }
        dBPlatform.setFeaturedContentAdId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBPlatform dBPlatform, long j) {
        dBPlatform.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBPlatform dBPlatform) {
        if (dBPlatform != null) {
            return dBPlatform.getId();
        }
        return null;
    }

    public List<DBPlatform> _queryDBContentItem_Platforms(Long l) {
        synchronized (this) {
            if (this.dBContentItem_PlatformsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ContentItemId.eq((Object) null), new WhereCondition[0]);
                this.dBContentItem_PlatformsQuery = queryBuilder.build();
            }
        }
        Query<DBPlatform> forCurrentThread = this.dBContentItem_PlatformsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }

    public List<DBPlatform> _queryDBFeaturedContentAd_Platforms(Long l) {
        synchronized (this) {
            if (this.dBFeaturedContentAd_PlatformsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.FeaturedContentAdId.eq((Object) null), new WhereCondition[0]);
                this.dBFeaturedContentAd_PlatformsQuery = queryBuilder.build();
            }
        }
        Query<DBPlatform> forCurrentThread = this.dBFeaturedContentAd_PlatformsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
