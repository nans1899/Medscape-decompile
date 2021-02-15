package com.wbmd.qxcalculator.model.db.v8;

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

public class DBErrorCheckDao extends AbstractDao<DBErrorCheck, Long> {
    public static final String TABLENAME = "DBERROR_CHECK";
    private Query<DBErrorCheck> dBCalculator_ErrorChecksQuery;

    public static class Properties {
        public static final Property Answer = new Property(6, String.class, "answer", false, "ANSWER");
        public static final Property CalculatorId = new Property(8, Long.class, "calculatorId", false, "CALCULATOR_ID");
        public static final Property Formula = new Property(7, String.class, "formula", false, "FORMULA");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property OrderedId = new Property(2, Long.class, "orderedId", false, "ORDERED_ID");
        public static final Property Position = new Property(3, Integer.class, "position", false, "POSITION");
        public static final Property Title = new Property(5, String.class, "title", false, "TITLE");
        public static final Property Type = new Property(4, String.class, "type", false, "TYPE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBErrorCheckDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBErrorCheckDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBERROR_CHECK' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'ORDERED_ID' INTEGER,'POSITION' INTEGER,'TYPE' TEXT,'TITLE' TEXT,'ANSWER' TEXT,'FORMULA' TEXT,'CALCULATOR_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBERROR_CHECK'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBErrorCheck dBErrorCheck) {
        sQLiteStatement.clearBindings();
        Long id = dBErrorCheck.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBErrorCheck.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        Long orderedId = dBErrorCheck.getOrderedId();
        if (orderedId != null) {
            sQLiteStatement.bindLong(3, orderedId.longValue());
        }
        Integer position = dBErrorCheck.getPosition();
        if (position != null) {
            sQLiteStatement.bindLong(4, (long) position.intValue());
        }
        String type = dBErrorCheck.getType();
        if (type != null) {
            sQLiteStatement.bindString(5, type);
        }
        String title = dBErrorCheck.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(6, title);
        }
        String answer = dBErrorCheck.getAnswer();
        if (answer != null) {
            sQLiteStatement.bindString(7, answer);
        }
        String formula = dBErrorCheck.getFormula();
        if (formula != null) {
            sQLiteStatement.bindString(8, formula);
        }
        Long calculatorId = dBErrorCheck.getCalculatorId();
        if (calculatorId != null) {
            sQLiteStatement.bindLong(9, calculatorId.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBErrorCheck readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        Long valueOf2 = cursor.isNull(i4) ? null : Long.valueOf(cursor.getLong(i4));
        int i5 = i + 3;
        Integer valueOf3 = cursor.isNull(i5) ? null : Integer.valueOf(cursor.getInt(i5));
        int i6 = i + 4;
        String string2 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 5;
        String string3 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = i + 6;
        String string4 = cursor.isNull(i8) ? null : cursor.getString(i8);
        int i9 = i + 7;
        int i10 = i + 8;
        return new DBErrorCheck(valueOf, string, valueOf2, valueOf3, string2, string3, string4, cursor.isNull(i9) ? null : cursor.getString(i9), cursor.isNull(i10) ? null : Long.valueOf(cursor.getLong(i10)));
    }

    public void readEntity(Cursor cursor, DBErrorCheck dBErrorCheck, int i) {
        int i2 = i + 0;
        Long l = null;
        dBErrorCheck.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBErrorCheck.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBErrorCheck.setOrderedId(cursor.isNull(i4) ? null : Long.valueOf(cursor.getLong(i4)));
        int i5 = i + 3;
        dBErrorCheck.setPosition(cursor.isNull(i5) ? null : Integer.valueOf(cursor.getInt(i5)));
        int i6 = i + 4;
        dBErrorCheck.setType(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        dBErrorCheck.setTitle(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 6;
        dBErrorCheck.setAnswer(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 7;
        dBErrorCheck.setFormula(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 8;
        if (!cursor.isNull(i10)) {
            l = Long.valueOf(cursor.getLong(i10));
        }
        dBErrorCheck.setCalculatorId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBErrorCheck dBErrorCheck, long j) {
        dBErrorCheck.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBErrorCheck dBErrorCheck) {
        if (dBErrorCheck != null) {
            return dBErrorCheck.getId();
        }
        return null;
    }

    public List<DBErrorCheck> _queryDBCalculator_ErrorChecks(Long l) {
        synchronized (this) {
            if (this.dBCalculator_ErrorChecksQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.CalculatorId.eq((Object) null), new WhereCondition[0]);
                this.dBCalculator_ErrorChecksQuery = queryBuilder.build();
            }
        }
        Query<DBErrorCheck> forCurrentThread = this.dBCalculator_ErrorChecksQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
