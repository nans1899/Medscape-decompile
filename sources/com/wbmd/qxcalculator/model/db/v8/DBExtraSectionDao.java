package com.wbmd.qxcalculator.model.db.v8;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.medscape.android.Constants;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import java.util.List;

public class DBExtraSectionDao extends AbstractDao<DBExtraSection, Long> {
    public static final String TABLENAME = "DBEXTRA_SECTION";
    private Query<DBExtraSection> dBCalculator_ExtraSectionsQuery;
    private DaoSession daoSession;

    public static class Properties {
        public static final Property CalculatorId = new Property(8, Long.class, "calculatorId", false, "CALCULATOR_ID");
        public static final Property ConditionFormula = new Property(7, String.class, "conditionFormula", false, "CONDITION_FORMULA");
        public static final Property HideWhenNoResults = new Property(6, Boolean.class, "hideWhenNoResults", false, "HIDE_WHEN_NO_RESULTS");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property SectionIndex = new Property(4, Integer.class, Constants.EXTRA_DRUG_SELECTION_INDEX_OBJ, false, "SECTION_INDEX");
        public static final Property ShowSeparators = new Property(5, Boolean.class, "showSeparators", false, "SHOW_SEPARATORS");
        public static final Property SubTitle = new Property(3, String.class, "subTitle", false, "SUB_TITLE");
        public static final Property Title = new Property(2, String.class, "title", false, "TITLE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBExtraSectionDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBExtraSectionDao(DaoConfig daoConfig, DaoSession daoSession2) {
        super(daoConfig, daoSession2);
        this.daoSession = daoSession2;
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBEXTRA_SECTION' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'TITLE' TEXT,'SUB_TITLE' TEXT,'SECTION_INDEX' INTEGER,'SHOW_SEPARATORS' INTEGER,'HIDE_WHEN_NO_RESULTS' INTEGER,'CONDITION_FORMULA' TEXT,'CALCULATOR_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBEXTRA_SECTION'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBExtraSection dBExtraSection) {
        sQLiteStatement.clearBindings();
        Long id = dBExtraSection.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBExtraSection.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String title = dBExtraSection.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(3, title);
        }
        String subTitle = dBExtraSection.getSubTitle();
        if (subTitle != null) {
            sQLiteStatement.bindString(4, subTitle);
        }
        Integer sectionIndex = dBExtraSection.getSectionIndex();
        if (sectionIndex != null) {
            sQLiteStatement.bindLong(5, (long) sectionIndex.intValue());
        }
        Boolean showSeparators = dBExtraSection.getShowSeparators();
        long j = 1;
        if (showSeparators != null) {
            sQLiteStatement.bindLong(6, showSeparators.booleanValue() ? 1 : 0);
        }
        Boolean hideWhenNoResults = dBExtraSection.getHideWhenNoResults();
        if (hideWhenNoResults != null) {
            if (!hideWhenNoResults.booleanValue()) {
                j = 0;
            }
            sQLiteStatement.bindLong(7, j);
        }
        String conditionFormula = dBExtraSection.getConditionFormula();
        if (conditionFormula != null) {
            sQLiteStatement.bindString(8, conditionFormula);
        }
        Long calculatorId = dBExtraSection.getCalculatorId();
        if (calculatorId != null) {
            sQLiteStatement.bindLong(9, calculatorId.longValue());
        }
    }

    /* access modifiers changed from: protected */
    public void attachEntity(DBExtraSection dBExtraSection) {
        super.attachEntity(dBExtraSection);
        dBExtraSection.__setDaoSession(this.daoSession);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBExtraSection readEntity(Cursor cursor, int i) {
        Boolean bool;
        Boolean bool2;
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        String string3 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        Integer valueOf2 = cursor.isNull(i6) ? null : Integer.valueOf(cursor.getInt(i6));
        int i7 = i + 5;
        boolean z = true;
        if (cursor.isNull(i7)) {
            bool = null;
        } else {
            bool = Boolean.valueOf(cursor.getShort(i7) != 0);
        }
        int i8 = i + 6;
        if (cursor.isNull(i8)) {
            bool2 = null;
        } else {
            if (cursor.getShort(i8) == 0) {
                z = false;
            }
            bool2 = Boolean.valueOf(z);
        }
        int i9 = i + 7;
        int i10 = i + 8;
        return new DBExtraSection(valueOf, string, string2, string3, valueOf2, bool, bool2, cursor.isNull(i9) ? null : cursor.getString(i9), cursor.isNull(i10) ? null : Long.valueOf(cursor.getLong(i10)));
    }

    public void readEntity(Cursor cursor, DBExtraSection dBExtraSection, int i) {
        Boolean bool;
        Boolean bool2;
        int i2 = i + 0;
        Long l = null;
        dBExtraSection.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBExtraSection.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBExtraSection.setTitle(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBExtraSection.setSubTitle(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBExtraSection.setSectionIndex(cursor.isNull(i6) ? null : Integer.valueOf(cursor.getInt(i6)));
        int i7 = i + 5;
        boolean z = true;
        if (cursor.isNull(i7)) {
            bool = null;
        } else {
            bool = Boolean.valueOf(cursor.getShort(i7) != 0);
        }
        dBExtraSection.setShowSeparators(bool);
        int i8 = i + 6;
        if (cursor.isNull(i8)) {
            bool2 = null;
        } else {
            if (cursor.getShort(i8) == 0) {
                z = false;
            }
            bool2 = Boolean.valueOf(z);
        }
        dBExtraSection.setHideWhenNoResults(bool2);
        int i9 = i + 7;
        dBExtraSection.setConditionFormula(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 8;
        if (!cursor.isNull(i10)) {
            l = Long.valueOf(cursor.getLong(i10));
        }
        dBExtraSection.setCalculatorId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBExtraSection dBExtraSection, long j) {
        dBExtraSection.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBExtraSection dBExtraSection) {
        if (dBExtraSection != null) {
            return dBExtraSection.getId();
        }
        return null;
    }

    public List<DBExtraSection> _queryDBCalculator_ExtraSections(Long l) {
        synchronized (this) {
            if (this.dBCalculator_ExtraSectionsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.CalculatorId.eq((Object) null), new WhereCondition[0]);
                this.dBCalculator_ExtraSectionsQuery = queryBuilder.build();
            }
        }
        Query<DBExtraSection> forCurrentThread = this.dBCalculator_ExtraSectionsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
