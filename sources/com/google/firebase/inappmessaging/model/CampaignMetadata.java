package com.google.firebase.inappmessaging.model;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class CampaignMetadata {
    private final String campaignId;
    private final String campaignName;
    private final boolean isTestMessage;

    public CampaignMetadata(String str, String str2, boolean z) {
        this.campaignId = str;
        this.campaignName = str2;
        this.isTestMessage = z;
    }

    public String getCampaignId() {
        return this.campaignId;
    }

    public String getCampaignName() {
        return this.campaignName;
    }

    public boolean getIsTestMessage() {
        return this.isTestMessage;
    }
}
