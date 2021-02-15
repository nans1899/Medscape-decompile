package com.google.firebase.inappmessaging.model;

import java.util.Map;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public abstract class InAppMessage {
    @Deprecated
    Button actionButton;
    @Deprecated
    String backgroundHexColor;
    @Deprecated
    Text body;
    @Deprecated
    String campaignId;
    CampaignMetadata campaignMetadata;
    @Deprecated
    String campaignName;
    private Map<String, String> data;
    @Deprecated
    ImageData imageData;
    @Deprecated
    String imageUrl;
    @Deprecated
    Boolean isTestMessage;
    MessageType messageType;
    @Deprecated
    Text title;

    @Deprecated
    public abstract Action getAction();

    @Deprecated
    public InAppMessage(Text text, Text text2, String str, ImageData imageData2, Button button, Action action, String str2, String str3, String str4, Boolean bool, MessageType messageType2, Map<String, String> map) {
        this.title = text;
        this.body = text2;
        this.imageUrl = str;
        this.imageData = imageData2;
        this.actionButton = button;
        this.backgroundHexColor = str2;
        this.campaignId = str3;
        this.campaignName = str4;
        this.isTestMessage = bool;
        this.messageType = messageType2;
        this.campaignMetadata = new CampaignMetadata(str3, str4, bool.booleanValue());
        this.data = map;
    }

    public InAppMessage(CampaignMetadata campaignMetadata2, MessageType messageType2, Map<String, String> map) {
        this.campaignMetadata = campaignMetadata2;
        this.messageType = messageType2;
        this.data = map;
    }

    @Deprecated
    public Text getTitle() {
        return this.title;
    }

    @Deprecated
    public Text getBody() {
        return this.body;
    }

    @Deprecated
    public String getImageUrl() {
        return this.imageUrl;
    }

    @Deprecated
    public ImageData getImageData() {
        return this.imageData;
    }

    @Deprecated
    public Button getActionButton() {
        if (getAction() != null) {
            return getAction().getButton();
        }
        return this.actionButton;
    }

    @Deprecated
    public String getBackgroundHexColor() {
        return this.backgroundHexColor;
    }

    @Deprecated
    public String getCampaignId() {
        return this.campaignMetadata.getCampaignId();
    }

    @Deprecated
    public String getCampaignName() {
        return this.campaignMetadata.getCampaignName();
    }

    @Deprecated
    public Boolean getIsTestMessage() {
        return Boolean.valueOf(this.campaignMetadata.getIsTestMessage());
    }

    public MessageType getMessageType() {
        return this.messageType;
    }

    public CampaignMetadata getCampaignMetadata() {
        return this.campaignMetadata;
    }

    public Map<String, String> getData() {
        return this.data;
    }
}
