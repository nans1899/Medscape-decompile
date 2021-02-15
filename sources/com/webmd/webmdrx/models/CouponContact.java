package com.webmd.webmdrx.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CouponContact {
    @SerializedName("BIN")
    @Expose
    private String bIN;
    @SerializedName("ContactId")
    @Expose
    private Integer contactId;
    @SerializedName("ContactSourceId")
    @Expose
    private Integer contactSourceId;
    @SerializedName("ContactTypeId")
    @Expose
    private Integer contactTypeId;
    @SerializedName("GroupNumber")
    @Expose
    private String groupNumber;
    @SerializedName("MemberNumber")
    @Expose
    private String memberNumber;
    @SerializedName("MemberNumberInt")
    @Expose
    private Integer memberNumberInt;
    @SerializedName("PCN")
    @Expose
    private String pCN;
    @SerializedName("PDFUrl")
    @Expose
    private Object pDFUrl;
    @SerializedName("ProspectId")
    @Expose
    private Integer prospectId;
    @SerializedName("SegmentCode")
    @Expose
    private String segmentCode;

    public String getBIN() {
        return this.bIN;
    }

    public void setBIN(String str) {
        this.bIN = str;
    }

    public Integer getContactId() {
        return this.contactId;
    }

    public void setContactId(Integer num) {
        this.contactId = num;
    }

    public Integer getContactSourceId() {
        return this.contactSourceId;
    }

    public void setContactSourceId(Integer num) {
        this.contactSourceId = num;
    }

    public Integer getContactTypeId() {
        return this.contactTypeId;
    }

    public void setContactTypeId(Integer num) {
        this.contactTypeId = num;
    }

    public String getGroupNumber() {
        return this.groupNumber;
    }

    public void setGroupNumber(String str) {
        this.groupNumber = str;
    }

    public String getMemberNumber() {
        return this.memberNumber;
    }

    public void setMemberNumber(String str) {
        this.memberNumber = str;
    }

    public Integer getMemberNumberInt() {
        return this.memberNumberInt;
    }

    public void setMemberNumberInt(Integer num) {
        this.memberNumberInt = num;
    }

    public String getPCN() {
        return this.pCN;
    }

    public void setPCN(String str) {
        this.pCN = str;
    }

    public Object getPDFUrl() {
        return this.pDFUrl;
    }

    public void setPDFUrl(Object obj) {
        this.pDFUrl = obj;
    }

    public Integer getProspectId() {
        return this.prospectId;
    }

    public void setProspectId(Integer num) {
        this.prospectId = num;
    }

    public String getSegmentCode() {
        return this.segmentCode;
    }

    public void setSegmentCode(String str) {
        this.segmentCode = str;
    }
}
