package com.medscape.android.activity.saved.views;

import androidx.viewpager.widget.ViewPager;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.activity.saved.model.TabLayoutElement;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\f"}, d2 = {"com/medscape/android/activity/saved/views/SaveActivity$setupSaved$1", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "onPageScrollStateChanged", "", "state", "", "onPageScrolled", "position", "positionOffset", "", "positionOffsetPixels", "onPageSelected", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SaveActivity.kt */
public final class SaveActivity$setupSaved$1 implements ViewPager.OnPageChangeListener {
    final /* synthetic */ SaveActivity this$0;

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    SaveActivity$setupSaved$1(SaveActivity saveActivity) {
        this.this$0 = saveActivity;
    }

    public void onPageSelected(int i) {
        if (i != SaveActivity.access$getSaveViewModel$p(this.this$0).getActivePosition()) {
            SaveActivity.access$getSaveViewModel$p(this.this$0).setActivePosition(i);
            TabLayoutElement tabLayoutElement = SaveActivity.access$getSaveViewModel$p(this.this$0).getTabElements$medscape_release().get(i);
            if (!this.this$0.getTabClicked()) {
                OmnitureManager.get().markModule(false, "content-tab", "swp", (Map<String, Object>) null);
            }
            this.this$0.getTabListener().onTabClicked(i, tabLayoutElement, false);
            this.this$0.getTabsLayout().scrollToPosition(i);
            String name = SaveActivity.access$getSaveViewModel$p(this.this$0).getTabElements$medscape_release().get(i).getName();
            if (Intrinsics.areEqual((Object) name, (Object) SaveActivity.access$getSaveViewModel$p(this.this$0).getALL())) {
                this.this$0.mPvid = OmnitureManager.get().trackPageView(this.this$0, "other", Constants.SUBSCRIPTION_TYPE_SAVED, "view-all", (String) null, (String) null, (Map<String, Object>) null);
            } else if (Intrinsics.areEqual((Object) name, (Object) SaveActivity.access$getSaveViewModel$p(this.this$0).getDRUGS())) {
                this.this$0.mPvid = OmnitureManager.get().trackPageView(this.this$0, com.medscape.android.Constants.OMNITURE_CHANNEL_REFERENCE, Constants.SUBSCRIPTION_TYPE_SAVED, "view-drugs", (String) null, (String) null, (Map<String, Object>) null);
            } else if (Intrinsics.areEqual((Object) name, (Object) SaveActivity.access$getSaveViewModel$p(this.this$0).getREF())) {
                this.this$0.mPvid = OmnitureManager.get().trackPageView(this.this$0, "other", Constants.SUBSCRIPTION_TYPE_SAVED, "view-cndprcd", (String) null, (String) null, (Map<String, Object>) null);
            } else if (Intrinsics.areEqual((Object) name, (Object) SaveActivity.access$getSaveViewModel$p(this.this$0).getCALC())) {
                this.this$0.mPvid = OmnitureManager.get().trackPageView(this.this$0, "other", Constants.SUBSCRIPTION_TYPE_SAVED, "view-calcs", (String) null, (String) null, (Map<String, Object>) null);
            } else if (Intrinsics.areEqual((Object) name, (Object) SaveActivity.access$getSaveViewModel$p(this.this$0).getNEWS())) {
                this.this$0.mPvid = OmnitureManager.get().trackPageView(this.this$0, com.medscape.android.Constants.OMNITURE_CHANNEL_NEWS, Constants.SUBSCRIPTION_TYPE_SAVED, "view-news", (String) null, (String) null, (Map<String, Object>) null);
            } else if (Intrinsics.areEqual((Object) name, (Object) SaveActivity.access$getSaveViewModel$p(this.this$0).getEDU())) {
                this.this$0.mPvid = OmnitureManager.get().trackPageView(this.this$0, "other", Constants.SUBSCRIPTION_TYPE_SAVED, "view-cme", (String) null, (String) null, (Map<String, Object>) null);
            }
        }
    }
}
