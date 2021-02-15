package com.qxmd.eventssdkandroid.model.db.v2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.google.firebase.messaging.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.medscape.android.cache.FeedCache;
import com.medscape.android.db.FeedDetail;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import java.util.Date;

public class EventDBDao extends AbstractDao<EventDB, Long> {
    public static final String TABLENAME = "EVENT_DB";

    public static class Properties {
        public static final Property AppName = new Property(1, String.class, "appName", false, "APP_NAME");
        public static final Property AppVersion = new Property(2, String.class, RemoteConfigConstants.RequestFieldKey.APP_VERSION, false, "APP_VERSION");
        public static final Property CampaignAdId = new Property(17, Long.class, "campaignAdId", false, "CAMPAIGN_AD_ID");
        public static final Property Category = new Property(14, String.class, "category", false, "CATEGORY");
        public static final Property Device = new Property(3, String.class, "device", false, "DEVICE");
        public static final Property Duration = new Property(4, Long.class, "duration", false, "DURATION");
        public static final Property EmailHash = new Property(5, String.class, "emailHash", false, "EMAIL_HASH");
        public static final Property EventTypeId = new Property(6, Long.class, "eventTypeId", false, "EVENT_TYPE_ID");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property InstitutionId = new Property(7, Long.class, "institutionId", false, "INSTITUTION_ID");
        public static final Property Label = new Property(15, String.class, Constants.ScionAnalytics.PARAM_LABEL, false, "LABEL");
        public static final Property LocationId = new Property(8, Long.class, "locationId", false, "LOCATION_ID");
        public static final Property ObjectId = new Property(9, Long.class, "objectId", false, "OBJECT_ID");
        public static final Property ProfessionId = new Property(10, Long.class, "professionId", false, "PROFESSION_ID");
        public static final Property SpecialtyId = new Property(11, Long.class, FeedCache.FeedCaches.SPECIALITY_ID, false, "SPECIALTY_ID");
        public static final Property TimeStamp = new Property(13, Date.class, "timeStamp", false, "TIME_STAMP");
        public static final Property Url = new Property(16, String.class, "url", false, FeedDetail.F_URL);
        public static final Property UserId = new Property(12, Long.class, com.webmd.wbmdcmepulse.models.utils.Constants.USER_TAG_KEY_USER_ID, false, "USER_ID");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public EventDBDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public EventDBDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'EVENT_DB' ('_id' INTEGER PRIMARY KEY ,'APP_NAME' TEXT,'APP_VERSION' TEXT,'DEVICE' TEXT,'DURATION' INTEGER,'EMAIL_HASH' TEXT,'EVENT_TYPE_ID' INTEGER,'INSTITUTION_ID' INTEGER,'LOCATION_ID' INTEGER,'OBJECT_ID' INTEGER,'PROFESSION_ID' INTEGER,'SPECIALTY_ID' INTEGER,'USER_ID' INTEGER,'TIME_STAMP' INTEGER,'CATEGORY' TEXT,'LABEL' TEXT,'URL' TEXT,'CAMPAIGN_AD_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'EVENT_DB'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, EventDB eventDB) {
        sQLiteStatement.clearBindings();
        Long id = eventDB.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String appName = eventDB.getAppName();
        if (appName != null) {
            sQLiteStatement.bindString(2, appName);
        }
        String appVersion = eventDB.getAppVersion();
        if (appVersion != null) {
            sQLiteStatement.bindString(3, appVersion);
        }
        String device = eventDB.getDevice();
        if (device != null) {
            sQLiteStatement.bindString(4, device);
        }
        Long duration = eventDB.getDuration();
        if (duration != null) {
            sQLiteStatement.bindLong(5, duration.longValue());
        }
        String emailHash = eventDB.getEmailHash();
        if (emailHash != null) {
            sQLiteStatement.bindString(6, emailHash);
        }
        Long eventTypeId = eventDB.getEventTypeId();
        if (eventTypeId != null) {
            sQLiteStatement.bindLong(7, eventTypeId.longValue());
        }
        Long institutionId = eventDB.getInstitutionId();
        if (institutionId != null) {
            sQLiteStatement.bindLong(8, institutionId.longValue());
        }
        Long locationId = eventDB.getLocationId();
        if (locationId != null) {
            sQLiteStatement.bindLong(9, locationId.longValue());
        }
        Long objectId = eventDB.getObjectId();
        if (objectId != null) {
            sQLiteStatement.bindLong(10, objectId.longValue());
        }
        Long professionId = eventDB.getProfessionId();
        if (professionId != null) {
            sQLiteStatement.bindLong(11, professionId.longValue());
        }
        Long specialtyId = eventDB.getSpecialtyId();
        if (specialtyId != null) {
            sQLiteStatement.bindLong(12, specialtyId.longValue());
        }
        Long userId = eventDB.getUserId();
        if (userId != null) {
            sQLiteStatement.bindLong(13, userId.longValue());
        }
        Date timeStamp = eventDB.getTimeStamp();
        if (timeStamp != null) {
            sQLiteStatement.bindLong(14, timeStamp.getTime());
        }
        String category = eventDB.getCategory();
        if (category != null) {
            sQLiteStatement.bindString(15, category);
        }
        String label = eventDB.getLabel();
        if (label != null) {
            sQLiteStatement.bindString(16, label);
        }
        String url = eventDB.getUrl();
        if (url != null) {
            sQLiteStatement.bindString(17, url);
        }
        Long campaignAdId = eventDB.getCampaignAdId();
        if (campaignAdId != null) {
            sQLiteStatement.bindLong(18, campaignAdId.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public EventDB readEntity(Cursor cursor, int i) {
        Long l;
        Date date;
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
        Long valueOf2 = cursor2.isNull(i6) ? null : Long.valueOf(cursor2.getLong(i6));
        int i7 = i + 5;
        String string4 = cursor2.isNull(i7) ? null : cursor2.getString(i7);
        int i8 = i + 6;
        Long valueOf3 = cursor2.isNull(i8) ? null : Long.valueOf(cursor2.getLong(i8));
        int i9 = i + 7;
        Long valueOf4 = cursor2.isNull(i9) ? null : Long.valueOf(cursor2.getLong(i9));
        int i10 = i + 8;
        Long valueOf5 = cursor2.isNull(i10) ? null : Long.valueOf(cursor2.getLong(i10));
        int i11 = i + 9;
        Long valueOf6 = cursor2.isNull(i11) ? null : Long.valueOf(cursor2.getLong(i11));
        int i12 = i + 10;
        Long valueOf7 = cursor2.isNull(i12) ? null : Long.valueOf(cursor2.getLong(i12));
        int i13 = i + 11;
        Long valueOf8 = cursor2.isNull(i13) ? null : Long.valueOf(cursor2.getLong(i13));
        int i14 = i + 12;
        Long valueOf9 = cursor2.isNull(i14) ? null : Long.valueOf(cursor2.getLong(i14));
        int i15 = i + 13;
        if (cursor2.isNull(i15)) {
            l = valueOf9;
            date = null;
        } else {
            l = valueOf9;
            date = new Date(cursor2.getLong(i15));
        }
        int i16 = i + 14;
        String string5 = cursor2.isNull(i16) ? null : cursor2.getString(i16);
        int i17 = i + 15;
        String string6 = cursor2.isNull(i17) ? null : cursor2.getString(i17);
        int i18 = i + 16;
        String string7 = cursor2.isNull(i18) ? null : cursor2.getString(i18);
        int i19 = i + 17;
        return new EventDB(valueOf, string, string2, string3, valueOf2, string4, valueOf3, valueOf4, valueOf5, valueOf6, valueOf7, valueOf8, l, date, string5, string6, string7, cursor2.isNull(i19) ? null : Long.valueOf(cursor2.getLong(i19)));
    }

    public void readEntity(Cursor cursor, EventDB eventDB, int i) {
        int i2 = i + 0;
        Long l = null;
        eventDB.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        eventDB.setAppName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        eventDB.setAppVersion(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        eventDB.setDevice(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        eventDB.setDuration(cursor.isNull(i6) ? null : Long.valueOf(cursor.getLong(i6)));
        int i7 = i + 5;
        eventDB.setEmailHash(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 6;
        eventDB.setEventTypeId(cursor.isNull(i8) ? null : Long.valueOf(cursor.getLong(i8)));
        int i9 = i + 7;
        eventDB.setInstitutionId(cursor.isNull(i9) ? null : Long.valueOf(cursor.getLong(i9)));
        int i10 = i + 8;
        eventDB.setLocationId(cursor.isNull(i10) ? null : Long.valueOf(cursor.getLong(i10)));
        int i11 = i + 9;
        eventDB.setObjectId(cursor.isNull(i11) ? null : Long.valueOf(cursor.getLong(i11)));
        int i12 = i + 10;
        eventDB.setProfessionId(cursor.isNull(i12) ? null : Long.valueOf(cursor.getLong(i12)));
        int i13 = i + 11;
        eventDB.setSpecialtyId(cursor.isNull(i13) ? null : Long.valueOf(cursor.getLong(i13)));
        int i14 = i + 12;
        eventDB.setUserId(cursor.isNull(i14) ? null : Long.valueOf(cursor.getLong(i14)));
        int i15 = i + 13;
        eventDB.setTimeStamp(cursor.isNull(i15) ? null : new Date(cursor.getLong(i15)));
        int i16 = i + 14;
        eventDB.setCategory(cursor.isNull(i16) ? null : cursor.getString(i16));
        int i17 = i + 15;
        eventDB.setLabel(cursor.isNull(i17) ? null : cursor.getString(i17));
        int i18 = i + 16;
        eventDB.setUrl(cursor.isNull(i18) ? null : cursor.getString(i18));
        int i19 = i + 17;
        if (!cursor.isNull(i19)) {
            l = Long.valueOf(cursor.getLong(i19));
        }
        eventDB.setCampaignAdId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(EventDB eventDB, long j) {
        eventDB.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(EventDB eventDB) {
        if (eventDB != null) {
            return eventDB.getId();
        }
        return null;
    }
}
