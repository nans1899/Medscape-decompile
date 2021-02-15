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

public class DBQuestionDao extends AbstractDao<DBQuestion, Long> {
    public static final String TABLENAME = "DBQUESTION";
    private Query<DBQuestion> dBCalculator_QuestionsQuery;
    private DaoSession daoSession;

    public static class Properties {
        public static final Property AllowNegativeAnswer = new Property(13, Boolean.class, "allowNegativeAnswer", false, "ALLOW_NEGATIVE_ANSWER");
        public static final Property CalculatorId = new Property(14, Long.class, "calculatorId", false, "CALCULATOR_ID");
        public static final Property Category = new Property(7, String.class, "category", false, "CATEGORY");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property InitialValue = new Property(12, Double.class, "initialValue", false, "INITIAL_VALUE");
        public static final Property LastUsedUnits = new Property(11, String.class, "lastUsedUnits", false, "LAST_USED_UNITS");
        public static final Property LinkedCalculatorTitle = new Property(10, String.class, "linkedCalculatorTitle", false, "LINKED_CALCULATOR_TITLE");
        public static final Property MoreInfoSource = new Property(9, String.class, "moreInfoSource", false, "MORE_INFO_SOURCE");
        public static final Property MoreInformation = new Property(8, String.class, "moreInformation", false, "MORE_INFORMATION");
        public static final Property OrderedId = new Property(2, Long.class, "orderedId", false, "ORDERED_ID");
        public static final Property Position = new Property(3, Integer.class, "position", false, "POSITION");
        public static final Property SectionName = new Property(4, String.class, "sectionName", false, "SECTION_NAME");
        public static final Property Title = new Property(5, String.class, "title", false, "TITLE");
        public static final Property Type = new Property(6, String.class, "type", false, "TYPE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBQuestionDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBQuestionDao(DaoConfig daoConfig, DaoSession daoSession2) {
        super(daoConfig, daoSession2);
        this.daoSession = daoSession2;
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBQUESTION' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'ORDERED_ID' INTEGER,'POSITION' INTEGER,'SECTION_NAME' TEXT,'TITLE' TEXT,'TYPE' TEXT,'CATEGORY' TEXT,'MORE_INFORMATION' TEXT,'MORE_INFO_SOURCE' TEXT,'LINKED_CALCULATOR_TITLE' TEXT,'LAST_USED_UNITS' TEXT,'INITIAL_VALUE' REAL,'ALLOW_NEGATIVE_ANSWER' INTEGER,'CALCULATOR_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBQUESTION'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBQuestion dBQuestion) {
        sQLiteStatement.clearBindings();
        Long id = dBQuestion.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBQuestion.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        Long orderedId = dBQuestion.getOrderedId();
        if (orderedId != null) {
            sQLiteStatement.bindLong(3, orderedId.longValue());
        }
        Integer position = dBQuestion.getPosition();
        if (position != null) {
            sQLiteStatement.bindLong(4, (long) position.intValue());
        }
        String sectionName = dBQuestion.getSectionName();
        if (sectionName != null) {
            sQLiteStatement.bindString(5, sectionName);
        }
        String title = dBQuestion.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(6, title);
        }
        String type = dBQuestion.getType();
        if (type != null) {
            sQLiteStatement.bindString(7, type);
        }
        String category = dBQuestion.getCategory();
        if (category != null) {
            sQLiteStatement.bindString(8, category);
        }
        String moreInformation = dBQuestion.getMoreInformation();
        if (moreInformation != null) {
            sQLiteStatement.bindString(9, moreInformation);
        }
        String moreInfoSource = dBQuestion.getMoreInfoSource();
        if (moreInfoSource != null) {
            sQLiteStatement.bindString(10, moreInfoSource);
        }
        String linkedCalculatorTitle = dBQuestion.getLinkedCalculatorTitle();
        if (linkedCalculatorTitle != null) {
            sQLiteStatement.bindString(11, linkedCalculatorTitle);
        }
        String lastUsedUnits = dBQuestion.getLastUsedUnits();
        if (lastUsedUnits != null) {
            sQLiteStatement.bindString(12, lastUsedUnits);
        }
        Double initialValue = dBQuestion.getInitialValue();
        if (initialValue != null) {
            sQLiteStatement.bindDouble(13, initialValue.doubleValue());
        }
        Boolean allowNegativeAnswer = dBQuestion.getAllowNegativeAnswer();
        if (allowNegativeAnswer != null) {
            sQLiteStatement.bindLong(14, allowNegativeAnswer.booleanValue() ? 1 : 0);
        }
        Long calculatorId = dBQuestion.getCalculatorId();
        if (calculatorId != null) {
            sQLiteStatement.bindLong(15, calculatorId.longValue());
        }
    }

