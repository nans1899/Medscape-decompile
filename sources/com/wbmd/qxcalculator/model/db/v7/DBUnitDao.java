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

public class DBUnitDao extends AbstractDao<DBUnit, Long> {
    public static final String TABLENAME = "DBUNIT";
    private Query<DBUnit> dBQuestion_UnitsQuery;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property InitialValue = new Property(4, Double.class, "initialValue", false, "INITIAL_VALUE");
        public static final Property MaxValue = new Property(7, Double.class, "maxValue", false, "MAX_VALUE");
        public static final Property MaxValueMessage = new Property(9, String.class, "maxValueMessage", false, "MAX_VALUE_MESSAGE");
        public static final Property MinValue = new Property(6, Double.class, "minValue", false, "MIN_VALUE");
        public static final Property MinValueMessage = new Property(8, String.class, "minValueMessage", false, "MIN_VALUE_MESSAGE");
        public static final Property QuestionId = new Property(10, Long.class, "questionId", false, "QUESTION_ID");
        public static final Property Title = new Property(2, String.class, "title", false, "TITLE");
        public static final Property Type = new Property(3, String.class, "type", false, "TYPE");
        public static final Property UnitFactor = new Property(5, Double.class, "unitFactor", false, "UNIT_FACTOR");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBUnitDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBUnitDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBUNIT' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'TITLE' TEXT,'TYPE' TEXT,'INITIAL_VALUE' REAL,'UNIT_FACTOR' REAL,'MIN_VALUE' REAL,'MAX_VALUE' REAL,'MIN_VALUE_MESSAGE' TEXT,'MAX_VALUE_MESSAGE' TEXT,'QUESTION_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBUNIT'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBUnit dBUnit) {
        sQLiteStatement.clearBindings();
        Long id = dBUnit.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBUnit.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String title = dBUnit.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(3, title);
        }
        String type = dBUnit.getType();
        if (type != null) {
            sQLiteStatement.bindString(4, type);
        }
        Double initialValue = dBUnit.getInitialValue();
        if (initialValue != null) {
            sQLiteStatement.bindDouble(5, initialValue.doubleValue());
        }
        Double unitFactor = dBUnit.getUnitFactor();
        if (unitFactor != null) {
            sQLiteStatement.bindDouble(6, unitFactor.doubleValue());
        }
        Double minValue = dBUnit.getMinValue();
        if (minValue != null) {
            sQLiteStatement.bindDouble(7, minValue.doubleValue());
        }
        Double maxValue = dBUnit.getMaxValue();
        if (maxValue != null) {
            sQLiteStatement.bindDouble(8, maxValue.doubleValue());
        }
        String minValueMessage = dBUnit.getMinValueMessage();
        if (minValueMessage != null) {
            sQLiteStatement.bindString(9, minValueMessage);
        }
        String maxValueMessage = dBUnit.getMaxValueMessage();
        if (maxValueMessage != null) {
            sQLiteStatement.bindString(10, maxValueMessage);
        }
        Long questionId = dBUnit.getQuestionId();
        if (questionId != null) {
            sQLiteStatement.bindLong(11, questionId.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBUnit readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        String string3 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        Double valueOf2 = cursor.isNull(i6) ? null : Double.valueOf(cursor.getDouble(i6));
        int i7 = i + 5;
        Double valueOf3 = cursor.isNull(i7) ? null : Double.valueOf(cursor.getDouble(i7));
        int i8 = i + 6;
        Double valueOf4 = cursor.isNull(i8) ? null : Double.valueOf(cursor.getDouble(i8));
        int i9 = i + 7;
        Double valueOf5 = cursor.isNull(i9) ? null : Double.valueOf(cursor.getDouble(i9));
        int i10 = i + 8;
        String string4 = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = i + 9;
        int i12 = i + 10;
        return new DBUnit(valueOf, string, string2, string3, valueOf2, valueOf3, valueOf4, valueOf5, string4, cursor.isNull(i11) ? null : cursor.getString(i11), cursor.isNull(i12) ? null : Long.valueOf(cursor.getLong(i12)));
    }

    public void readEntity(Cursor cursor, DBUnit dBUnit, int i) {
        int i2 = i + 0;
        Long l = null;
        dBUnit.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBUnit.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBUnit.setTitle(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBUnit.setType(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBUnit.setInitialValue(cursor.isNull(i6) ? null : Double.valueOf(cursor.getDouble(i6)));
        int i7 = i + 5;
        dBUnit.setUnitFactor(cursor.isNull(i7) ? null : Double.valueOf(cursor.getDouble(i7)));
        int i8 = i + 6;
        dBUnit.setMinValue(cursor.isNull(i8) ? null : Double.valueOf(cursor.getDouble(i8)));
        int i9 = i + 7;
        dBUnit.setMaxValue(cursor.isNull(i9) ? null : Double.valueOf(cursor.getDouble(i9)));
        int i10 = i + 8;
        dBUnit.setMinValueMessage(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 9;
        dBUnit.setMaxValueMessage(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 10;
        if (!cursor.isNull(i12)) {
            l = Long.valueOf(cursor.getLong(i12));
        }
        dBUnit.setQuestionId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBUnit dBUnit, long j) {
        dBUnit.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBUnit dBUnit) {
        if (dBUnit != null) {
            return dBUnit.getId();
        }
        return null;
    }

    public List<DBUnit> _queryDBQuestion_Units(Long l) {
        synchronized (this) {
            if (this.dBQuestion_UnitsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.QuestionId.eq((Object) null), new WhereCondition[0]);
                this.dBQuestion_UnitsQuery = queryBuilder.build();
            }
        }
        Query<DBUnit> forCurrentThread = this.dBQuestion_UnitsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
