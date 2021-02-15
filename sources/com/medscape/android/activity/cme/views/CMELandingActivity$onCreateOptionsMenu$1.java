package com.medscape.android.activity.cme.views;

import android.content.Intent;
import android.view.MenuItem;
import androidx.core.view.GravityCompat;
import com.medscape.android.Constants;
import com.medscape.android.base.BottomNavBaseActivity;
import com.medscape.android.search.MedscapeSearchActivity;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/MenuItem;", "kotlin.jvm.PlatformType", "onMenuItemClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: CMELandingActivity.kt */
final class CMELandingActivity$onCreateOptionsMenu$1 implements MenuItem.OnMenuItemClickListener {
    final /* synthetic */ CMELandingActivity this$0;

    CMELandingActivity$onCreateOptionsMenu$1(CMELandingActivity cMELandingActivity) {
        this.this$0 = cMELandingActivity;
    }

    public final boolean onMenuItemClick(MenuItem menuItem) {
        if (this.this$0.getNavigationDrawer().isDrawerOpen((int) GravityCompat.START)) {
            BottomNavBaseActivity.toggleNavigationDrawer$default(this.this$0, false, 1, (Object) null);
        } else {
            Intent intent = new Intent(this.this$0, MedscapeSearchActivity.class);
            intent.setAction("android.intent.action.SEARCH");
            intent.putExtra(Constants.EXTRA_MODE, Constants.SEARCH_EDUCATION);
            this.this$0.startActivity(intent);
        }
        return false;
    }
}
