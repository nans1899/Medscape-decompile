package com.google.internal.firebase.inappmessaging.v1.sdkserving;

import com.google.developers.mobile.targeting.proto.ClientSignalsProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public interface FetchEligibleCampaignsRequestOrBuilder extends MessageLiteOrBuilder {
    CampaignImpression getAlreadySeenCampaigns(int i);

    int getAlreadySeenCampaignsCount();

    List<CampaignImpression> getAlreadySeenCampaignsList();

    ClientSignalsProto.ClientSignals getClientSignals();

    String getProjectNumber();

    ByteString getProjectNumberBytes();

    ClientAppInfo getRequestingClientApp();

    boolean hasClientSignals();

    boolean hasRequestingClientApp();
}
