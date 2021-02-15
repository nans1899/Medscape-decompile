package com.wbmd.qxcalculator.model.db.v9;

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

public class DBReferenceBookSectionDao extends AbstractDao<DBReferenceBookSection, Long> {
    public static final String TABLENAME = "DBREFERENCE_BOOK_SECTION";
    private Query<DBReferenceBookSection> dBReferenceBook_ReferenceBookSectionsQuery;
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Footer = new Property(4, String.class, "footer", false, "FOOTER");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property Position = new Property(5, Long.class, "position", false, "POSITION");
        public static final Property ReferenceBookId = new Property(6, Long.class, "referenceBookId", false, "REFERENCE_BOOK_ID");
        public static final Property SubTitle = new Property(3, String.class, "subTitle", false, "SUB_TITLE");
        public static final Property Title = new Property(2, String.class, "title", false, "TITLE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBReferenceBookSectionDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBReferenceBookSectionDao(DaoConfig daoConfig, DaoSession daoSession2) {
        super(daoConfig, daoSession2);
        this.daoSession = daoSession2;
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBREFERENCE_BOOK_SECTION' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'TITLE' TEXT,'SUB_TITLE' TEXT,'FOOTER' TEXT,'POSITION' INTEGER,'REFERENCE_BOOK_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBREFERENCE_BOOK_SECTION'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBReferenceBookSection dBReferenceBookSection) {
        sQLiteStatement.clearBindings();
        Long id = dBReferenceBookSection.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBReferenceBookSection.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String title = dBReferenceBookSection.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(3, title);
        }
        String subTitle = dBReferenceBookSection.getSubTitle();
        if (subTitle != null) {
            sQLiteStatement.bindString(4, subTitle);
        }
        String footer = dBReferenceBookSection.getFooter();
        if (footer != null) {
            sQLiteStatement.bindString(5, footer);
        }
        Long position = dBReferenceBookSection.getPosition();
        if (position != null) {
            sQLiteStatement.bindLong(6, position.longValue());
        }
        Long referenceBookId = dBReferenceBookSection.getReferenceBookId();
        if (referenceBookId != null) {
            sQLiteStatement.bindLong(7, referenceBookId.longValue());
        }
    }

    /* access modifiers changed from: protected */
    public void attachEntity(DBReferenceBookSection dBReferenceBookSection) {
        super.attachEntity(dBReferenceBookSection);
        dBReferenceBookSection.__setDaoSession(this.daoSession);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBReferenceBookSection readEntity(Cursor cursor, int i) {
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
        int i8 = i + 6;
        return new DBReferenceBookSection(valueOf, string, string2, string3, string4, cursor.isNull(i7) ? null : Long.valueOf(cursor.getLong(i7)), cursor.isNull(i8) ? null : Long.valueOf(cursor.getLong(i8)));
    }

    public void readEntity(Cursor cursor, DBReferenceBookSection dBReferenceBookSection, int i) {
        int i2 = i + 0;
        Long l = null;
        dBReferenceBookSection.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBReferenceBookSection.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBReferenceBookSection.setTitle(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBReferenceBookSection.setSubTitle(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBReferenceBookSection.setFooter(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        dBReferenceBookSection.setPosition(cursor.isNull(i7) ? null : Long.valueOf(cursor.getLong(i7)));
        int i8 = i + 6;
        if (!cursor.isNull(i8)) {
            l = Long.valueOf(cursor.getLong(i8));
        }
        dBReferenceBookSection.setReferenceBookId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBReferenceBookSection dBReferenceBookSection, long j) {
        dBReferenceBookSection.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBReferenceBookSection dBReferenceBookSection) {
        if (dBReferenceBookSection != null) {
            return dBReferenceBookSection.getId();
        }
        return null;
    }

    public List<DBReferenceBookSection> _queryDBReferenceBook_ReferenceBookSections(Long l) {
        synchronized (this) {
            if (this.dBReferenceBook_ReferenceBookSectionsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ReferenceBookId.eq((Object) null), new WhereCondition[0]);
                this.dBReferenceBook_ReferenceBookSectionsQuery = queryBuilder.build();
            }
        }
        Query<DBReferenceBookSection> forCurrentThread = this.dBReferenceBook_ReferenceBookSectionsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
