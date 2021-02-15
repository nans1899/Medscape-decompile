package com.medscape.android.landingfeed.repository;

import com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import net.media.android.bidder.base.common.Constants;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "config", "Lcom/medscape/android/analytics/remoteconfig/feed/FeedConfigModel;", "invoke", "com/medscape/android/landingfeed/repository/FeedRepository$Companion$getFeedItemsByType$1$1"}, k = 3, mv = {1, 4, 0})
/* compiled from: FeedRepository.kt */
final class FeedRepository$Companion$getFeedItemsByType$$inlined$filter$lambda$1 extends Lambda implements Function1<FeedConfigModel, Boolean> {
    final /* synthetic */ int $feedType$inlined;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FeedRepository$Companion$getFeedItemsByType$$inlined$filter$lambda$1(int i) {
        super(1);
        this.$feedType$inlined = i;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((FeedConfigModel) obj));
    }

    public final boolean invoke(FeedConfigModel feedConfigModel) {
        Intrinsics.checkNotNullParameter(feedConfigModel, Constants.CONFIG_FILE_NAME);
        return !Intrinsics.areEqual((Object) feedConfigModel.getFeedType(), (Object) (String) FeedRepository.typeMap.get(Integer.valueOf(this.$feedType$inlined)));
    }
}
