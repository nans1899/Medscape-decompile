package com.wbmd.qxcalculator.model.db.v5;

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

public class DBProfessionDao extends AbstractDao<DBProfession, Long> {
    public static final String TABLENAME = "DBPROFESSION";
    private Query<DBProfession> dBFilter_ProfessionsQuery;

    public static class Properties {
        public static final Property FilterId = new Property(3, Long.class, "filterId", false, "FILTER_ID");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, Long.class, "identifier", false, "IDENTIFIER");
        public static final Property Name = new Property(2, String.class, "name", false, "NAME");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBProfessionDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBProfessionDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBPROFESSION' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' INTEGER,'NAME' TEXT,'FILTER_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBPROFESSION'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBProfession dBProfession) {
        sQLiteStatement.clearBindings();
        Long id = dBProfession.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        Long identifier = dBProfession.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindLong(2, identifier.longValue());
        }
        String name = dBProfession.getName();
        if (name != null) {
            sQLiteStatement.bindString(3, name);
        }
        Long filterId = dBProfession.getFilterId();
        if (filterId != null) {
            sQLiteStatement.bindLong(4, filterId.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBProfession readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long l = null;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        Long valueOf2 = cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3));
        int i4 = i + 2;
        String string = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            l = Long.valueOf(cursor.getLong(i5));
        }
        return new DBProfession(valueOf, valueOf2, string, l);
    }

    public void readEntity(Cursor cursor, DBProfession dBProfession, int i) {
        int i2 = i + 0;
        Long l = null;
        dBProfession.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBProfession.setIdentifier(cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3)));
        int i4 = i + 2;
        dBProfession.setName(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            l = Long.valueOf(cursor.getLong(i5));
        }
        dBProfession.setFilterId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBProfession dBProfession, long j) {
        dBProfession.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBProfession dBProfession) {
        if (dBProfession != null) {
            return dBProfession.getId();
        }
        return null;
    }

    public List<DBProfession> _queryDBFilter_Professions(Long l) {
        synchronized (this) {
            if (this.dBFilter_ProfessionsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.FilterId.eq((Object) null), new WhereCondition[0]);
                this.dBFilter_ProfessionsQuery = queryBuilder.build();
            }
        }
        Query<DBProfession> forCurrentThread = this.dBFilter_ProfessionsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
