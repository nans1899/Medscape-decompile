package com.wbmd.qxcalculator.model.db.v7;

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

public class DBRestrictionDao extends AbstractDao<DBRestriction, Long> {
    public static final String TABLENAME = "DBRESTRICTION";
    private Query<DBRestriction> dBContentItem_RestrictionsQuery;
    private DaoSession daoSession;

    public static class Properties {
        public static final Property AlertMessage = new Property(3, String.class, "alertMessage", false, "ALERT_MESSAGE");
        public static final Property AlertTitle = new Property(2, String.class, "alertTitle", false, "ALERT_TITLE");
        public static final Property AlternateUrl = new Property(4, String.class, "alternateUrl", false, "ALTERNATE_URL");
        public static final Property ContentItemId = new Property(5, Long.class, "contentItemId", false, "CONTENT_ITEM_ID");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBRestrictionDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBRestrictionDao(DaoConfig daoConfig, DaoSession daoSession2) {
        super(daoConfig, daoSession2);
        this.daoSession = daoSession2;
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBRESTRICTION' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'ALERT_TITLE' TEXT,'ALERT_MESSAGE' TEXT,'ALTERNATE_URL' TEXT,'CONTENT_ITEM_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBRESTRICTION'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBRestriction dBRestriction) {
        sQLiteStatement.clearBindings();
        Long id = dBRestriction.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBRestriction.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String alertTitle = dBRestriction.getAlertTitle();
        if (alertTitle != null) {
            sQLiteStatement.bindString(3, alertTitle);
        }
        String alertMessage = dBRestriction.getAlertMessage();
        if (alertMessage != null) {
            sQLiteStatement.bindString(4, alertMessage);
        }
        String alternateUrl = dBRestriction.getAlternateUrl();
        if (alternateUrl != null) {
            sQLiteStatement.bindString(5, alternateUrl);
        }
        Long contentItemId = dBRestriction.getContentItemId();
        if (contentItemId != null) {
            sQLiteStatement.bindLong(6, contentItemId.longValue());
        }
    }

    /* access modifiers changed from: protected */
    public void attachEntity(DBRestriction dBRestriction) {
        super.attachEntity(dBRestriction);
        dBRestriction.__setDaoSession(this.daoSession);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBRestriction readEntity(Cursor cursor, int i) {
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
        return new DBRestriction(valueOf, string, string2, string3, cursor.isNull(i6) ? null : cursor.getString(i6), cursor.isNull(i7) ? null : Long.valueOf(cursor.getLong(i7)));
    }

    public void readEntity(Cursor cursor, DBRestriction dBRestriction, int i) {
        int i2 = i + 0;
        Long l = null;
        dBRestriction.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBRestriction.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBRestriction.setAlertTitle(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBRestriction.setAlertMessage(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBRestriction.setAlternateUrl(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        if (!cursor.isNull(i7)) {
            l = Long.valueOf(cursor.getLong(i7));
        }
        dBRestriction.setContentItemId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBRestriction dBRestriction, long j) {
        dBRestriction.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBRestriction dBRestriction) {
        if (dBRestriction != null) {
            return dBRestriction.getId();
        }
        return null;
    }

    public List<DBRestriction> _queryDBContentItem_Restrictions(Long l) {
        synchronized (this) {
            if (this.dBContentItem_RestrictionsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ContentItemId.eq((Object) null), new WhereCondition[0]);
                this.dBContentItem_RestrictionsQuery = queryBuilder.build();
            }
        }
        Query<DBRestriction> forCurrentThread = this.dBContentItem_RestrictionsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
