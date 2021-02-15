package com.wbmd.ads;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/wbmd/ads/ScrollSpeedLimitSupport;", "", "maxAllowedScrollSpeedForAdInjection", "", "shouldLimitAdInjectionOnScrollSpeed", "", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ScrollSpeedSupport.kt */
public interface ScrollSpeedLimitSupport {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    /* compiled from: ScrollSpeedSupport.kt */
    public static final class DefaultImpls {
        public static int maxAllowedScrollSpeedForAdInjection(ScrollSpeedLimitSupport scrollSpeedLimitSupport) {
            return 10;
        }
    }

    int maxAllowedScrollSpeedForAdInjection();

    boolean shouldLimitAdInjectionOnScrollSpeed();
}
