package com.qxmd.eventssdkandroid.model.db.v1;

import java.util.Date;

public class EventDB {
    private String appName;
    private String appVersion;
    private String device;
    private Long duration;
    private String emailHash;
    private Long eventTypeId;
    private Long id;
    private Long institutionId;
    private Long locationId;
    private Long objectId;
    private Long professionId;
    private Long specialtyId;
    private Date timeStamp;
    private Long userId;

    public EventDB() {
    }

    public EventDB(Long l) {
        this.id = l;
    }

    public EventDB(Long l, String str, String str2, String str3, Long l2, String str4, Long l3, Long l4, Long l5, Long l6, Long l7, Long l8, Long l9, Date date) {
        this.id = l;
        this.appName = str;
        this.appVersion = str2;
        this.device = str3;
        this.duration = l2;
        this.emailHash = str4;
        this.eventTypeId = l3;
        this.institutionId = l4;
        this.locationId = l5;
        this.objectId = l6;
        this.professionId = l7;
        this.specialtyId = l8;
        this.userId = l9;
        this.timeStamp = date;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public String getDevice() {
        return this.device;
    }

    public void setDevice(String str) {
        this.device = str;
    }

    public Long getDuration() {
        return this.duration;
    }

    public void setDuration(Long l) {
        this.duration = l;
    }

    public String getEmailHash() {
        return this.emailHash;
    }

    public void setEmailHash(String str) {
        this.emailHash = str;
    }

    public Long getEventTypeId() {
        return this.eventTypeId;
    }

    public void setEventTypeId(Long l) {
        this.eventTypeId = l;
    }

    public Long getInstitutionId() {
        return this.institutionId;
    }

    public void setInstitutionId(Long l) {
        this.institutionId = l;
    }

    public Long getLocationId() {
        return this.locationId;
    }

    public void setLocationId(Long l) {
        this.locationId = l;
    }

    public Long getObjectId() {
        return this.objectId;
    }

    public void setObjectId(Long l) {
        this.objectId = l;
    }

    public Long getProfessionId() {
        return this.professionId;
    }

    public void setProfessionId(Long l) {
        this.professionId = l;
    }

    public Long getSpecialtyId() {
        return this.specialtyId;
    }

    public void setSpecialtyId(Long l) {
        this.specialtyId = l;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long l) {
        this.userId = l;
    }

    public Date getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(Date date) {
        this.timeStamp = date;
    }
}
