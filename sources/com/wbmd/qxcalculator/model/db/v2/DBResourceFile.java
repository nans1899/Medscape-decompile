package com.wbmd.qxcalculator.model.db.v2;

public class DBResourceFile {
    private Double aspectRatio;
    private String baseName;
    private String content;
    private Long contentItemId;
    private String contentItemIdentifier;
    private String deviceType;
    private Double dipHeight;
    private Double dipWidth;
    private Long id;
    private String identifier;
    private Long lastModifiedDate;
    private String name;
    private Double scaleFactor;
    private Long size;
    private String src;
    private String type;

    public DBResourceFile() {
    }

    public DBResourceFile(Long l) {
        this.id = l;
    }

    public DBResourceFile(Long l, String str, String str2, Long l2, Long l3, String str3, String str4, Double d, Double d2, String str5, String str6, Double d3, Double d4, String str7, String str8, Long l4) {
        this.id = l;
        this.identifier = str;
        this.name = str2;
        this.lastModifiedDate = l2;
        this.size = l3;
        this.src = str3;
        this.type = str4;
        this.dipWidth = d;
        this.dipHeight = d2;
        this.baseName = str5;
        this.deviceType = str6;
        this.scaleFactor = d3;
        this.aspectRatio = d4;
        this.contentItemIdentifier = str7;
        this.content = str8;
        this.contentItemId = l4;
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

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Long getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Long l) {
        this.lastModifiedDate = l;
    }

    public Long getSize() {
        return this.size;
    }

    public void setSize(Long l) {
        this.size = l;
    }

    public String getSrc() {
        return this.src;
    }

    public void setSrc(String str) {
        this.src = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public Double getDipWidth() {
        return this.dipWidth;
    }

    public void setDipWidth(Double d) {
        this.dipWidth = d;
    }

    public Double getDipHeight() {
        return this.dipHeight;
    }

    public void setDipHeight(Double d) {
        this.dipHeight = d;
    }

    public String getBaseName() {
        return this.baseName;
    }

    public void setBaseName(String str) {
        this.baseName = str;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public Double getScaleFactor() {
        return this.scaleFactor;
    }

    public void setScaleFactor(Double d) {
        this.scaleFactor = d;
    }

    public Double getAspectRatio() {
        return this.aspectRatio;
    }

    public void setAspectRatio(Double d) {
        this.aspectRatio = d;
    }

    public String getContentItemIdentifier() {
        return this.contentItemIdentifier;
    }

    public void setContentItemIdentifier(String str) {
        this.contentItemIdentifier = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public Long getContentItemId() {
        return this.contentItemId;
    }

    public void setContentItemId(Long l) {
        this.contentItemId = l;
    }
}
