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

public class DBReferenceBookSectionItemDao extends AbstractDao<DBReferenceBookSectionItem, Long> {
    public static final String TABLENAME = "DBREFERENCE_BOOK_SECTION_ITEM";
    private Query<DBReferenceBookSectionItem> dBReferenceBookSectionItem_ReferenceBookSubSectionItemsQuery;
    private Query<DBReferenceBookSectionItem> dBReferenceBookSection_ReferenceBookSectionItemsQuery;
    private DaoSession daoSession;

    public static class Properties {
        public static final Property AccentColor = new Property(5, String.class, "accentColor", false, "ACCENT_COLOR");
        public static final Property Color = new Property(4, String.class, "color", false, "COLOR");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property Position = new Property(8, Long.class, "position", false, "POSITION");
        public static final Property ReferenceBookSectionId = new Property(10, Long.class, "referenceBookSectionId", false, "REFERENCE_BOOK_SECTION_ID");
        public static final Property ReferenceBookSectionItemId = new Property(11, Long.class, "referenceBookSectionItemId", false, "REFERENCE_BOOK_SECTION_ITEM_ID");
        public static final Property ShareLink = new Property(9, String.class, "shareLink", false, "SHARE_LINK");
        public static final Property Source = new Property(6, String.class, "source", false, "SOURCE");
        public static final Property SourceId = new Property(7, String.class, "sourceId", false, "SOURCE_ID");
        public static final Property SubTitle = new Property(3, String.class, "subTitle", false, "SUB_TITLE");
        public static final Property Title = new Property(2, String.class, "title", false, "TITLE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBReferenceBookSectionItemDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBReferenceBookSectionItemDao(DaoConfig daoConfig, DaoSession daoSession2) {
        super(daoConfig, daoSession2);
        this.daoSession = daoSession2;
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBREFERENCE_BOOK_SECTION_ITEM' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'TITLE' TEXT,'SUB_TITLE' TEXT,'COLOR' TEXT,'ACCENT_COLOR' TEXT,'SOURCE' TEXT,'SOURCE_ID' TEXT,'POSITION' INTEGER,'SHARE_LINK' TEXT,'REFERENCE_BOOK_SECTION_ID' INTEGER,'REFERENCE_BOOK_SECTION_ITEM_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBREFERENCE_BOOK_SECTION_ITEM'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBReferenceBookSectionItem dBReferenceBookSectionItem) {
        sQLiteStatement.clearBindings();
        Long id = dBReferenceBookSectionItem.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBReferenceBookSectionItem.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String title = dBReferenceBookSectionItem.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(3, title);
        }
        String subTitle = dBReferenceBookSectionItem.getSubTitle();
        if (subTitle != null) {
            sQLiteStatement.bindString(4, subTitle);
        }
        String color = dBReferenceBookSectionItem.getColor();
        if (color != null) {
            sQLiteStatement.bindString(5, color);
        }
        String accentColor = dBReferenceBookSectionItem.getAccentColor();
        if (accentColor != null) {
            sQLiteStatement.bindString(6, accentColor);
        }
        String source = dBReferenceBookSectionItem.getSource();
        if (source != null) {
            sQLiteStatement.bindString(7, source);
        }
        String sourceId = dBReferenceBookSectionItem.getSourceId();
        if (sourceId != null) {
            sQLiteStatement.bindString(8, sourceId);
        }
        Long position = dBReferenceBookSectionItem.getPosition();
        if (position != null) {
            sQLiteStatement.bindLong(9, position.longValue());
        }
        String shareLink = dBReferenceBookSectionItem.getShareLink();
        if (shareLink != null) {
            sQLiteStatement.bindString(10, shareLink);
        }
        Long referenceBookSectionId = dBReferenceBookSectionItem.getReferenceBookSectionId();
        if (referenceBookSectionId != null) {
            sQLiteStatement.bindLong(11, referenceBookSectionId.longValue());
        }
        Long referenceBookSectionItemId = dBReferenceBookSectionItem.getReferenceBookSectionItemId();
        if (referenceBookSectionItemId != null) {
            sQLiteStatement.bindLong(12, referenceBookSectionItemId.longValue());
        }
    }

