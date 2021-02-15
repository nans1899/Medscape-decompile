package com.wbmd.qxcalculator.model.db.v7;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.facebook.share.internal.ShareConstants;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

public class DBPushNotificationDao extends AbstractDao<DBPushNotification, Long> {
    public static final String TABLENAME = "DBPUSH_NOTIFICATION";

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property Message = new Property(2, String.class, ShareConstants.WEB_DIALOG_PARAM_MESSAGE, false, "MESSAGE");
        public static final Property ReadDate = new Property(3, Long.class, "readDate", false, "READ_DATE");
        public static final Property ReceivedDate = new Property(4, Long.class, "receivedDate", false, "RECEIVED_DATE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBPushNotificationDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBPushNotificationDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBPUSH_NOTIFICATION' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'MESSAGE' TEXT,'READ_DATE' INTEGER,'RECEIVED_DATE' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBPUSH_NOTIFICATION'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBPushNotification dBPushNotification) {
        sQLiteStatement.clearBindings();
        Long id = dBPushNotification.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBPushNotification.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String message = dBPushNotification.getMessage();
        if (message != null) {
            sQLiteStatement.bindString(3, message);
        }
        Long readDate = dBPushNotification.getReadDate();
        if (readDate != null) {
            sQLiteStatement.bindLong(4, readDate.longValue());
        }
        Long receivedDate = dBPushNotification.getReceivedDate();
        if (receivedDate != null) {
            sQLiteStatement.bindLong(5, receivedDate.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBPushNotification readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        int i6 = i + 4;
        return new DBPushNotification(valueOf, string, string2, cursor.isNull(i5) ? null : Long.valueOf(cursor.getLong(i5)), cursor.isNull(i6) ? null : Long.valueOf(cursor.getLong(i6)));
    }

    public void readEntity(Cursor cursor, DBPushNotification dBPushNotification, int i) {
        int i2 = i + 0;
        Long l = null;
        dBPushNotification.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBPushNotification.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBPushNotification.setMessage(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBPushNotification.setReadDate(cursor.isNull(i5) ? null : Long.valueOf(cursor.getLong(i5)));
        int i6 = i + 4;
        if (!cursor.isNull(i6)) {
            l = Long.valueOf(cursor.getLong(i6));
        }
        dBPushNotification.setReceivedDate(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBPushNotification dBPushNotification, long j) {
        dBPushNotification.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBPushNotification dBPushNotification) {
        if (dBPushNotification != null) {
            return dBPushNotification.getId();
        }
        return null;
    }
}
