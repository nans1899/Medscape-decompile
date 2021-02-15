package com.wbmd.qxcalculator.model.db.v10;

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

public class DBDefinitionSectionDao extends AbstractDao<DBDefinitionSection, Long> {
    public static final String TABLENAME = "DBDEFINITION_SECTION";
    private Query<DBDefinitionSection> dBDefinition_DefinitionSectionsQuery;

    public static class Properties {
        public static final Property Body = new Property(3, String.class, "body", false, "BODY");
        public static final Property DefinitionId = new Property(5, Long.class, "definitionId", false, "DEFINITION_ID");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property Position = new Property(4, Integer.class, "position", false, "POSITION");
        public static final Property Title = new Property(2, String.class, "title", false, "TITLE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBDefinitionSectionDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBDefinitionSectionDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBDEFINITION_SECTION' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'TITLE' TEXT,'BODY' TEXT,'POSITION' INTEGER,'DEFINITION_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBDEFINITION_SECTION'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBDefinitionSection dBDefinitionSection) {
        sQLiteStatement.clearBindings();
        Long id = dBDefinitionSection.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBDefinitionSection.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String title = dBDefinitionSection.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(3, title);
        }
        String body = dBDefinitionSection.getBody();
        if (body != null) {
            sQLiteStatement.bindString(4, body);
        }
        Integer position = dBDefinitionSection.getPosition();
        if (position != null) {
            sQLiteStatement.bindLong(5, (long) position.intValue());
        }
        Long definitionId = dBDefinitionSection.getDefinitionId();
        if (definitionId != null) {
            sQLiteStatement.bindLong(6, definitionId.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBDefinitionSection readEntity(Cursor cursor, int i) {
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
        return new DBDefinitionSection(valueOf, string, string2, string3, cursor.isNull(i6) ? null : Integer.valueOf(cursor.getInt(i6)), cursor.isNull(i7) ? null : Long.valueOf(cursor.getLong(i7)));
    }

    public void readEntity(Cursor cursor, DBDefinitionSection dBDefinitionSection, int i) {
        int i2 = i + 0;
        Long l = null;
        dBDefinitionSection.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBDefinitionSection.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBDefinitionSection.setTitle(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBDefinitionSection.setBody(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBDefinitionSection.setPosition(cursor.isNull(i6) ? null : Integer.valueOf(cursor.getInt(i6)));
        int i7 = i + 5;
        if (!cursor.isNull(i7)) {
            l = Long.valueOf(cursor.getLong(i7));
        }
        dBDefinitionSection.setDefinitionId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBDefinitionSection dBDefinitionSection, long j) {
        dBDefinitionSection.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBDefinitionSection dBDefinitionSection) {
        if (dBDefinitionSection != null) {
            return dBDefinitionSection.getId();
        }
        return null;
    }

    public List<DBDefinitionSection> _queryDBDefinition_DefinitionSections(Long l) {
        synchronized (this) {
            if (this.dBDefinition_DefinitionSectionsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.DefinitionId.eq((Object) null), new WhereCondition[0]);
                this.dBDefinition_DefinitionSectionsQuery = queryBuilder.build();
            }
        }
        Query<DBDefinitionSection> forCurrentThread = this.dBDefinition_DefinitionSectionsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
