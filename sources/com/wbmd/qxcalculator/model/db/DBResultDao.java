package com.wbmd.qxcalculator.model.db;

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

public class DBResultDao extends AbstractDao<DBResult, Long> {
    public static final String TABLENAME = "DBRESULT";
    private Query<DBResult> dBCalculator_ResultsQuery;

    public static class Properties {
        public static final Property Answer = new Property(11, String.class, "answer", false, "ANSWER");
        public static final Property AnswerPrimary = new Property(12, String.class, "answerPrimary", false, "ANSWER_PRIMARY");
        public static final Property AnswerSecondary = new Property(13, String.class, "answerSecondary", false, "ANSWER_SECONDARY");
        public static final Property CalculatorId = new Property(14, Long.class, "calculatorId", false, "CALCULATOR_ID");
        public static final Property ConditionFormula = new Property(5, String.class, "conditionFormula", false, "CONDITION_FORMULA");
        public static final Property Formula = new Property(10, String.class, "formula", false, "FORMULA");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property OrderedId = new Property(2, Long.class, "orderedId", false, "ORDERED_ID");
        public static final Property Position = new Property(3, Integer.class, "position", false, "POSITION");
        public static final Property SubTitle = new Property(8, String.class, "subTitle", false, "SUB_TITLE");
        public static final Property SubTitleFormula = new Property(9, String.class, "subTitleFormula", false, "SUB_TITLE_FORMULA");
        public static final Property Title = new Property(6, String.class, "title", false, "TITLE");
        public static final Property TitleFormula = new Property(7, String.class, "titleFormula", false, "TITLE_FORMULA");
        public static final Property Type = new Property(4, String.class, "type", false, "TYPE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBResultDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBResultDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBRESULT' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'ORDERED_ID' INTEGER,'POSITION' INTEGER,'TYPE' TEXT,'CONDITION_FORMULA' TEXT,'TITLE' TEXT,'TITLE_FORMULA' TEXT,'SUB_TITLE' TEXT,'SUB_TITLE_FORMULA' TEXT,'FORMULA' TEXT,'ANSWER' TEXT,'ANSWER_PRIMARY' TEXT,'ANSWER_SECONDARY' TEXT,'CALCULATOR_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBRESULT'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBResult dBResult) {
        sQLiteStatement.clearBindings();
        Long id = dBResult.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBResult.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        Long orderedId = dBResult.getOrderedId();
        if (orderedId != null) {
            sQLiteStatement.bindLong(3, orderedId.longValue());
        }
        Integer position = dBResult.getPosition();
        if (position != null) {
            sQLiteStatement.bindLong(4, (long) position.intValue());
        }
        String type = dBResult.getType();
        if (type != null) {
            sQLiteStatement.bindString(5, type);
        }
        String conditionFormula = dBResult.getConditionFormula();
        if (conditionFormula != null) {
            sQLiteStatement.bindString(6, conditionFormula);
        }
        String title = dBResult.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(7, title);
        }
        String titleFormula = dBResult.getTitleFormula();
        if (titleFormula != null) {
            sQLiteStatement.bindString(8, titleFormula);
        }
        String subTitle = dBResult.getSubTitle();
        if (subTitle != null) {
            sQLiteStatement.bindString(9, subTitle);
        }
        String subTitleFormula = dBResult.getSubTitleFormula();
        if (subTitleFormula != null) {
            sQLiteStatement.bindString(10, subTitleFormula);
        }
        String formula = dBResult.getFormula();
        if (formula != null) {
            sQLiteStatement.bindString(11, formula);
        }
        String answer = dBResult.getAnswer();
        if (answer != null) {
            sQLiteStatement.bindString(12, answer);
        }
        String answerPrimary = dBResult.getAnswerPrimary();
        if (answerPrimary != null) {
            sQLiteStatement.bindString(13, answerPrimary);
        }
        String answerSecondary = dBResult.getAnswerSecondary();
        if (answerSecondary != null) {
            sQLiteStatement.bindString(14, answerSecondary);
        }
        Long calculatorId = dBResult.getCalculatorId();
        if (calculatorId != null) {
            sQLiteStatement.bindLong(15, calculatorId.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBResult readEntity(Cursor cursor, int i) {
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
        int i14 = i + 12;
        int i15 = i + 13;
        int i16 = i + 14;
        return new DBResult(cursor2.isNull(i2) ? null : Long.valueOf(cursor2.getLong(i2)), cursor2.isNull(i3) ? null : cursor2.getString(i3), cursor2.isNull(i4) ? null : Long.valueOf(cursor2.getLong(i4)), cursor2.isNull(i5) ? null : Integer.valueOf(cursor2.getInt(i5)), cursor2.isNull(i6) ? null : cursor2.getString(i6), cursor2.isNull(i7) ? null : cursor2.getString(i7), cursor2.isNull(i8) ? null : cursor2.getString(i8), cursor2.isNull(i9) ? null : cursor2.getString(i9), cursor2.isNull(i10) ? null : cursor2.getString(i10), cursor2.isNull(i11) ? null : cursor2.getString(i11), cursor2.isNull(i12) ? null : cursor2.getString(i12), cursor2.isNull(i13) ? null : cursor2.getString(i13), cursor2.isNull(i14) ? null : cursor2.getString(i14), cursor2.isNull(i15) ? null : cursor2.getString(i15), cursor2.isNull(i16) ? null : Long.valueOf(cursor2.getLong(i16)));
    }

    public void readEntity(Cursor cursor, DBResult dBResult, int i) {
        int i2 = i + 0;
        Long l = null;
        dBResult.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBResult.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBResult.setOrderedId(cursor.isNull(i4) ? null : Long.valueOf(cursor.getLong(i4)));
        int i5 = i + 3;
        dBResult.setPosition(cursor.isNull(i5) ? null : Integer.valueOf(cursor.getInt(i5)));
        int i6 = i + 4;
        dBResult.setType(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        dBResult.setConditionFormula(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 6;
        dBResult.setTitle(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 7;
        dBResult.setTitleFormula(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 8;
        dBResult.setSubTitle(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 9;
        dBResult.setSubTitleFormula(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 10;
        dBResult.setFormula(cursor.isNull(i12) ? null : cursor.getString(i12));
        int i13 = i + 11;
        dBResult.setAnswer(cursor.isNull(i13) ? null : cursor.getString(i13));
        int i14 = i + 12;
        dBResult.setAnswerPrimary(cursor.isNull(i14) ? null : cursor.getString(i14));
        int i15 = i + 13;
        dBResult.setAnswerSecondary(cursor.isNull(i15) ? null : cursor.getString(i15));
        int i16 = i + 14;
        if (!cursor.isNull(i16)) {
            l = Long.valueOf(cursor.getLong(i16));
        }
        dBResult.setCalculatorId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBResult dBResult, long j) {
        dBResult.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBResult dBResult) {
        if (dBResult != null) {
            return dBResult.getId();
        }
        return null;
    }

    public List<DBResult> _queryDBCalculator_Results(Long l) {
        synchronized (this) {
            if (this.dBCalculator_ResultsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.CalculatorId.eq((Object) null), new WhereCondition[0]);
                this.dBCalculator_ResultsQuery = queryBuilder.build();
            }
        }
        Query<DBResult> forCurrentThread = this.dBCalculator_ResultsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
