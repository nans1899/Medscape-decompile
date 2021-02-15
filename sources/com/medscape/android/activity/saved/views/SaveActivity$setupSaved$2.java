package com.medscape.android.activity.saved.views;

import androidx.lifecycle.Observer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 4, 0})
/* compiled from: SaveActivity.kt */
final class SaveActivity$setupSaved$2<T> implements Observer<Boolean> {
    final /* synthetic */ SaveActivity this$0;

    SaveActivity$setupSaved$2(SaveActivity saveActivity) {
        this.this$0 = saveActivity;
    }

    public final void onChanged(Boolean bool) {
        if (Intrinsics.areEqual((Object) bool, (Object) true)) {
            this.this$0.getTabsLayout().setVisibility(8);
            SaveActivity.access$getViewPager$p(this.this$0).setVisibility(8);
            SaveActivity.access$getEmptyView$p(this.this$0).setVisibility(0);
            this.this$0.getDelete().setVisibility(8);
            SaveActivity.access$getSaveViewModel$p(this.this$0).setEditModeActive(false);
        }
    }
}
