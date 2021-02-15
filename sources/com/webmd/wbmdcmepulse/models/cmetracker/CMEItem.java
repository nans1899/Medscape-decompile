package com.webmd.wbmdcmepulse.models.cmetracker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Date;

public class CMEItem implements Parcelable {
    public static final String COMPLETED = "COMPLETED";
    public static final String COMPLETE_LOC = "COMPLETE_LOC";
    public static final String COMPLETE_MOC = "COMPLETE_MOC";
    public static final Parcelable.Creator<CMEItem> CREATOR = new Parcelable.Creator<CMEItem>() {
        public CMEItem createFromParcel(Parcel parcel) {
            return new CMEItem(parcel);
        }

        public CMEItem[] newArray(int i) {
            return new CMEItem[i];
        }
    };
    public static final String IN_PROGRESS = "IN_PROGRESS";
    private String continueActivityUri;
    private String coreCompetency;
    private double credit;
    private String creditType;
    private Date expirationDate;
    private String format;
    private boolean isSection;
    private ArrayList<String> mErrorCodes = new ArrayList<>();
    private String mMocStatus;
    private Date participationDate;
    private String provider;
    private long questionnaireId;
    private String referrerUri;
    private long resultId;
    private double rxCredit;
    private String title;
    private String type;
    private String viewCertificateUri;

    public int describeContents() {
        return 0;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Date date) {
        this.expirationDate = date;
    }

    public Date getParticipationDate() {
        return this.participationDate;
    }

    public void setParticipationDate(Date date) {
        this.participationDate = date;
    }

    public String getCreditType() {
        return this.creditType;
    }

    public void setCreditType(String str) {
        this.creditType = str.replaceAll("&trade;", "™");
    }

    public double getCredit() {
        return this.credit;
    }

    public void setCredit(double d) {
        this.credit = d;
    }

    public double getRxCredit() {
        return this.rxCredit;
    }

    public void setRxCredit(double d) {
        this.rxCredit = d;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String str) {
        this.provider = str;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String str) {
        this.format = str;
    }

    public String getCoreCompetency() {
        return this.coreCompetency;
    }

    public void setCoreCompetency(String str) {
        this.coreCompetency = str;
    }

    public long getResultId() {
        return this.resultId;
    }

    public void setResultId(long j) {
        this.resultId = j;
    }

    public long getQuestionnaireId() {
        return this.questionnaireId;
    }

    public void setQuestionnaireId(long j) {
        this.questionnaireId = j;
    }

    public String getReferrerUri() {
        return this.referrerUri;
    }

    public void setReferrerUri(String str) {
        this.referrerUri = str;
    }

    public String getContinueActivityUri() {
        return this.continueActivityUri;
    }

    public void setContinueActivityUri(String str) {
        this.continueActivityUri = str;
    }

    public String getViewCertificateUri() {
        return this.viewCertificateUri;
    }

    public void setViewCertificateUri(String str) {
        this.viewCertificateUri = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public boolean isSection() {
        return this.isSection;
    }

    public void setIsSection(boolean z) {
        this.isSection = z;
    }

    public ArrayList<String> getErrorCodes() {
        return this.mErrorCodes;
    }

    public void setErrorCodeMap(ArrayList<String> arrayList) {
        this.mErrorCodes = arrayList;
    }

    public String getMOCStatus() {
        return this.mMocStatus;
    }

    public void setMOCStatus(String str) {
        this.mMocStatus = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        Date date = this.expirationDate;
        long j = -1;
        parcel.writeLong(date != null ? date.getTime() : -1);
        Date date2 = this.participationDate;
        if (date2 != null) {
            j = date2.getTime();
        }
        parcel.writeLong(j);
        parcel.writeString(this.creditType);
        parcel.writeDouble(this.credit);
        parcel.writeDouble(this.rxCredit);
        parcel.writeString(this.provider);
        parcel.writeString(this.format);
        parcel.writeString(this.coreCompetency);
        parcel.writeLong(this.resultId);
        parcel.writeLong(this.questionnaireId);
        parcel.writeString(this.referrerUri);
        parcel.writeString(this.continueActivityUri);
        parcel.writeString(this.viewCertificateUri);
        parcel.writeString(this.type);
        parcel.writeByte(this.isSection ? (byte) 1 : 0);
        parcel.writeStringList(this.mErrorCodes);
        parcel.writeString(this.mMocStatus);
    }

    public CMEItem() {
    }

    protected CMEItem(Parcel parcel) {
        Date date;
        this.title = parcel.readString();
        long readLong = parcel.readLong();
        Date date2 = null;
        if (readLong == -1) {
            date = null;
        } else {
            date = new Date(readLong);
        }
        this.expirationDate = date;
        long readLong2 = parcel.readLong();
        this.participationDate = readLong2 != -1 ? new Date(readLong2) : date2;
        this.creditType = parcel.readString();
        this.credit = parcel.readDouble();
        this.rxCredit = parcel.readDouble();
        this.provider = parcel.readString();
        this.format = parcel.readString();
        this.coreCompetency = parcel.readString();
        this.resultId = parcel.readLong();
        this.questionnaireId = parcel.readLong();
        this.referrerUri = parcel.readString();
        this.continueActivityUri = parcel.readString();
        this.viewCertificateUri = parcel.readString();
        this.type = parcel.readString();
        this.isSection = parcel.readByte() != 0;
        this.mErrorCodes = parcel.createStringArrayList();
        this.mMocStatus = parcel.readString();
    }
}
