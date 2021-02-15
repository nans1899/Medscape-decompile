package com.wbmd.qxcalculator.model.db.v4;

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

public class DBSpecialtyDao extends AbstractDao<DBSpecialty, Long> {
    public static final String TABLENAME = "DBSPECIALTY";
    private Query<DBSpecialty> dBFilter_SpecialtiesQuery;

    public static class Properties {
        public static final Property CategoryIdentifier = new Property(3, String.class, "categoryIdentifier", false, "CATEGORY_IDENTIFIER");
        public static final Property ContributingAuthor = new Property(4, String.class, "contributingAuthor", false, "CONTRIBUTING_AUTHOR");
        public static final Property FilterId = new Property(5, Long.class, "filterId", false, "FILTER_ID");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, Long.class, "identifier", false, "IDENTIFIER");
        public static final Property Name = new Property(2, String.class, "name", false, "NAME");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBSpecialtyDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBSpecialtyDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBSPECIALTY' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' INTEGER,'NAME' TEXT,'CATEGORY_IDENTIFIER' TEXT,'CONTRIBUTING_AUTHOR' TEXT,'FILTER_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBSPECIALTY'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBSpecialty dBSpecialty) {
        sQLiteStatement.clearBindings();
        Long id = dBSpecialty.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        Long identifier = dBSpecialty.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindLong(2, identifier.longValue());
        }
        String name = dBSpecialty.getName();
        if (name != null) {
            sQLiteStatement.bindString(3, name);
        }
        String categoryIdentifier = dBSpecialty.getCategoryIdentifier();
        if (categoryIdentifier != null) {
            sQLiteStatement.bindString(4, categoryIdentifier);
        }
        String contributingAuthor = dBSpecialty.getContributingAuthor();
        if (contributingAuthor != null) {
            sQLiteStatement.bindString(5, contributingAuthor);
        }
        Long filterId = dBSpecialty.getFilterId();
        if (filterId != null) {
            sQLiteStatement.bindLong(6, filterId.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBSpecialty readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        Long valueOf2 = cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3));
        int i4 = i + 2;
        String string = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        String string2 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        int i7 = i + 5;
        return new DBSpecialty(valueOf, valueOf2, string, string2, cursor.isNull(i6) ? null : cursor.getString(i6), cursor.isNull(i7) ? null : Long.valueOf(cursor.getLong(i7)));
    }

    public void readEntity(Cursor cursor, DBSpecialty dBSpecialty, int i) {
        int i2 = i + 0;
        Long l = null;
        dBSpecialty.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBSpecialty.setIdentifier(cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3)));
        int i4 = i + 2;
        dBSpecialty.setName(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBSpecialty.setCategoryIdentifier(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBSpecialty.setContributingAuthor(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        if (!cursor.isNull(i7)) {
            l = Long.valueOf(cursor.getLong(i7));
        }
        dBSpecialty.setFilterId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBSpecialty dBSpecialty, long j) {
        dBSpecialty.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBSpecialty dBSpecialty) {
        if (dBSpecialty != null) {
            return dBSpecialty.getId();
        }
        return null;
    }

    public List<DBSpecialty> _queryDBFilter_Specialties(Long l) {
        synchronized (this) {
            if (this.dBFilter_SpecialtiesQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.FilterId.eq((Object) null), new WhereCondition[0]);
                this.dBFilter_SpecialtiesQuery = queryBuilder.build();
            }
        }
        Query<DBSpecialty> forCurrentThread = this.dBFilter_SpecialtiesQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
