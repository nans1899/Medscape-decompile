package com.wbmd.qxcalculator.model.db.v9;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.google.firebase.messaging.Constants;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import java.util.List;

public class DBConsentUserDao extends AbstractDao<DBConsentUser, Long> {
    public static final String TABLENAME = "DBCONSENT_USER";
    private Query<DBConsentUser> dBUser_CurrentConsentsQuery;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property IsExplicit = new Property(4, Boolean.class, "isExplicit", false, "IS_EXPLICIT");
        public static final Property IsOptIn = new Property(3, Boolean.class, "isOptIn", false, "IS_OPT_IN");
        public static final Property Label = new Property(2, String.class, Constants.ScionAnalytics.PARAM_LABEL, false, "LABEL");
        public static final Property UserId = new Property(5, Long.class, com.webmd.wbmdcmepulse.models.utils.Constants.USER_TAG_KEY_USER_ID, false, "USER_ID");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBConsentUserDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBConsentUserDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBCONSENT_USER' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'LABEL' TEXT,'IS_OPT_IN' INTEGER,'IS_EXPLICIT' INTEGER,'USER_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBCONSENT_USER'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBConsentUser dBConsentUser) {
        sQLiteStatement.clearBindings();
        Long id = dBConsentUser.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBConsentUser.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String label = dBConsentUser.getLabel();
        if (label != null) {
            sQLiteStatement.bindString(3, label);
        }
        Boolean isOptIn = dBConsentUser.getIsOptIn();
        long j = 1;
        if (isOptIn != null) {
            sQLiteStatement.bindLong(4, isOptIn.booleanValue() ? 1 : 0);
        }
        Boolean isExplicit = dBConsentUser.getIsExplicit();
        if (isExplicit != null) {
            if (!isExplicit.booleanValue()) {
                j = 0;
            }
            sQLiteStatement.bindLong(5, j);
        }
        Long userId = dBConsentUser.getUserId();
        if (userId != null) {
            sQLiteStatement.bindLong(6, userId.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBConsentUser readEntity(Cursor cursor, int i) {
        Boolean bool;
        Boolean bool2;
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        boolean z = true;
        if (cursor.isNull(i5)) {
            bool = null;
        } else {
            bool = Boolean.valueOf(cursor.getShort(i5) != 0);
        }
        int i6 = i + 4;
        if (cursor.isNull(i6)) {
            bool2 = null;
        } else {
            if (cursor.getShort(i6) == 0) {
                z = false;
            }
            bool2 = Boolean.valueOf(z);
        }
        int i7 = i + 5;
        return new DBConsentUser(valueOf, string, string2, bool, bool2, cursor.isNull(i7) ? null : Long.valueOf(cursor.getLong(i7)));
    }

    public void readEntity(Cursor cursor, DBConsentUser dBConsentUser, int i) {
        Boolean bool;
        Boolean bool2;
        int i2 = i + 0;
        Long l = null;
        dBConsentUser.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBConsentUser.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBConsentUser.setLabel(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        boolean z = true;
        if (cursor.isNull(i5)) {
            bool = null;
        } else {
            bool = Boolean.valueOf(cursor.getShort(i5) != 0);
        }
        dBConsentUser.setIsOptIn(bool);
        int i6 = i + 4;
        if (cursor.isNull(i6)) {
            bool2 = null;
        } else {
            if (cursor.getShort(i6) == 0) {
                z = false;
            }
            bool2 = Boolean.valueOf(z);
        }
        dBConsentUser.setIsExplicit(bool2);
        int i7 = i + 5;
        if (!cursor.isNull(i7)) {
            l = Long.valueOf(cursor.getLong(i7));
        }
        dBConsentUser.setUserId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBConsentUser dBConsentUser, long j) {
        dBConsentUser.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBConsentUser dBConsentUser) {
        if (dBConsentUser != null) {
            return dBConsentUser.getId();
        }
        return null;
    }

    public List<DBConsentUser> _queryDBUser_CurrentConsents(Long l) {
        synchronized (this) {
            if (this.dBUser_CurrentConsentsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.UserId.eq((Object) null), new WhereCondition[0]);
                this.dBUser_CurrentConsentsQuery = queryBuilder.build();
            }
        }
        Query<DBConsentUser> forCurrentThread = this.dBUser_CurrentConsentsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
