package com.wbmd.qxcalculator.model.db.v8;

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

public class DBConsentLocationDao extends AbstractDao<DBConsentLocation, Long> {
    public static final String TABLENAME = "DBCONSENT_LOCATION";
    private Query<DBConsentLocation> dBUser_ConsentLocationsRequiredQuery;
    private Query<DBConsentLocation> dBUser_ConsentLocationsToRequestQuery;

    public static class Properties {
        public static final Property DefaultOptIn = new Property(9, Boolean.class, "defaultOptIn", false, "DEFAULT_OPT_IN");
        public static final Property ExplicitRequired = new Property(6, Boolean.class, "explicitRequired", false, "EXPLICIT_REQUIRED");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property IsEditable = new Property(8, Boolean.class, "isEditable", false, "IS_EDITABLE");
        public static final Property Label = new Property(2, String.class, Constants.ScionAnalytics.PARAM_LABEL, false, "LABEL");
        public static final Property OptInRequired = new Property(7, Boolean.class, "optInRequired", false, "OPT_IN_REQUIRED");
        public static final Property ShortName = new Property(5, String.class, "shortName", false, "SHORT_NAME");
        public static final Property SubTitle = new Property(4, String.class, "subTitle", false, "SUB_TITLE");
        public static final Property Title = new Property(3, String.class, "title", false, "TITLE");
        public static final Property UserIdConsentLocationsRequired = new Property(10, Long.class, "userIdConsentLocationsRequired", false, "USER_ID_CONSENT_LOCATIONS_REQUIRED");
        public static final Property UserIdConsentLocationsToRequest = new Property(11, Long.class, "userIdConsentLocationsToRequest", false, "USER_ID_CONSENT_LOCATIONS_TO_REQUEST");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBConsentLocationDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBConsentLocationDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBCONSENT_LOCATION' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'LABEL' TEXT,'TITLE' TEXT,'SUB_TITLE' TEXT,'SHORT_NAME' TEXT,'EXPLICIT_REQUIRED' INTEGER,'OPT_IN_REQUIRED' INTEGER,'IS_EDITABLE' INTEGER,'DEFAULT_OPT_IN' INTEGER,'USER_ID_CONSENT_LOCATIONS_REQUIRED' INTEGER,'USER_ID_CONSENT_LOCATIONS_TO_REQUEST' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBCONSENT_LOCATION'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBConsentLocation dBConsentLocation) {
        sQLiteStatement.clearBindings();
        Long id = dBConsentLocation.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBConsentLocation.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String label = dBConsentLocation.getLabel();
        if (label != null) {
            sQLiteStatement.bindString(3, label);
        }
        String title = dBConsentLocation.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(4, title);
        }
        String subTitle = dBConsentLocation.getSubTitle();
        if (subTitle != null) {
            sQLiteStatement.bindString(5, subTitle);
        }
        String shortName = dBConsentLocation.getShortName();
        if (shortName != null) {
            sQLiteStatement.bindString(6, shortName);
        }
        Boolean explicitRequired = dBConsentLocation.getExplicitRequired();
        long j = 1;
        if (explicitRequired != null) {
            sQLiteStatement.bindLong(7, explicitRequired.booleanValue() ? 1 : 0);
        }
        Boolean optInRequired = dBConsentLocation.getOptInRequired();
        if (optInRequired != null) {
            sQLiteStatement.bindLong(8, optInRequired.booleanValue() ? 1 : 0);
        }
        Boolean isEditable = dBConsentLocation.getIsEditable();
        if (isEditable != null) {
            sQLiteStatement.bindLong(9, isEditable.booleanValue() ? 1 : 0);
        }
        Boolean defaultOptIn = dBConsentLocation.getDefaultOptIn();
        if (defaultOptIn != null) {
            if (!defaultOptIn.booleanValue()) {
                j = 0;
            }
            sQLiteStatement.bindLong(10, j);
        }
        Long userIdConsentLocationsRequired = dBConsentLocation.getUserIdConsentLocationsRequired();
        if (userIdConsentLocationsRequired != null) {
            sQLiteStatement.bindLong(11, userIdConsentLocationsRequired.longValue());
        }
        Long userIdConsentLocationsToRequest = dBConsentLocation.getUserIdConsentLocationsToRequest();
        if (userIdConsentLocationsToRequest != null) {
            sQLiteStatement.bindLong(12, userIdConsentLocationsToRequest.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBConsentLocation readEntity(Cursor cursor, int i) {
        Boolean bool;
        Boolean bool2;
        Boolean bool3;
        Boolean bool4;
        Cursor cursor2 = cursor;
        int i2 = i + 0;
        Long valueOf = cursor2.isNull(i2) ? null : Long.valueOf(cursor2.getLong(i2));
        int i3 = i + 1;
        String string = cursor2.isNull(i3) ? null : cursor2.getString(i3);
        int i4 = i + 2;
        String string2 = cursor2.isNull(i4) ? null : cursor2.getString(i4);
        int i5 = i + 3;
        String string3 = cursor2.isNull(i5) ? null : cursor2.getString(i5);
        int i6 = i + 4;
        String string4 = cursor2.isNull(i6) ? null : cursor2.getString(i6);
        int i7 = i + 5;
        String string5 = cursor2.isNull(i7) ? null : cursor2.getString(i7);
        int i8 = i + 6;
        boolean z = true;
        if (cursor2.isNull(i8)) {
            bool = null;
        } else {
            bool = Boolean.valueOf(cursor2.getShort(i8) != 0);
        }
        int i9 = i + 7;
        if (cursor2.isNull(i9)) {
            bool2 = null;
        } else {
            bool2 = Boolean.valueOf(cursor2.getShort(i9) != 0);
        }
        int i10 = i + 8;
        if (cursor2.isNull(i10)) {
            bool3 = null;
        } else {
            bool3 = Boolean.valueOf(cursor2.getShort(i10) != 0);
        }
        int i11 = i + 9;
        if (cursor2.isNull(i11)) {
            bool4 = null;
        } else {
            if (cursor2.getShort(i11) == 0) {
                z = false;
            }
            bool4 = Boolean.valueOf(z);
        }
        int i12 = i + 10;
        int i13 = i + 11;
        return new DBConsentLocation(valueOf, string, string2, string3, string4, string5, bool, bool2, bool3, bool4, cursor2.isNull(i12) ? null : Long.valueOf(cursor2.getLong(i12)), cursor2.isNull(i13) ? null : Long.valueOf(cursor2.getLong(i13)));
    }

    public void readEntity(Cursor cursor, DBConsentLocation dBConsentLocation, int i) {
        Boolean bool;
        Boolean bool2;
        Boolean bool3;
        Boolean bool4;
        int i2 = i + 0;
        Long l = null;
        dBConsentLocation.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBConsentLocation.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBConsentLocation.setLabel(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBConsentLocation.setTitle(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBConsentLocation.setSubTitle(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        dBConsentLocation.setShortName(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 6;
        boolean z = true;
        if (cursor.isNull(i8)) {
            bool = null;
        } else {
            bool = Boolean.valueOf(cursor.getShort(i8) != 0);
        }
        dBConsentLocation.setExplicitRequired(bool);
        int i9 = i + 7;
        if (cursor.isNull(i9)) {
            bool2 = null;
        } else {
            bool2 = Boolean.valueOf(cursor.getShort(i9) != 0);
        }
        dBConsentLocation.setOptInRequired(bool2);
        int i10 = i + 8;
        if (cursor.isNull(i10)) {
            bool3 = null;
        } else {
            bool3 = Boolean.valueOf(cursor.getShort(i10) != 0);
        }
        dBConsentLocation.setIsEditable(bool3);
        int i11 = i + 9;
        if (cursor.isNull(i11)) {
            bool4 = null;
        } else {
            if (cursor.getShort(i11) == 0) {
                z = false;
            }
            bool4 = Boolean.valueOf(z);
        }
        dBConsentLocation.setDefaultOptIn(bool4);
        int i12 = i + 10;
        dBConsentLocation.setUserIdConsentLocationsRequired(cursor.isNull(i12) ? null : Long.valueOf(cursor.getLong(i12)));
        int i13 = i + 11;
        if (!cursor.isNull(i13)) {
            l = Long.valueOf(cursor.getLong(i13));
        }
        dBConsentLocation.setUserIdConsentLocationsToRequest(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBConsentLocation dBConsentLocation, long j) {
        dBConsentLocation.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBConsentLocation dBConsentLocation) {
        if (dBConsentLocation != null) {
            return dBConsentLocation.getId();
        }
        return null;
    }

    public List<DBConsentLocation> _queryDBUser_ConsentLocationsRequired(Long l) {
        synchronized (this) {
            if (this.dBUser_ConsentLocationsRequiredQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.UserIdConsentLocationsRequired.eq((Object) null), new WhereCondition[0]);
                this.dBUser_ConsentLocationsRequiredQuery = queryBuilder.build();
            }
        }
        Query<DBConsentLocation> forCurrentThread = this.dBUser_ConsentLocationsRequiredQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }

    public List<DBConsentLocation> _queryDBUser_ConsentLocationsToRequest(Long l) {
        synchronized (this) {
            if (this.dBUser_ConsentLocationsToRequestQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.UserIdConsentLocationsToRequest.eq((Object) null), new WhereCondition[0]);
                this.dBUser_ConsentLocationsToRequestQuery = queryBuilder.build();
            }
        }
        Query<DBConsentLocation> forCurrentThread = this.dBUser_ConsentLocationsToRequestQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
