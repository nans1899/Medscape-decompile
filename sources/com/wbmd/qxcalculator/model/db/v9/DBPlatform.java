package com.wbmd.qxcalculator.model.db.v9;

public class DBPlatform {
    private Long contentItemId;
    private String deviceType;
    private Long featuredContentAdId;
    private Long id;
    private String identifier;
    private String maxVersion;
    private String minVersion;
    private String os;
    private Long promotionId;
    private Long restrictionId;

    public DBPlatform() {
    }

    public DBPlatform(Long l) {
        this.id = l;
    }

    public DBPlatform(Long l, String str, String str2, String str3, String str4, String str5, Long l2, Long l3, Long l4, Long l5) {
        this.id = l;
        this.identifier = str;
        this.os = str2;
        this.deviceType = str3;
        this.minVersion = str4;
        this.maxVersion = str5;
        this.contentItemId = l2;
        this.featuredContentAdId = l3;
        this.restrictionId = l4;
        this.promotionId = l5;
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

    public String getOs() {
        return this.os;
    }

    public void setOs(String str) {
        this.os = str;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public String getMinVersion() {
        return this.minVersion;
    }

    public void setMinVersion(String str) {
        this.minVersion = str;
    }

    public String getMaxVersion() {
        return this.maxVersion;
    }

    public void setMaxVersion(String str) {
        this.maxVersion = str;
    }

    public Long getContentItemId() {
        return this.contentItemId;
    }

    public void setContentItemId(Long l) {
        this.contentItemId = l;
    }

    public Long getFeaturedContentAdId() {
        return this.featuredContentAdId;
    }

    public void setFeaturedContentAdId(Long l) {
        this.featuredContentAdId = l;
    }

    public Long getRestrictionId() {
        return this.restrictionId;
    }

    public void setRestrictionId(Long l) {
        this.restrictionId = l;
    }

    public Long getPromotionId() {
        return this.promotionId;
    }

    public void setPromotionId(Long l) {
        this.promotionId = l;
    }
}
