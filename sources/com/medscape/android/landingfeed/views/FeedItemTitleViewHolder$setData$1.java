package com.medscape.android.landingfeed.views;

import android.content.Intent;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.medscape.android.Constants;
import com.medscape.android.activity.webviews.WebviewUtil;
import com.medscape.android.landingfeed.model.FeedDataItem;
import com.medscape.android.update.BackgroundUpdateProgressActivity;
import com.medscape.android.util.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: FeedItemTitleViewHolder.kt */
final class FeedItemTitleViewHolder$setData$1 implements View.OnClickListener {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ FeedDataItem $data;

    FeedItemTitleViewHolder$setData$1(FeedDataItem feedDataItem, FragmentActivity fragmentActivity) {
        this.$data = feedDataItem;
        this.$activity = fragmentActivity;
    }

    public final void onClick(View view) {
        FeedDataItem feedDataItem;
        String url;
        if (Util.isDataUpdatesItem(this.$data)) {
            Intent intent = new Intent(this.$activity, BackgroundUpdateProgressActivity.class);
            FeedDataItem feedDataItem2 = this.$data;
            String str = null;
            Intent putExtra = intent.putExtra(Constants.PREF_UPDATE_TITLE, feedDataItem2 != null ? feedDataItem2.getTitle() : null);
            FeedDataItem feedDataItem3 = this.$data;
            if (feedDataItem3 != null) {
                str = feedDataItem3.getBody();
            }
            Intent putExtra2 = putExtra.putExtra(Constants.PREF_UPDATE_MESSAGE, str);
            Intrinsics.checkNotNullExpressionValue(putExtra2, "Intent(activity, Backgro…DATE_MESSAGE, data?.body)");
            this.$activity.startActivity(putExtra2);
        } else if ((Util.isClinicalAdvancesItem(this.$data) || Util.isLiveEventsItem(this.$data)) && (feedDataItem = this.$data) != null && (url = feedDataItem.getUrl()) != null) {
            WebviewUtil.Companion.launchPlainWebView$default(WebviewUtil.Companion, this.$activity, url, this.$data.getType(), "", "", "other", this.$data.getType(), 1, false, 256, (Object) null);
        }
    }
}
