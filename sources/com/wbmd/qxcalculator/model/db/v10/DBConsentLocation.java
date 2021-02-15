package com.wbmd.qxcalculator.model.db.v10;

public class DBConsentLocation {
    private Boolean defaultOptIn;
    private Boolean explicitRequired;
    private Long id;
    private String identifier;
    private Boolean isEditable;
    private String label;
    private Boolean optInRequired;
    private String shortName;
    private String subTitle;
    private String title;
    private Long userIdConsentLocationsRequired;
    private Long userIdConsentLocationsToRequest;

    public DBConsentLocation() {
    }

    public DBConsentLocation(Long l) {
        this.id = l;
    }

    public DBConsentLocation(Long l, String str, String str2, String str3, String str4, String str5, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Long l2, Long l3) {
        this.id = l;
        this.identifier = str;
        this.label = str2;
        this.title = str3;
        this.subTitle = str4;
        this.shortName = str5;
        this.explicitRequired = bool;
        this.optInRequired = bool2;
        this.isEditable = bool3;
        this.defaultOptIn = bool4;
        this.userIdConsentLocationsRequired = l2;
        this.userIdConsentLocationsToRequest = l3;
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

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String str) {
        this.shortName = str;
    }

    public Boolean getExplicitRequired() {
        return this.explicitRequired;
    }

    public void setExplicitRequired(Boolean bool) {
        this.explicitRequired = bool;
    }

    public Boolean getOptInRequired() {
        return this.optInRequired;
    }

    public void setOptInRequired(Boolean bool) {
        this.optInRequired = bool;
    }

    public Boolean getIsEditable() {
        return this.isEditable;
    }

    public void setIsEditable(Boolean bool) {
        this.isEditable = bool;
    }

    public Boolean getDefaultOptIn() {
        return this.defaultOptIn;
    }

    public void setDefaultOptIn(Boolean bool) {
        this.defaultOptIn = bool;
    }

    public Long getUserIdConsentLocationsRequired() {
        return this.userIdConsentLocationsRequired;
    }

    public void setUserIdConsentLocationsRequired(Long l) {
        this.userIdConsentLocationsRequired = l;
    }

    public Long getUserIdConsentLocationsToRequest() {
        return this.userIdConsentLocationsToRequest;
    }

    public void setUserIdConsentLocationsToRequest(Long l) {
        this.userIdConsentLocationsToRequest = l;
    }
}
