package com.medscape.android.activity.events;

import android.view.View;
import com.medscape.android.base.BottomNavBaseActivity;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: LiveEventExploreActivity.kt */
final class LiveEventExploreActivity$generateHeaderView$1 implements View.OnClickListener {
    final /* synthetic */ LiveEventExploreActivity this$0;

    LiveEventExploreActivity$generateHeaderView$1(LiveEventExploreActivity liveEventExploreActivity) {
        this.this$0 = liveEventExploreActivity;
    }

    public final void onClick(View view) {
        BottomNavBaseActivity.toggleNavigationDrawer$default(this.this$0, false, 1, (Object) null);
    }
}
