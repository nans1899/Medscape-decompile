package com.wbmd.qxcalculator.model.db;

import com.wbmd.qxcalculator.model.db.DBPushNotificationDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DBPushNotification {
    public static final String TAG = DBPushNotification.class.getSimpleName();
    private Long id;
    private String identifier;
    private String message;
    private Long readDate;
    private Long receivedDate;

    public DBPushNotification() {
    }

    public DBPushNotification(Long l) {
        this.id = l;
    }

    public DBPushNotification(Long l, String str, String str2, Long l2, Long l3) {
        this.id = l;
        this.identifier = str;
        this.message = str2;
        this.readDate = l2;
        this.receivedDate = l3;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public Long getReadDate() {
        return this.readDate;
    }

    public void setReadDate(Long l) {
        this.readDate = l;
    }

    public Long getReceivedDate() {
        return this.receivedDate;
    }

    public void setReceivedDate(Long l) {
        this.receivedDate = l;
    }

    public static synchronized DBPushNotification getPushNotification(DaoSession daoSession, String str) {
        DBPushNotification dBPushNotification;
        synchronized (DBPushNotification.class) {
            if (daoSession == null || str == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBPushNotificationDao(), DBPushNotificationDao.Properties.Identifier, arrayList);
            if (allWithPropertyInData != null) {
                if (!allWithPropertyInData.isEmpty()) {
                    dBPushNotification = (DBPushNotification) allWithPropertyInData.get(0);
                    daoSession.getDBPushNotificationDao().update(dBPushNotification);
                    return dBPushNotification;
                }
            }
            DBPushNotification dBPushNotification2 = new DBPushNotification();
            dBPushNotification2.setIdentifier(str);
            daoSession.getDBPushNotificationDao().insert(dBPushNotification2);
            dBPushNotification = dBPushNotification2;
            daoSession.getDBPushNotificationDao().update(dBPushNotification);
            return dBPushNotification;
        }
    }

    public void updateReceivedDate(DaoSession daoSession, Date date) {
        setReceivedDate(Long.valueOf(date.getTime()));
        daoSession.getDBPushNotificationDao().update(this);
    }

    public void updateReadDate(DaoSession daoSession, Date date) {
        setReadDate(Long.valueOf(date.getTime()));
        daoSession.getDBPushNotificationDao().update(this);
    }

    public int getReceivedHour() {
        if (getReceivedDate() == null) {
            return -1;
        }
        Calendar instance = Calendar.getInstance();
        int i = instance.get(11);
        return instance.get(12) >= 30 ? i + 1 : i;
    }
}
