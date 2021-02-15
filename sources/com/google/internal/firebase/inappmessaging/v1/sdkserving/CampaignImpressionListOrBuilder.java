package com.google.internal.firebase.inappmessaging.v1.sdkserving;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public interface CampaignImpressionListOrBuilder extends MessageLiteOrBuilder {
    CampaignImpression getAlreadySeenCampaigns(int i);

    int getAlreadySeenCampaignsCount();

    List<CampaignImpression> getAlreadySeenCampaignsList();
}