    /* access modifiers changed from: protected */
    public void attachEntity(DBReferenceBookSectionItem dBReferenceBookSectionItem) {
        super.attachEntity(dBReferenceBookSectionItem);
        dBReferenceBookSectionItem.__setDaoSession(this.daoSession);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBReferenceBookSectionItem readEntity(Cursor cursor, int i) {
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
        int i13 = i + 11;
        return new DBReferenceBookSectionItem(cursor2.isNull(i2) ? null : Long.valueOf(cursor2.getLong(i2)), cursor2.isNull(i3) ? null : cursor2.getString(i3), cursor2.isNull(i4) ? null : cursor2.getString(i4), cursor2.isNull(i5) ? null : cursor2.getString(i5), cursor2.isNull(i6) ? null : cursor2.getString(i6), cursor2.isNull(i7) ? null : cursor2.getString(i7), cursor2.isNull(i8) ? null : cursor2.getString(i8), cursor2.isNull(i9) ? null : cursor2.getString(i9), cursor2.isNull(i10) ? null : Long.valueOf(cursor2.getLong(i10)), cursor2.isNull(i11) ? null : cursor2.getString(i11), cursor2.isNull(i12) ? null : Long.valueOf(cursor2.getLong(i12)), cursor2.isNull(i13) ? null : Long.valueOf(cursor2.getLong(i13)));
    }

    public void readEntity(Cursor cursor, DBReferenceBookSectionItem dBReferenceBookSectionItem, int i) {
        int i2 = i + 0;
        Long l = null;
        dBReferenceBookSectionItem.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBReferenceBookSectionItem.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBReferenceBookSectionItem.setTitle(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBReferenceBookSectionItem.setSubTitle(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBReferenceBookSectionItem.setColor(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        dBReferenceBookSectionItem.setAccentColor(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 6;
        dBReferenceBookSectionItem.setSource(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 7;
        dBReferenceBookSectionItem.setSourceId(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 8;
        dBReferenceBookSectionItem.setPosition(cursor.isNull(i10) ? null : Long.valueOf(cursor.getLong(i10)));
        int i11 = i + 9;
        dBReferenceBookSectionItem.setShareLink(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 10;
        dBReferenceBookSectionItem.setReferenceBookSectionId(cursor.isNull(i12) ? null : Long.valueOf(cursor.getLong(i12)));
        int i13 = i + 11;
        if (!cursor.isNull(i13)) {
            l = Long.valueOf(cursor.getLong(i13));
        }
        dBReferenceBookSectionItem.setReferenceBookSectionItemId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBReferenceBookSectionItem dBReferenceBookSectionItem, long j) {
        dBReferenceBookSectionItem.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBReferenceBookSectionItem dBReferenceBookSectionItem) {
        if (dBReferenceBookSectionItem != null) {
            return dBReferenceBookSectionItem.getId();
        }
        return null;
    }

    public List<DBReferenceBookSectionItem> _queryDBReferenceBookSection_ReferenceBookSectionItems(Long l) {
        synchronized (this) {
            if (this.dBReferenceBookSection_ReferenceBookSectionItemsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ReferenceBookSectionId.eq((Object) null), new WhereCondition[0]);
                this.dBReferenceBookSection_ReferenceBookSectionItemsQuery = queryBuilder.build();
            }
        }
        Query<DBReferenceBookSectionItem> forCurrentThread = this.dBReferenceBookSection_ReferenceBookSectionItemsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }

    public List<DBReferenceBookSectionItem> _queryDBReferenceBookSectionItem_ReferenceBookSubSectionItems(Long l) {
        synchronized (this) {
            if (this.dBReferenceBookSectionItem_ReferenceBookSubSectionItemsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ReferenceBookSectionItemId.eq((Object) null), new WhereCondition[0]);
                this.dBReferenceBookSectionItem_ReferenceBookSubSectionItemsQuery = queryBuilder.build();
            }
        }
        Query<DBReferenceBookSectionItem> forCurrentThread = this.dBReferenceBookSectionItem_ReferenceBookSubSectionItemsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
