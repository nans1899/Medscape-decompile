package com.webmd.medscape.live.explorelivevents.ui.fragments;

import android.view.View;
import kotlin.Metadata;
import kotlin.Pair;
import org.threeten.bp.ZonedDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: ExploreFragment.kt */
final class ExploreFragment$initUI$1 implements View.OnClickListener {
    final /* synthetic */ ExploreFragment this$0;

    ExploreFragment$initUI$1(ExploreFragment exploreFragment) {
        this.this$0 = exploreFragment;
    }

    public final void onClick(View view) {
        this.this$0.getPreferences().clear("KEY_SELECTED_SPECIALTIES");
        this.this$0.getPreferences().clear("KEY_SELECTED_LOCATIONS");
        EventsFragment.Companion.setOmnitureScreenType(0);
        this.this$0.showEvents(new Pair(ZonedDateTime.now(), null));
    }
}
