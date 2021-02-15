package com.wbmd.qxcalculator.model.db.v5;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.adobe.mobile.TargetLocationRequest;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import java.util.List;

public class DBCategoryDao extends AbstractDao<DBCategory, Long> {
    public static final String TABLENAME = "DBCATEGORY";
    private Query<DBCategory> dBCategory_SubCategoriesQuery;
    private Query<DBCategory> dBContentItem_CategoriesQuery;
    private DaoSession daoSession;

    public static class Properties {
        public static final Property CategoryId = new Property(8, Long.class, TargetLocationRequest.TARGET_PARAMETER_CATEGORY_ID, false, "CATEGORY_ID");
        public static final Property ContentItemCategoryCount = new Property(6, Long.class, "contentItemCategoryCount", false, "CONTENT_ITEM_CATEGORY_COUNT");
        public static final Property ContentItemId = new Property(7, Long.class, "contentItemId", false, "CONTENT_ITEM_ID");
        public static final Property ContentSpecificIdentifier = new Property(1, String.class, "contentSpecificIdentifier", false, "CONTENT_SPECIFIC_IDENTIFIER");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(2, String.class, "identifier", false, "IDENTIFIER");
        public static final Property ItemWeight = new Property(5, Integer.class, "itemWeight", false, "ITEM_WEIGHT");
        public static final Property Name = new Property(3, String.class, "name", false, "NAME");
        public static final Property Weight = new Property(4, Integer.class, "weight", false, "WEIGHT");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBCategoryDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBCategoryDao(DaoConfig daoConfig, DaoSession daoSession2) {
        super(daoConfig, daoSession2);
        this.daoSession = daoSession2;
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBCATEGORY' ('_id' INTEGER PRIMARY KEY ,'CONTENT_SPECIFIC_IDENTIFIER' TEXT,'IDENTIFIER' TEXT,'NAME' TEXT,'WEIGHT' INTEGER,'ITEM_WEIGHT' INTEGER,'CONTENT_ITEM_CATEGORY_COUNT' INTEGER,'CONTENT_ITEM_ID' INTEGER,'CATEGORY_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBCATEGORY'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBCategory dBCategory) {
        sQLiteStatement.clearBindings();
        Long id = dBCategory.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String contentSpecificIdentifier = dBCategory.getContentSpecificIdentifier();
        if (contentSpecificIdentifier != null) {
            sQLiteStatement.bindString(2, contentSpecificIdentifier);
        }
        String identifier = dBCategory.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(3, identifier);
        }
        String name = dBCategory.getName();
        if (name != null) {
            sQLiteStatement.bindString(4, name);
        }
        Integer weight = dBCategory.getWeight();
        if (weight != null) {
            sQLiteStatement.bindLong(5, (long) weight.intValue());
        }
        Integer itemWeight = dBCategory.getItemWeight();
        if (itemWeight != null) {
            sQLiteStatement.bindLong(6, (long) itemWeight.intValue());
        }
        Long contentItemCategoryCount = dBCategory.getContentItemCategoryCount();
        if (contentItemCategoryCount != null) {
            sQLiteStatement.bindLong(7, contentItemCategoryCount.longValue());
        }
        Long contentItemId = dBCategory.getContentItemId();
        if (contentItemId != null) {
            sQLiteStatement.bindLong(8, contentItemId.longValue());
        }
        Long categoryId = dBCategory.getCategoryId();
        if (categoryId != null) {
            sQLiteStatement.bindLong(9, categoryId.longValue());
        }
    }

    /* access modifiers changed from: protected */
    public void attachEntity(DBCategory dBCategory) {
        super.attachEntity(dBCategory);
        dBCategory.__setDaoSession(this.daoSession);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBCategory readEntity(Cursor cursor, int i) {
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
        Integer valueOf3 = cursor.isNull(i7) ? null : Integer.valueOf(cursor.getInt(i7));
        int i8 = i + 6;
        Long valueOf4 = cursor.isNull(i8) ? null : Long.valueOf(cursor.getLong(i8));
        int i9 = i + 7;
        int i10 = i + 8;
        return new DBCategory(valueOf, string, string2, string3, valueOf2, valueOf3, valueOf4, cursor.isNull(i9) ? null : Long.valueOf(cursor.getLong(i9)), cursor.isNull(i10) ? null : Long.valueOf(cursor.getLong(i10)));
    }

    public void readEntity(Cursor cursor, DBCategory dBCategory, int i) {
        int i2 = i + 0;
        Long l = null;
        dBCategory.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBCategory.setContentSpecificIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBCategory.setIdentifier(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBCategory.setName(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBCategory.setWeight(cursor.isNull(i6) ? null : Integer.valueOf(cursor.getInt(i6)));
        int i7 = i + 5;
        dBCategory.setItemWeight(cursor.isNull(i7) ? null : Integer.valueOf(cursor.getInt(i7)));
        int i8 = i + 6;
        dBCategory.setContentItemCategoryCount(cursor.isNull(i8) ? null : Long.valueOf(cursor.getLong(i8)));
        int i9 = i + 7;
        dBCategory.setContentItemId(cursor.isNull(i9) ? null : Long.valueOf(cursor.getLong(i9)));
        int i10 = i + 8;
        if (!cursor.isNull(i10)) {
            l = Long.valueOf(cursor.getLong(i10));
        }
        dBCategory.setCategoryId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBCategory dBCategory, long j) {
        dBCategory.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBCategory dBCategory) {
        if (dBCategory != null) {
            return dBCategory.getId();
        }
        return null;
    }

    public List<DBCategory> _queryDBContentItem_Categories(Long l) {
        synchronized (this) {
            if (this.dBContentItem_CategoriesQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ContentItemId.eq((Object) null), new WhereCondition[0]);
                this.dBContentItem_CategoriesQuery = queryBuilder.build();
            }
        }
        Query<DBCategory> forCurrentThread = this.dBContentItem_CategoriesQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }

    public List<DBCategory> _queryDBCategory_SubCategories(Long l) {
        synchronized (this) {
            if (this.dBCategory_SubCategoriesQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.CategoryId.eq((Object) null), new WhereCondition[0]);
                this.dBCategory_SubCategoriesQuery = queryBuilder.build();
            }
        }
        Query<DBCategory> forCurrentThread = this.dBCategory_SubCategoriesQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
