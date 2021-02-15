package com.wbmd.qxcalculator.model.db.v9;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.dd.plist.ASCIIPropertyListParser;
import com.facebook.share.internal.ShareConstants;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.List;

public class DBFeaturedContentAdDao extends AbstractDao<DBFeaturedContentAd, Long> {
    public static final String TABLENAME = "DBFEATURED_CONTENT_AD";
    private Query<DBFeaturedContentAd> dBContentItem_FeaturedContentAdsQuery;
    private DaoSession daoSession;
    private String selectDeep;

    public static class Properties {
        public static final Property BackgroundColor = new Property(7, String.class, "backgroundColor", false, "BACKGROUND_COLOR");
        public static final Property ContentItemId = new Property(9, Long.class, "contentItemId", false, "CONTENT_ITEM_ID");
        public static final Property Description = new Property(3, String.class, "description", false, ShareConstants.DESCRIPTION);
        public static final Property Disclaimer = new Property(4, String.class, "disclaimer", false, "DISCLAIMER");
        public static final Property DisplayFrequency = new Property(8, Double.class, "displayFrequency", false, "DISPLAY_FREQUENCY");
        public static final Property Footer = new Property(5, String.class, "footer", false, "FOOTER");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property ImageSource = new Property(6, String.class, "imageSource", false, "IMAGE_SOURCE");
        public static final Property Name = new Property(2, String.class, "name", false, "NAME");
        public static final Property PromotionId = new Property(10, Long.class, "promotionId", false, "PROMOTION_ID");
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
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBFEATURED_CONTENT_AD' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'NAME' TEXT,'DESCRIPTION' TEXT,'DISCLAIMER' TEXT,'FOOTER' TEXT,'IMAGE_SOURCE' TEXT,'BACKGROUND_COLOR' TEXT,'DISPLAY_FREQUENCY' REAL,'CONTENT_ITEM_ID' INTEGER,'PROMOTION_ID' INTEGER);");
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
        String disclaimer = dBFeaturedContentAd.getDisclaimer();
        if (disclaimer != null) {
            sQLiteStatement.bindString(5, disclaimer);
        }
        String footer = dBFeaturedContentAd.getFooter();
        if (footer != null) {
            sQLiteStatement.bindString(6, footer);
        }
        String imageSource = dBFeaturedContentAd.getImageSource();
        if (imageSource != null) {
            sQLiteStatement.bindString(7, imageSource);
        }
        String backgroundColor = dBFeaturedContentAd.getBackgroundColor();
        if (backgroundColor != null) {
            sQLiteStatement.bindString(8, backgroundColor);
        }
        Double displayFrequency = dBFeaturedContentAd.getDisplayFrequency();
        if (displayFrequency != null) {
            sQLiteStatement.bindDouble(9, displayFrequency.doubleValue());
        }
        Long contentItemId = dBFeaturedContentAd.getContentItemId();
        if (contentItemId != null) {
            sQLiteStatement.bindLong(10, contentItemId.longValue());
        }
        Long promotionId = dBFeaturedContentAd.getPromotionId();
        if (promotionId != null) {
            sQLiteStatement.bindLong(11, promotionId.longValue());
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
        Cursor cursor2 = cursor;
        int i2 = i + 0;
        int i3 = i + 1;
        int i4 = i + 2;
        int i5 = i + 3;
        int i6 = i + 4;
        int i7 = i + 5;
        int i8 = i + 6;
        int i9 = i + 7;
        int i10 = i + 8;
        int i11 = i + 9;
        int i12 = i + 10;
        return new DBFeaturedContentAd(cursor2.isNull(i2) ? null : Long.valueOf(cursor2.getLong(i2)), cursor2.isNull(i3) ? null : cursor2.getString(i3), cursor2.isNull(i4) ? null : cursor2.getString(i4), cursor2.isNull(i5) ? null : cursor2.getString(i5), cursor2.isNull(i6) ? null : cursor2.getString(i6), cursor2.isNull(i7) ? null : cursor2.getString(i7), cursor2.isNull(i8) ? null : cursor2.getString(i8), cursor2.isNull(i9) ? null : cursor2.getString(i9), cursor2.isNull(i10) ? null : Double.valueOf(cursor2.getDouble(i10)), cursor2.isNull(i11) ? null : Long.valueOf(cursor2.getLong(i11)), cursor2.isNull(i12) ? null : Long.valueOf(cursor2.getLong(i12)));
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
        dBFeaturedContentAd.setDisclaimer(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        dBFeaturedContentAd.setFooter(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 6;
        dBFeaturedContentAd.setImageSource(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 7;
        dBFeaturedContentAd.setBackgroundColor(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 8;
        dBFeaturedContentAd.setDisplayFrequency(cursor.isNull(i10) ? null : Double.valueOf(cursor.getDouble(i10)));
        int i11 = i + 9;
        dBFeaturedContentAd.setContentItemId(cursor.isNull(i11) ? null : Long.valueOf(cursor.getLong(i11)));
        int i12 = i + 10;
        if (!cursor.isNull(i12)) {
            l = Long.valueOf(cursor.getLong(i12));
        }
        dBFeaturedContentAd.setPromotionId(l);
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

    /* access modifiers changed from: protected */
    public String getSelectDeep() {
        if (this.selectDeep == null) {
            StringBuilder sb = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(sb, "T", getAllColumns());
            sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            SqlUtils.appendColumns(sb, "T0", this.daoSession.getDBPromotionDao().getAllColumns());
            sb.append(" FROM DBFEATURED_CONTENT_AD T");
            sb.append(" LEFT JOIN DBPROMOTION T0 ON T.'PROMOTION_ID'=T0.'_id'");
            sb.append(' ');
            this.selectDeep = sb.toString();
        }
        return this.selectDeep;
    }

    /* access modifiers changed from: protected */
    public DBFeaturedContentAd loadCurrentDeep(Cursor cursor, boolean z) {
        DBFeaturedContentAd dBFeaturedContentAd = (DBFeaturedContentAd) loadCurrent(cursor, 0, z);
        dBFeaturedContentAd.setPromotion((DBPromotion) loadCurrentOther(this.daoSession.getDBPromotionDao(), cursor, getAllColumns().length));
        return dBFeaturedContentAd;
    }

    public DBFeaturedContentAd loadDeep(Long l) {
        assertSinglePk();
        if (l == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(getSelectDeep());
        sb.append("WHERE ");
        SqlUtils.appendColumnsEqValue(sb, "T", getPkColumns());
        Cursor rawQuery = this.db.rawQuery(sb.toString(), new String[]{l.toString()});
        try {
            if (!rawQuery.moveToFirst()) {
                return null;
            }
            if (rawQuery.isLast()) {
                DBFeaturedContentAd loadCurrentDeep = loadCurrentDeep(rawQuery, true);
                rawQuery.close();
                return loadCurrentDeep;
            }
            throw new IllegalStateException("Expected unique result, but count was " + rawQuery.getCount());
        } finally {
            rawQuery.close();
        }
    }

    public List<DBFeaturedContentAd> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        ArrayList arrayList = new ArrayList(count);
        if (cursor.moveToFirst()) {
            if (this.identityScope != null) {
                this.identityScope.lock();
                this.identityScope.reserveRoom(count);
            }
            do {
                try {
                    arrayList.add(loadCurrentDeep(cursor, false));
                } finally {
                    if (this.identityScope != null) {
                        this.identityScope.unlock();
                    }
                }
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public List<DBFeaturedContentAd> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }

    public List<DBFeaturedContentAd> queryDeep(String str, String... strArr) {
        SQLiteDatabase sQLiteDatabase = this.db;
        return loadDeepAllAndCloseCursor(sQLiteDatabase.rawQuery(getSelectDeep() + str, strArr));
    }
}
