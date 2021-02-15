package com.webmd.webmdrx.models;

public class DrugInteractionDisplayItem {
    private String headerText;
    private DrugInteractionInfo info;
    private boolean isHeader;

    public DrugInteractionDisplayItem() {
        this.isHeader = false;
        this.headerText = "";
        this.info = null;
    }

    public DrugInteractionDisplayItem(DrugInteractionInfo drugInteractionInfo, boolean z, String str) {
        this.info = drugInteractionInfo;
        this.isHeader = z;
        this.headerText = str;
    }

    public boolean isHeader() {
        return this.isHeader;
    }

    public void setHeader(boolean z) {
        this.isHeader = z;
    }

    public String getHeaderText() {
        return this.headerText;
    }

    public void setHeaderText(String str) {
        this.headerText = str;
    }

    public DrugInteractionInfo getInfo() {
        return this.info;
    }

    public void setInfo(DrugInteractionInfo drugInteractionInfo) {
        this.info = drugInteractionInfo;
    }
}
