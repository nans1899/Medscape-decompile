package com.medscape.android.landingfeed.repository;

import java.util.Comparator;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0006\u001a\n \u0005*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"<anonymous>", "", "T", "K", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$3"}, k = 3, mv = {1, 4, 0})
/* compiled from: Comparisons.kt */
public final class FeedRepository$Companion$getFeedItemsByType$$inlined$compareBy$1<T> implements Comparator<T> {
    final /* synthetic */ Comparator $comparator;

    public FeedRepository$Companion$getFeedItemsByType$$inlined$compareBy$1(Comparator comparator) {
        this.$comparator = comparator;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r4 = r4.get(0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int compare(T r4, T r5) {
        /*
            r3 = this;
            java.util.Comparator r0 = r3.$comparator
            com.medscape.android.landingfeed.model.FeedDataItem r4 = (com.medscape.android.landingfeed.model.FeedDataItem) r4
            java.util.ArrayList r4 = r4.getConfigItems()
            r1 = 0
            r2 = 0
            if (r4 == 0) goto L_0x001d
            java.lang.Object r4 = r4.get(r1)
            com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel r4 = (com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel) r4
            if (r4 == 0) goto L_0x001d
            int r4 = r4.getPosition()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            goto L_0x001e
        L_0x001d:
            r4 = r2
        L_0x001e:
            com.medscape.android.landingfeed.model.FeedDataItem r5 = (com.medscape.android.landingfeed.model.FeedDataItem) r5
            java.util.ArrayList r5 = r5.getConfigItems()
            if (r5 == 0) goto L_0x0036
            java.lang.Object r5 = r5.get(r1)
            com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel r5 = (com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel) r5
            if (r5 == 0) goto L_0x0036
            int r5 = r5.getPosition()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
        L_0x0036:
            int r4 = r0.compare(r4, r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.landingfeed.repository.FeedRepository$Companion$getFeedItemsByType$$inlined$compareBy$1.compare(java.lang.Object, java.lang.Object):int");
    }
}
