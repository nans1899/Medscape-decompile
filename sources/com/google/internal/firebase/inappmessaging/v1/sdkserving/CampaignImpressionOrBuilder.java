package com.google.internal.firebase.inappmessaging.v1.sdkserving;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public interface CampaignImpressionOrBuilder extends MessageLiteOrBuilder {
    String getCampaignId();

    ByteString getCampaignIdBytes();

    long getImpressionTimestampMillis();
}
