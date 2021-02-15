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

public class DBAnswerChoiceDao extends AbstractDao<DBAnswerChoice, Long> {
    public static final String TABLENAME = "DBANSWER_CHOICE";
    private Query<DBAnswerChoice> dBQuestion_AnswerChoicesQuery;

    public static class Properties {
        public static final Property AnswerFactor = new Property(4, Double.class, "answerFactor", false, "ANSWER_FACTOR");
        public static final Property ErrorMessage = new Property(8, String.class, "errorMessage", false, "ERROR_MESSAGE");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property QuestionId = new Property(9, Long.class, "questionId", false, "QUESTION_ID");
        public static final Property SuccessMessage = new Property(6, String.class, "successMessage", false, "SUCCESS_MESSAGE");
        public static final Property TitlePrimary = new Property(2, String.class, "titlePrimary", false, "TITLE_PRIMARY");
        public static final Property TitleSecondary = new Property(3, String.class, "titleSecondary", false, "TITLE_SECONDARY");
        public static final Property UnitIndependent = new Property(5, Boolean.class, "unitIndependent", false, "UNIT_INDEPENDENT");
        public static final Property WarningMessage = new Property(7, String.class, "warningMessage", false, "WARNING_MESSAGE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBAnswerChoiceDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBAnswerChoiceDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBANSWER_CHOICE' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'TITLE_PRIMARY' TEXT,'TITLE_SECONDARY' TEXT,'ANSWER_FACTOR' REAL,'UNIT_INDEPENDENT' INTEGER,'SUCCESS_MESSAGE' TEXT,'WARNING_MESSAGE' TEXT,'ERROR_MESSAGE' TEXT,'QUESTION_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBANSWER_CHOICE'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBAnswerChoice dBAnswerChoice) {
        sQLiteStatement.clearBindings();
        Long id = dBAnswerChoice.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBAnswerChoice.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String titlePrimary = dBAnswerChoice.getTitlePrimary();
        if (titlePrimary != null) {
            sQLiteStatement.bindString(3, titlePrimary);
        }
        String titleSecondary = dBAnswerChoice.getTitleSecondary();
        if (titleSecondary != null) {
            sQLiteStatement.bindString(4, titleSecondary);
        }
        Double answerFactor = dBAnswerChoice.getAnswerFactor();
        if (answerFactor != null) {
            sQLiteStatement.bindDouble(5, answerFactor.doubleValue());
        }
        Boolean unitIndependent = dBAnswerChoice.getUnitIndependent();
        if (unitIndependent != null) {
            sQLiteStatement.bindLong(6, unitIndependent.booleanValue() ? 1 : 0);
        }
        String successMessage = dBAnswerChoice.getSuccessMessage();
        if (successMessage != null) {
            sQLiteStatement.bindString(7, successMessage);
        }
        String warningMessage = dBAnswerChoice.getWarningMessage();
        if (warningMessage != null) {
            sQLiteStatement.bindString(8, warningMessage);
        }
        String errorMessage = dBAnswerChoice.getErrorMessage();
        if (errorMessage != null) {
            sQLiteStatement.bindString(9, errorMessage);
        }
        Long questionId = dBAnswerChoice.getQuestionId();
        if (questionId != null) {
            sQLiteStatement.bindLong(10, questionId.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBAnswerChoice readEntity(Cursor cursor, int i) {
        Boolean bool;
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
        if (cursor.isNull(i7)) {
            bool = null;
        } else {
            bool = Boolean.valueOf(cursor.getShort(i7) != 0);
        }
        int i8 = i + 6;
        String string4 = cursor.isNull(i8) ? null : cursor.getString(i8);
        int i9 = i + 7;
        String string5 = cursor.isNull(i9) ? null : cursor.getString(i9);
        int i10 = i + 8;
        int i11 = i + 9;
        return new DBAnswerChoice(valueOf, string, string2, string3, valueOf2, bool, string4, string5, cursor.isNull(i10) ? null : cursor.getString(i10), cursor.isNull(i11) ? null : Long.valueOf(cursor.getLong(i11)));
    }

    public void readEntity(Cursor cursor, DBAnswerChoice dBAnswerChoice, int i) {
        Boolean bool;
        int i2 = i + 0;
        Long l = null;
        dBAnswerChoice.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBAnswerChoice.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBAnswerChoice.setTitlePrimary(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBAnswerChoice.setTitleSecondary(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBAnswerChoice.setAnswerFactor(cursor.isNull(i6) ? null : Double.valueOf(cursor.getDouble(i6)));
        int i7 = i + 5;
        if (cursor.isNull(i7)) {
            bool = null;
        } else {
            bool = Boolean.valueOf(cursor.getShort(i7) != 0);
        }
        dBAnswerChoice.setUnitIndependent(bool);
        int i8 = i + 6;
        dBAnswerChoice.setSuccessMessage(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 7;
        dBAnswerChoice.setWarningMessage(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 8;
        dBAnswerChoice.setErrorMessage(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 9;
        if (!cursor.isNull(i11)) {
            l = Long.valueOf(cursor.getLong(i11));
        }
        dBAnswerChoice.setQuestionId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBAnswerChoice dBAnswerChoice, long j) {
        dBAnswerChoice.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBAnswerChoice dBAnswerChoice) {
        if (dBAnswerChoice != null) {
            return dBAnswerChoice.getId();
        }
        return null;
    }

    public List<DBAnswerChoice> _queryDBQuestion_AnswerChoices(Long l) {
        synchronized (this) {
            if (this.dBQuestion_AnswerChoicesQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.QuestionId.eq((Object) null), new WhereCondition[0]);
                this.dBQuestion_AnswerChoicesQuery = queryBuilder.build();
            }
        }
        Query<DBAnswerChoice> forCurrentThread = this.dBQuestion_AnswerChoicesQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
