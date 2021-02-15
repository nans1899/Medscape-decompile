package com.wbmd.qxcalculator.model.db.v4;

public class DBPushNotification {
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
}
