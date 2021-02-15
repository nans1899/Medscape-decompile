package com.wbmd.qxcalculator.model.db.v6;

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

public class DBMoreInfoSectionDao extends AbstractDao<DBMoreInfoSection, Long> {
    public static final String TABLENAME = "DBMORE_INFO_SECTION";
    private Query<DBMoreInfoSection> dBContentItem_MoreInfoSectionsQuery;
    private DaoSession daoSession;

    public static class Properties {
        public static final Property ContentItemId = new Property(5, Long.class, "contentItemId", false, "CONTENT_ITEM_ID");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property Position = new Property(4, Integer.class, "position", false, "POSITION");
        public static final Property SubTitle = new Property(3, String.class, "subTitle", false, "SUB_TITLE");
        public static final Property Title = new Property(2, String.class, "title", false, "TITLE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBMoreInfoSectionDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBMoreInfoSectionDao(DaoConfig daoConfig, DaoSession daoSession2) {
        super(daoConfig, daoSession2);
        this.daoSession = daoSession2;
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBMORE_INFO_SECTION' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'TITLE' TEXT,'SUB_TITLE' TEXT,'POSITION' INTEGER,'CONTENT_ITEM_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBMORE_INFO_SECTION'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBMoreInfoSection dBMoreInfoSection) {
        sQLiteStatement.clearBindings();
        Long id = dBMoreInfoSection.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBMoreInfoSection.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String title = dBMoreInfoSection.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(3, title);
        }
        String subTitle = dBMoreInfoSection.getSubTitle();
        if (subTitle != null) {
            sQLiteStatement.bindString(4, subTitle);
        }
        Integer position = dBMoreInfoSection.getPosition();
        if (position != null) {
            sQLiteStatement.bindLong(5, (long) position.intValue());
        }
        Long contentItemId = dBMoreInfoSection.getContentItemId();
        if (contentItemId != null) {
            sQLiteStatement.bindLong(6, contentItemId.longValue());
        }
    }

    /* access modifiers changed from: protected */
    public void attachEntity(DBMoreInfoSection dBMoreInfoSection) {
        super.attachEntity(dBMoreInfoSection);
        dBMoreInfoSection.__setDaoSession(this.daoSession);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBMoreInfoSection readEntity(Cursor cursor, int i) {
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
        return new DBMoreInfoSection(valueOf, string, string2, string3, cursor.isNull(i6) ? null : Integer.valueOf(cursor.getInt(i6)), cursor.isNull(i7) ? null : Long.valueOf(cursor.getLong(i7)));
    }

    public void readEntity(Cursor cursor, DBMoreInfoSection dBMoreInfoSection, int i) {
        int i2 = i + 0;
        Long l = null;
        dBMoreInfoSection.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBMoreInfoSection.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBMoreInfoSection.setTitle(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBMoreInfoSection.setSubTitle(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBMoreInfoSection.setPosition(cursor.isNull(i6) ? null : Integer.valueOf(cursor.getInt(i6)));
        int i7 = i + 5;
        if (!cursor.isNull(i7)) {
            l = Long.valueOf(cursor.getLong(i7));
        }
        dBMoreInfoSection.setContentItemId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBMoreInfoSection dBMoreInfoSection, long j) {
        dBMoreInfoSection.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBMoreInfoSection dBMoreInfoSection) {
        if (dBMoreInfoSection != null) {
            return dBMoreInfoSection.getId();
        }
        return null;
    }

    public List<DBMoreInfoSection> _queryDBContentItem_MoreInfoSections(Long l) {
        synchronized (this) {
            if (this.dBContentItem_MoreInfoSectionsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ContentItemId.eq((Object) null), new WhereCondition[0]);
                this.dBContentItem_MoreInfoSectionsQuery = queryBuilder.build();
            }
        }
        Query<DBMoreInfoSection> forCurrentThread = this.dBContentItem_MoreInfoSectionsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
