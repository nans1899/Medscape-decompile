package com.wbmd.qxcalculator.model.db.v1;

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

public class DBFilterDao extends AbstractDao<DBFilter, Long> {
    public static final String TABLENAME = "DBFILTER";
    private Query<DBFilter> dBContentItem_FiltersQuery;
    private Query<DBFilter> dBFeaturedContentAd_FiltersQuery;
    private DaoSession daoSession;

    public static class Properties {
        public static final Property ContentItemId = new Property(3, Long.class, "contentItemId", false, "CONTENT_ITEM_ID");
        public static final Property FeaturedContentAdId = new Property(4, Long.class, "featuredContentAdId", false, "FEATURED_CONTENT_AD_ID");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property Type = new Property(2, String.class, "type", false, "TYPE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBFilterDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBFilterDao(DaoConfig daoConfig, DaoSession daoSession2) {
        super(daoConfig, daoSession2);
        this.daoSession = daoSession2;
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBFILTER' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'TYPE' TEXT,'CONTENT_ITEM_ID' INTEGER,'FEATURED_CONTENT_AD_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBFILTER'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBFilter dBFilter) {
        sQLiteStatement.clearBindings();
        Long id = dBFilter.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBFilter.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String type = dBFilter.getType();
        if (type != null) {
            sQLiteStatement.bindString(3, type);
        }
        Long contentItemId = dBFilter.getContentItemId();
        if (contentItemId != null) {
            sQLiteStatement.bindLong(4, contentItemId.longValue());
        }
        Long featuredContentAdId = dBFilter.getFeaturedContentAdId();
        if (featuredContentAdId != null) {
            sQLiteStatement.bindLong(5, featuredContentAdId.longValue());
        }
    }

    /* access modifiers changed from: protected */
    public void attachEntity(DBFilter dBFilter) {
        super.attachEntity(dBFilter);
        dBFilter.__setDaoSession(this.daoSession);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBFilter readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        int i6 = i + 4;
        return new DBFilter(valueOf, string, string2, cursor.isNull(i5) ? null : Long.valueOf(cursor.getLong(i5)), cursor.isNull(i6) ? null : Long.valueOf(cursor.getLong(i6)));
    }

    public void readEntity(Cursor cursor, DBFilter dBFilter, int i) {
        int i2 = i + 0;
        Long l = null;
        dBFilter.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBFilter.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBFilter.setType(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBFilter.setContentItemId(cursor.isNull(i5) ? null : Long.valueOf(cursor.getLong(i5)));
        int i6 = i + 4;
        if (!cursor.isNull(i6)) {
            l = Long.valueOf(cursor.getLong(i6));
        }
        dBFilter.setFeaturedContentAdId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBFilter dBFilter, long j) {
        dBFilter.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBFilter dBFilter) {
        if (dBFilter != null) {
            return dBFilter.getId();
        }
        return null;
    }

    public List<DBFilter> _queryDBContentItem_Filters(Long l) {
        synchronized (this) {
            if (this.dBContentItem_FiltersQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ContentItemId.eq((Object) null), new WhereCondition[0]);
                this.dBContentItem_FiltersQuery = queryBuilder.build();
            }
        }
        Query<DBFilter> forCurrentThread = this.dBContentItem_FiltersQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }

    public List<DBFilter> _queryDBFeaturedContentAd_Filters(Long l) {
        synchronized (this) {
            if (this.dBFeaturedContentAd_FiltersQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.FeaturedContentAdId.eq((Object) null), new WhereCondition[0]);
                this.dBFeaturedContentAd_FiltersQuery = queryBuilder.build();
            }
        }
        Query<DBFilter> forCurrentThread = this.dBFeaturedContentAd_FiltersQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