    /* access modifiers changed from: protected */
    public void attachEntity(DBQuestion dBQuestion) {
        super.attachEntity(dBQuestion);
        dBQuestion.__setDaoSession(this.daoSession);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBQuestion readEntity(Cursor cursor, int i) {
        Boolean bool;
        Cursor cursor2 = cursor;
        int i2 = i + 0;
        Long valueOf = cursor2.isNull(i2) ? null : Long.valueOf(cursor2.getLong(i2));
        int i3 = i + 1;
        String string = cursor2.isNull(i3) ? null : cursor2.getString(i3);
        int i4 = i + 2;
        Long valueOf2 = cursor2.isNull(i4) ? null : Long.valueOf(cursor2.getLong(i4));
        int i5 = i + 3;
        Integer valueOf3 = cursor2.isNull(i5) ? null : Integer.valueOf(cursor2.getInt(i5));
        int i6 = i + 4;
        String string2 = cursor2.isNull(i6) ? null : cursor2.getString(i6);
        int i7 = i + 5;
        String string3 = cursor2.isNull(i7) ? null : cursor2.getString(i7);
        int i8 = i + 6;
        String string4 = cursor2.isNull(i8) ? null : cursor2.getString(i8);
        int i9 = i + 7;
        String string5 = cursor2.isNull(i9) ? null : cursor2.getString(i9);
        int i10 = i + 8;
        String string6 = cursor2.isNull(i10) ? null : cursor2.getString(i10);
        int i11 = i + 9;
        String string7 = cursor2.isNull(i11) ? null : cursor2.getString(i11);
        int i12 = i + 10;
        String string8 = cursor2.isNull(i12) ? null : cursor2.getString(i12);
        int i13 = i + 11;
        String string9 = cursor2.isNull(i13) ? null : cursor2.getString(i13);
        int i14 = i + 12;
        Double valueOf4 = cursor2.isNull(i14) ? null : Double.valueOf(cursor2.getDouble(i14));
        int i15 = i + 13;
        if (cursor2.isNull(i15)) {
            bool = null;
        } else {
            bool = Boolean.valueOf(cursor2.getShort(i15) != 0);
        }
        int i16 = i + 14;
        return new DBQuestion(valueOf, string, valueOf2, valueOf3, string2, string3, string4, string5, string6, string7, string8, string9, valueOf4, bool, cursor2.isNull(i16) ? null : Long.valueOf(cursor2.getLong(i16)));
    }

    public void readEntity(Cursor cursor, DBQuestion dBQuestion, int i) {
        Boolean bool;
        int i2 = i + 0;
        Long l = null;
        dBQuestion.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBQuestion.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBQuestion.setOrderedId(cursor.isNull(i4) ? null : Long.valueOf(cursor.getLong(i4)));
        int i5 = i + 3;
        dBQuestion.setPosition(cursor.isNull(i5) ? null : Integer.valueOf(cursor.getInt(i5)));
        int i6 = i + 4;
        dBQuestion.setSectionName(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        dBQuestion.setTitle(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 6;
        dBQuestion.setType(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 7;
        dBQuestion.setCategory(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 8;
        dBQuestion.setMoreInformation(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 9;
        dBQuestion.setMoreInfoSource(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 10;
        dBQuestion.setLinkedCalculatorTitle(cursor.isNull(i12) ? null : cursor.getString(i12));
        int i13 = i + 11;
        dBQuestion.setLastUsedUnits(cursor.isNull(i13) ? null : cursor.getString(i13));
        int i14 = i + 12;
        dBQuestion.setInitialValue(cursor.isNull(i14) ? null : Double.valueOf(cursor.getDouble(i14)));
        int i15 = i + 13;
        if (cursor.isNull(i15)) {
            bool = null;
        } else {
            bool = Boolean.valueOf(cursor.getShort(i15) != 0);
        }
        dBQuestion.setAllowNegativeAnswer(bool);
        int i16 = i + 14;
        if (!cursor.isNull(i16)) {
            l = Long.valueOf(cursor.getLong(i16));
        }
        dBQuestion.setCalculatorId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBQuestion dBQuestion, long j) {
        dBQuestion.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBQuestion dBQuestion) {
        if (dBQuestion != null) {
            return dBQuestion.getId();
        }
        return null;
    }

    public List<DBQuestion> _queryDBCalculator_Questions(Long l) {
        synchronized (this) {
            if (this.dBCalculator_QuestionsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.CalculatorId.eq((Object) null), new WhereCondition[0]);
                this.dBCalculator_QuestionsQuery = queryBuilder.build();
            }
        }
        Query<DBQuestion> forCurrentThread = this.dBCalculator_QuestionsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
