package com.medscape.android.analytics.remoteconfig.feed;

import com.google.gson.Gson;
import com.medscape.android.Constants;
import com.medscape.android.analytics.remoteconfig.RemoteConfig;
import com.medscape.android.landingfeed.model.FeedDataItem;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u0006J\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u00062\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/feed/FeedConfigManager;", "", "()V", "getFeedData", "Ljava/util/ArrayList;", "Lcom/medscape/android/landingfeed/model/FeedDataItem;", "Lkotlin/collections/ArrayList;", "parseFeedData", "value", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedConfigManager.kt */
public final class FeedConfigManager {
    public final ArrayList<FeedDataItem> getFeedData() {
        String string = new RemoteConfig().getMFirebaseRemoteConfig().getString(Constants.REMOTE_FEED_CONFIG);
        Intrinsics.checkNotNullExpressionValue(string, "RemoteConfig().mFirebase…tants.REMOTE_FEED_CONFIG)");
        return parseFeedData(string);
    }

    public final ArrayList<FeedDataItem> parseFeedData(String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        try {
            Object fromJson = new Gson().fromJson(str, new FeedConfigManager$parseFeedData$feedType$1().getType());
            Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(value, feedType)");
            return (ArrayList) fromJson;
        } catch (Exception unused) {
            return new ArrayList<>();
        }
    }
}
