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

public class DBTagDao extends AbstractDao<DBTag, Long> {
    public static final String TABLENAME = "DBTAG";
    private Query<DBTag> dBContentItem_TagsQuery;

    public static class Properties {
        public static final Property ContentItemId = new Property(3, Long.class, "contentItemId", false, "CONTENT_ITEM_ID");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property Name = new Property(2, String.class, "name", false, "NAME");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBTagDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBTagDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBTAG' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'NAME' TEXT,'CONTENT_ITEM_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBTAG'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBTag dBTag) {
        sQLiteStatement.clearBindings();
        Long id = dBTag.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBTag.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String name = dBTag.getName();
        if (name != null) {
            sQLiteStatement.bindString(3, name);
        }
        Long contentItemId = dBTag.getContentItemId();
        if (contentItemId != null) {
            sQLiteStatement.bindLong(4, contentItemId.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBTag readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long l = null;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            l = Long.valueOf(cursor.getLong(i5));
        }
        return new DBTag(valueOf, string, string2, l);
    }

    public void readEntity(Cursor cursor, DBTag dBTag, int i) {
        int i2 = i + 0;
        Long l = null;
        dBTag.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBTag.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBTag.setName(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            l = Long.valueOf(cursor.getLong(i5));
        }
        dBTag.setContentItemId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBTag dBTag, long j) {
        dBTag.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBTag dBTag) {
        if (dBTag != null) {
            return dBTag.getId();
        }
        return null;
    }

    public List<DBTag> _queryDBContentItem_Tags(Long l) {
        synchronized (this) {
            if (this.dBContentItem_TagsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ContentItemId.eq((Object) null), new WhereCondition[0]);
                this.dBContentItem_TagsQuery = queryBuilder.build();
            }
        }
        Query<DBTag> forCurrentThread = this.dBContentItem_TagsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
