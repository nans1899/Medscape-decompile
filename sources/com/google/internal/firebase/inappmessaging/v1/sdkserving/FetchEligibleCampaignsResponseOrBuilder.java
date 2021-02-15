package com.google.internal.firebase.inappmessaging.v1.sdkserving;

import com.google.internal.firebase.inappmessaging.v1.CampaignProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public interface FetchEligibleCampaignsResponseOrBuilder extends MessageLiteOrBuilder {
    long getExpirationEpochTimestampMillis();

    CampaignProto.ThickContent getMessages(int i);

    int getMessagesCount();

    List<CampaignProto.ThickContent> getMessagesList();
}
