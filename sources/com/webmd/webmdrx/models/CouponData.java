package com.webmd.webmdrx.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CouponData {
    @SerializedName("Contacts")
    @Expose
    private List<CouponContact> contacts = null;
    @SerializedName("ErrorDetails")
    @Expose
    private List<Object> errorDetails = null;
    @SerializedName("Errors")
    @Expose
    private List<Object> errors = null;
    @SerializedName("HasWarnings")
    @Expose
    private Boolean hasWarnings;
    @SerializedName("IPLocation")
    @Expose
    private Object iPLocation;
    @SerializedName("PDFCreated")
    @Expose
    private Boolean pDFCreated;
    @SerializedName("ProspectId")
    @Expose
    private Integer prospectId;
    @SerializedName("SentEmail")
    @Expose
    private Boolean sentEmail;
    @SerializedName("SentSMS")
    @Expose
    private Boolean sentSMS;
    @SerializedName("Success")
    @Expose
    private Boolean success;
    @SerializedName("Warnings")
    @Expose
    private List<Object> warnings = null;

    public List<Object> getErrorDetails() {
        return this.errorDetails;
    }

    public void setErrorDetails(List<Object> list) {
        this.errorDetails = list;
    }

    public List<Object> getErrors() {
        return this.errors;
    }

    public void setErrors(List<Object> list) {
        this.errors = list;
    }

    public Boolean getHasWarnings() {
        return this.hasWarnings;
    }

    public void setHasWarnings(Boolean bool) {
        this.hasWarnings = bool;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean bool) {
        this.success = bool;
    }

    public List<Object> getWarnings() {
        return this.warnings;
    }

    public void setWarnings(List<Object> list) {
        this.warnings = list;
    }

    public List<CouponContact> getContacts() {
        return this.contacts;
    }

    public void setContacts(List<CouponContact> list) {
        this.contacts = list;
    }

    public Object getIPLocation() {
        return this.iPLocation;
    }

    public void setIPLocation(Object obj) {
        this.iPLocation = obj;
    }

    public Boolean getPDFCreated() {
        return this.pDFCreated;
    }

    public void setPDFCreated(Boolean bool) {
        this.pDFCreated = bool;
    }

    public Integer getProspectId() {
        return this.prospectId;
    }

    public void setProspectId(Integer num) {
        this.prospectId = num;
    }

    public Boolean getSentEmail() {
        return this.sentEmail;
    }

    public void setSentEmail(Boolean bool) {
        this.sentEmail = bool;
    }

    public Boolean getSentSMS() {
        return this.sentSMS;
    }

    public void setSentSMS(Boolean bool) {
        this.sentSMS = bool;
    }
}
