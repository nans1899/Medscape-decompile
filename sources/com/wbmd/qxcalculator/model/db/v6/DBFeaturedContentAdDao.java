package com.wbmd.qxcalculator.model.db.v6;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.facebook.share.internal.ShareConstants;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import java.util.List;

public class DBFeaturedContentAdDao extends AbstractDao<DBFeaturedContentAd, Long> {
    public static final String TABLENAME = "DBFEATURED_CONTENT_AD";
    private Query<DBFeaturedContentAd> dBContentItem_FeaturedContentAdsQuery;
    private DaoSession daoSession;

    public static class Properties {
        public static final Property BackgroundColor = new Property(6, String.class, "backgroundColor", false, "BACKGROUND_COLOR");
        public static final Property ContentItemId = new Property(8, Long.class, "contentItemId", false, "CONTENT_ITEM_ID");
        public static final Property Description = new Property(3, String.class, "description", false, ShareConstants.DESCRIPTION);
        public static final Property DisplayFrequency = new Property(7, Double.class, "displayFrequency", false, "DISPLAY_FREQUENCY");
        public static final Property Footer = new Property(4, String.class, "footer", false, "FOOTER");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property ImageSource = new Property(5, String.class, "imageSource", false, "IMAGE_SOURCE");
        public static final Property Name = new Property(2, String.class, "name", false, "NAME");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBFeaturedContentAdDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBFeaturedContentAdDao(DaoConfig daoConfig, DaoSession daoSession2) {
        super(daoConfig, daoSession2);
        this.daoSession = daoSession2;
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBFEATURED_CONTENT_AD' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'NAME' TEXT,'DESCRIPTION' TEXT,'FOOTER' TEXT,'IMAGE_SOURCE' TEXT,'BACKGROUND_COLOR' TEXT,'DISPLAY_FREQUENCY' REAL,'CONTENT_ITEM_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBFEATURED_CONTENT_AD'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBFeaturedContentAd dBFeaturedContentAd) {
        sQLiteStatement.clearBindings();
        Long id = dBFeaturedContentAd.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBFeaturedContentAd.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String name = dBFeaturedContentAd.getName();
        if (name != null) {
            sQLiteStatement.bindString(3, name);
        }
        String description = dBFeaturedContentAd.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(4, description);
        }
        String footer = dBFeaturedContentAd.getFooter();
        if (footer != null) {
            sQLiteStatement.bindString(5, footer);
        }
        String imageSource = dBFeaturedContentAd.getImageSource();
        if (imageSource != null) {
            sQLiteStatement.bindString(6, imageSource);
        }
        String backgroundColor = dBFeaturedContentAd.getBackgroundColor();
        if (backgroundColor != null) {
            sQLiteStatement.bindString(7, backgroundColor);
        }
        Double displayFrequency = dBFeaturedContentAd.getDisplayFrequency();
        if (displayFrequency != null) {
            sQLiteStatement.bindDouble(8, displayFrequency.doubleValue());
        }
        Long contentItemId = dBFeaturedContentAd.getContentItemId();
        if (contentItemId != null) {
            sQLiteStatement.bindLong(9, contentItemId.longValue());
        }
    }

    /* access modifiers changed from: protected */
    public void attachEntity(DBFeaturedContentAd dBFeaturedContentAd) {
        super.attachEntity(dBFeaturedContentAd);
        dBFeaturedContentAd.__setDaoSession(this.daoSession);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBFeaturedContentAd readEntity(Cursor cursor, int i) {
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
        String string6 = cursor.isNull(i8) ? null : cursor.getString(i8);
        int i9 = i + 7;
        int i10 = i + 8;
        return new DBFeaturedContentAd(valueOf, string, string2, string3, string4, string5, string6, cursor.isNull(i9) ? null : Double.valueOf(cursor.getDouble(i9)), cursor.isNull(i10) ? null : Long.valueOf(cursor.getLong(i10)));
    }

    public void readEntity(Cursor cursor, DBFeaturedContentAd dBFeaturedContentAd, int i) {
        int i2 = i + 0;
        Long l = null;
        dBFeaturedContentAd.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBFeaturedContentAd.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBFeaturedContentAd.setName(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBFeaturedContentAd.setDescription(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBFeaturedContentAd.setFooter(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        dBFeaturedContentAd.setImageSource(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 6;
        dBFeaturedContentAd.setBackgroundColor(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 7;
        dBFeaturedContentAd.setDisplayFrequency(cursor.isNull(i9) ? null : Double.valueOf(cursor.getDouble(i9)));
        int i10 = i + 8;
        if (!cursor.isNull(i10)) {
            l = Long.valueOf(cursor.getLong(i10));
        }
        dBFeaturedContentAd.setContentItemId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBFeaturedContentAd dBFeaturedContentAd, long j) {
        dBFeaturedContentAd.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBFeaturedContentAd dBFeaturedContentAd) {
        if (dBFeaturedContentAd != null) {
            return dBFeaturedContentAd.getId();
        }
        return null;
    }

    public List<DBFeaturedContentAd> _queryDBContentItem_FeaturedContentAds(Long l) {
        synchronized (this) {
            if (this.dBContentItem_FeaturedContentAdsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ContentItemId.eq((Object) null), new WhereCondition[0]);
                this.dBContentItem_FeaturedContentAdsQuery = queryBuilder.build();
            }
        }
        Query<DBFeaturedContentAd> forCurrentThread = this.dBContentItem_FeaturedContentAdsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
