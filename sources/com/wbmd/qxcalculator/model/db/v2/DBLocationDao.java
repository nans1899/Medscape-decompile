package com.wbmd.qxcalculator.model.db.v2;

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

public class DBLocationDao extends AbstractDao<DBLocation, Long> {
    public static final String TABLENAME = "DBLOCATION";
    private Query<DBLocation> dBFilter_LocationsQuery;

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

    public DBLocationDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBLocationDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBLOCATION' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' INTEGER,'NAME' TEXT,'FILTER_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBLOCATION'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBLocation dBLocation) {
        sQLiteStatement.clearBindings();
        Long id = dBLocation.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        Long identifier = dBLocation.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindLong(2, identifier.longValue());
        }
        String name = dBLocation.getName();
        if (name != null) {
            sQLiteStatement.bindString(3, name);
        }
        Long filterId = dBLocation.getFilterId();
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

    public DBLocation readEntity(Cursor cursor, int i) {
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
        return new DBLocation(valueOf, valueOf2, string, l);
    }

    public void readEntity(Cursor cursor, DBLocation dBLocation, int i) {
        int i2 = i + 0;
        Long l = null;
        dBLocation.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBLocation.setIdentifier(cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3)));
        int i4 = i + 2;
        dBLocation.setName(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        if (!cursor.isNull(i5)) {
            l = Long.valueOf(cursor.getLong(i5));
        }
        dBLocation.setFilterId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBLocation dBLocation, long j) {
        dBLocation.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBLocation dBLocation) {
        if (dBLocation != null) {
            return dBLocation.getId();
        }
        return null;
    }

    public List<DBLocation> _queryDBFilter_Locations(Long l) {
        synchronized (this) {
            if (this.dBFilter_LocationsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.FilterId.eq((Object) null), new WhereCondition[0]);
                this.dBFilter_LocationsQuery = queryBuilder.build();
            }
        }
        Query<DBLocation> forCurrentThread = this.dBFilter_LocationsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
