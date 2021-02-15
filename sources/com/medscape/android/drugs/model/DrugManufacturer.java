package com.medscape.android.drugs.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DrugManufacturer implements Serializable {
    static SimpleDateFormat FORMATTER = new SimpleDateFormat("MM/dd/yyyy");
    private int drugId;
    private Date expirationDate;
    private String subTitle;
    private String title;
    private String titleColor;
    private String url;

    public int getDrugId() {
        return this.drugId;
    }

    public void setDrugId(int i) {
        this.drugId = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        if (str == null) {
            this.title = "Contact Manufacturer";
        } else {
            this.title = str;
        }
    }

    public String getTitleColor() {
        return this.titleColor;
    }

    public void setTitleColor(String str) {
        this.titleColor = str;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        if (str != null || this.drugId == 0) {
            this.url = str;
            return;
        }
        this.url = "http://www.medscape.com/sp/mfr/" + this.drugId;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(String str) {
        if (str == null || str.equals("")) {
            this.expirationDate = null;
            return;
        }
        try {
            this.expirationDate = FORMATTER.parse(str.trim());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
