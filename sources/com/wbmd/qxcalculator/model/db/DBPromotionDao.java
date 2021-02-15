package com.wbmd.qxcalculator.model.db;

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

public class DBPromotionDao extends AbstractDao<DBPromotion, Long> {
    public static final String TABLENAME = "DBPROMOTION";
    private Query<DBPromotion> dBContentItem_PromotionsQuery;
    private DaoSession daoSession;

    public static class Properties {
        public static final Property CampaignAdId = new Property(4, Long.class, "campaignAdId", false, "CAMPAIGN_AD_ID");
        public static final Property ContentItemId = new Property(5, Long.class, "contentItemId", false, "CONTENT_ITEM_ID");
        public static final Property Footer = new Property(3, String.class, "footer", false, "FOOTER");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property Title = new Property(2, String.class, "title", false, "TITLE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBPromotionDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBPromotionDao(DaoConfig daoConfig, DaoSession daoSession2) {
        super(daoConfig, daoSession2);
        this.daoSession = daoSession2;
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBPROMOTION' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'TITLE' TEXT,'FOOTER' TEXT,'CAMPAIGN_AD_ID' INTEGER,'CONTENT_ITEM_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBPROMOTION'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBPromotion dBPromotion) {
        sQLiteStatement.clearBindings();
        Long id = dBPromotion.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBPromotion.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String title = dBPromotion.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(3, title);
        }
        String footer = dBPromotion.getFooter();
        if (footer != null) {
            sQLiteStatement.bindString(4, footer);
        }
        Long campaignAdId = dBPromotion.getCampaignAdId();
        if (campaignAdId != null) {
            sQLiteStatement.bindLong(5, campaignAdId.longValue());
        }
        Long contentItemId = dBPromotion.getContentItemId();
        if (contentItemId != null) {
            sQLiteStatement.bindLong(6, contentItemId.longValue());
        }
    }

    /* access modifiers changed from: protected */
    public void attachEntity(DBPromotion dBPromotion) {
        super.attachEntity(dBPromotion);
        dBPromotion.__setDaoSession(this.daoSession);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBPromotion readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        String string3 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        int i7 = i + 5;
        return new DBPromotion(valueOf, string, string2, string3, cursor.isNull(i6) ? null : Long.valueOf(cursor.getLong(i6)), cursor.isNull(i7) ? null : Long.valueOf(cursor.getLong(i7)));
    }

    public void readEntity(Cursor cursor, DBPromotion dBPromotion, int i) {
        int i2 = i + 0;
        Long l = null;
        dBPromotion.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBPromotion.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBPromotion.setTitle(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBPromotion.setFooter(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBPromotion.setCampaignAdId(cursor.isNull(i6) ? null : Long.valueOf(cursor.getLong(i6)));
        int i7 = i + 5;
        if (!cursor.isNull(i7)) {
            l = Long.valueOf(cursor.getLong(i7));
        }
        dBPromotion.setContentItemId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBPromotion dBPromotion, long j) {
        dBPromotion.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBPromotion dBPromotion) {
        if (dBPromotion != null) {
            return dBPromotion.getId();
        }
        return null;
    }

    public List<DBPromotion> _queryDBContentItem_Promotions(Long l) {
        synchronized (this) {
            if (this.dBContentItem_PromotionsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ContentItemId.eq((Object) null), new WhereCondition[0]);
                this.dBContentItem_PromotionsQuery = queryBuilder.build();
            }
        }
        Query<DBPromotion> forCurrentThread = this.dBContentItem_PromotionsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
