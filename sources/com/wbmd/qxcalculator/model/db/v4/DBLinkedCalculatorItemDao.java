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

public class DBLinkedCalculatorItemDao extends AbstractDao<DBLinkedCalculatorItem, Long> {
    public static final String TABLENAME = "DBLINKED_CALCULATOR_ITEM";
    private Query<DBLinkedCalculatorItem> dBQuestion_LinkedCalculatorItemsQuery;

    public static class Properties {
        public static final Property CalculatorIdentifier = new Property(2, String.class, "calculatorIdentifier", false, "CALCULATOR_IDENTIFIER");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property QuestionId = new Property(5, Long.class, "questionId", false, "QUESTION_ID");
        public static final Property ResultConvertFormula = new Property(3, String.class, "resultConvertFormula", false, "RESULT_CONVERT_FORMULA");
        public static final Property ResultUnits = new Property(4, String.class, "resultUnits", false, "RESULT_UNITS");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBLinkedCalculatorItemDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBLinkedCalculatorItemDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBLINKED_CALCULATOR_ITEM' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'CALCULATOR_IDENTIFIER' TEXT,'RESULT_CONVERT_FORMULA' TEXT,'RESULT_UNITS' TEXT,'QUESTION_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBLINKED_CALCULATOR_ITEM'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBLinkedCalculatorItem dBLinkedCalculatorItem) {
        sQLiteStatement.clearBindings();
        Long id = dBLinkedCalculatorItem.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBLinkedCalculatorItem.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String calculatorIdentifier = dBLinkedCalculatorItem.getCalculatorIdentifier();
        if (calculatorIdentifier != null) {
            sQLiteStatement.bindString(3, calculatorIdentifier);
        }
        String resultConvertFormula = dBLinkedCalculatorItem.getResultConvertFormula();
        if (resultConvertFormula != null) {
            sQLiteStatement.bindString(4, resultConvertFormula);
        }
        String resultUnits = dBLinkedCalculatorItem.getResultUnits();
        if (resultUnits != null) {
            sQLiteStatement.bindString(5, resultUnits);
        }
        Long questionId = dBLinkedCalculatorItem.getQuestionId();
        if (questionId != null) {
            sQLiteStatement.bindLong(6, questionId.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBLinkedCalculatorItem readEntity(Cursor cursor, int i) {
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
        return new DBLinkedCalculatorItem(valueOf, string, string2, string3, cursor.isNull(i6) ? null : cursor.getString(i6), cursor.isNull(i7) ? null : Long.valueOf(cursor.getLong(i7)));
    }

    public void readEntity(Cursor cursor, DBLinkedCalculatorItem dBLinkedCalculatorItem, int i) {
        int i2 = i + 0;
        Long l = null;
        dBLinkedCalculatorItem.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBLinkedCalculatorItem.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBLinkedCalculatorItem.setCalculatorIdentifier(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBLinkedCalculatorItem.setResultConvertFormula(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBLinkedCalculatorItem.setResultUnits(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        if (!cursor.isNull(i7)) {
            l = Long.valueOf(cursor.getLong(i7));
        }
        dBLinkedCalculatorItem.setQuestionId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBLinkedCalculatorItem dBLinkedCalculatorItem, long j) {
        dBLinkedCalculatorItem.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBLinkedCalculatorItem dBLinkedCalculatorItem) {
        if (dBLinkedCalculatorItem != null) {
            return dBLinkedCalculatorItem.getId();
        }
        return null;
    }

    public List<DBLinkedCalculatorItem> _queryDBQuestion_LinkedCalculatorItems(Long l) {
        synchronized (this) {
            if (this.dBQuestion_LinkedCalculatorItemsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.QuestionId.eq((Object) null), new WhereCondition[0]);
                this.dBQuestion_LinkedCalculatorItemsQuery = queryBuilder.build();
            }
        }
        Query<DBLinkedCalculatorItem> forCurrentThread = this.dBQuestion_LinkedCalculatorItemsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
