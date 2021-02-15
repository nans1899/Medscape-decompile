package com.webmd.webmdrx.activities.share;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.fragments.ShareSavingsFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\b"}, d2 = {"com/webmd/webmdrx/activities/share/ShareSavingsActivity$setUpTabs$2", "Lcom/google/android/material/tabs/TabLayout$OnTabSelectedListener;", "onTabReselected", "", "tab", "Lcom/google/android/material/tabs/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ShareSavingsActivity.kt */
public final class ShareSavingsActivity$setUpTabs$2 implements TabLayout.OnTabSelectedListener {
    final /* synthetic */ ShareSavingsActivity this$0;

    public void onTabReselected(TabLayout.Tab tab) {
        Intrinsics.checkNotNullParameter(tab, "tab");
    }

    public void onTabUnselected(TabLayout.Tab tab) {
        Intrinsics.checkNotNullParameter(tab, "tab");
    }

    ShareSavingsActivity$setUpTabs$2(ShareSavingsActivity shareSavingsActivity) {
        this.this$0 = shareSavingsActivity;
    }

    public void onTabSelected(TabLayout.Tab tab) {
        ShareSavingsFragment shareSavingsFragment;
        Intrinsics.checkNotNullParameter(tab, "tab");
        ShareSavingsFragment mShareSavingsFragment$wbmdrx_release = this.this$0.getMShareSavingsFragment$wbmdrx_release();
        if (mShareSavingsFragment$wbmdrx_release != null && mShareSavingsFragment$wbmdrx_release.isFragmentAttached()) {
            mShareSavingsFragment$wbmdrx_release.hideKeyboard();
        }
        Integer num = (Integer) tab.getTag();
        boolean z = num != null && num.intValue() == 0;
        ShareSavingsActivity shareSavingsActivity = this.this$0;
        Intent intent = shareSavingsActivity.getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        Bundle extras = intent.getExtras();
        if (extras != null) {
            ShareSavingsFragment.Companion companion = ShareSavingsFragment.Companion;
            ShareSavingsActivity shareSavingsActivity2 = this.this$0;
            String access$getMDrugContentID$p = shareSavingsActivity2.mDrugContentID;
            Intrinsics.checkNotNullExpressionValue(extras, "it");
            shareSavingsFragment = companion.newInstance(z, shareSavingsActivity2, access$getMDrugContentID$p, extras);
        } else {
            shareSavingsFragment = null;
        }
        shareSavingsActivity.setMShareSavingsFragment$wbmdrx_release(shareSavingsFragment);
        ShareSavingsFragment mShareSavingsFragment$wbmdrx_release2 = this.this$0.getMShareSavingsFragment$wbmdrx_release();
        if (mShareSavingsFragment$wbmdrx_release2 != null) {
            this.this$0.getSupportFragmentManager().beginTransaction().replace(R.id.share_tab_container, mShareSavingsFragment$wbmdrx_release2).commit();
        }
    }
}
