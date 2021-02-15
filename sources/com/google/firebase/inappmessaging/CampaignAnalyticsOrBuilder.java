package com.google.firebase.inappmessaging;

import com.google.firebase.inappmessaging.CampaignAnalytics;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public interface CampaignAnalyticsOrBuilder extends MessageLiteOrBuilder {
    String getCampaignId();

    ByteString getCampaignIdBytes();

    ClientAppInfo getClientApp();

    long getClientTimestampMillis();

    DismissType getDismissType();

    int getEngagementMetricsDeliveryRetryCount();

    CampaignAnalytics.EventCase getEventCase();

    EventType getEventType();

    FetchErrorReason getFetchErrorReason();

    String getFiamSdkVersion();

    ByteString getFiamSdkVersionBytes();

    String getProjectNumber();

    ByteString getProjectNumberBytes();

    RenderErrorReason getRenderErrorReason();

    boolean hasCampaignId();

    boolean hasClientApp();

    boolean hasClientTimestampMillis();

    boolean hasDismissType();

    boolean hasEngagementMetricsDeliveryRetryCount();

    boolean hasEventType();

    boolean hasFetchErrorReason();

    boolean hasFiamSdkVersion();

    boolean hasProjectNumber();

    boolean hasRenderErrorReason();
}
