package com.ib.clickstream;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/ib/clickstream/ClickstreamConstants;", "", "()V", "ClickstreamError", "EventType", "clickstream_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ClickstreamConstants.kt */
public final class ClickstreamConstants {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/ib/clickstream/ClickstreamConstants$EventType;", "", "(Ljava/lang/String;I)V", "pageView", "articleClick", "GDPRAcceptance", "digitalFingerprint", "relatedTopicClick", "impressionBatch", "clickstream_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: ClickstreamConstants.kt */
    public enum EventType {
        pageView,
        articleClick,
        GDPRAcceptance,
        digitalFingerprint,
        relatedTopicClick,
        impressionBatch
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/ib/clickstream/ClickstreamConstants$ClickstreamError;", "", "(Ljava/lang/String;I)V", "getErrorMsg", "", "payloadSize", "generic", "clickstream_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: ClickstreamConstants.kt */
    public enum ClickstreamError {
        payloadSize,
        generic;

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

            static {
                int[] iArr = new int[ClickstreamError.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[ClickstreamError.payloadSize.ordinal()] = 1;
            }
        }

        public final String getErrorMsg() {
            return WhenMappings.$EnumSwitchMapping$0[ordinal()] != 1 ? "An error occureed." : "The payload size exceeds the 4KB limit.";
        }
    }
}
