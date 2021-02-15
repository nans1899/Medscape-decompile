package com.medscape.android.homescreen.views;

import android.content.Intent;
import android.view.View;
import com.medscape.android.Constants;
import com.medscape.android.search.MedscapeSearchActivity;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: HomeScreenFragment.kt */
final class HomeScreenFragment$setListeners$1 implements View.OnClickListener {
    final /* synthetic */ HomeScreenFragment this$0;

    HomeScreenFragment$setListeners$1(HomeScreenFragment homeScreenFragment) {
        this.this$0 = homeScreenFragment;
    }

    public final void onClick(View view) {
        Intent intent = new Intent(this.this$0.getContext(), MedscapeSearchActivity.class);
        intent.setAction("android.intent.action.SEARCH");
        intent.putExtra(Constants.EXTRA_MODE, Constants.SEARCH_REFERENCE);
        this.this$0.startActivity(intent);
    }
}
