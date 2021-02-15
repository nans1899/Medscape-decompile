package com.medscape.android.homescreen.views;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.medscape.android.base.BottomNavBaseActivity;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: HomeScreenFragment.kt */
final class HomeScreenFragment$setListeners$2 implements View.OnClickListener {
    final /* synthetic */ HomeScreenFragment this$0;

    HomeScreenFragment$setListeners$2(HomeScreenFragment homeScreenFragment) {
        this.this$0 = homeScreenFragment;
    }

    public final void onClick(View view) {
        if (this.this$0.getActivity() instanceof BottomNavBaseActivity) {
            FragmentActivity activity = this.this$0.getActivity();
            if (activity != null) {
                BottomNavBaseActivity.toggleNavigationDrawer$default((BottomNavBaseActivity) activity, false, 1, (Object) null);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.base.BottomNavBaseActivity");
        }
    }
}
