package com.medscape.android.activity.events;

import android.os.Bundle;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import com.medscape.android.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroidx/navigation/NavController;", "destination", "Landroidx/navigation/NavDestination;", "<anonymous parameter 2>", "Landroid/os/Bundle;", "onDestinationChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: LiveEventExploreActivity.kt */
final class LiveEventExploreActivity$initNavController$2 implements NavController.OnDestinationChangedListener {
    final /* synthetic */ LiveEventExploreActivity this$0;

    LiveEventExploreActivity$initNavController$2(LiveEventExploreActivity liveEventExploreActivity) {
        this.this$0 = liveEventExploreActivity;
    }

    public final void onDestinationChanged(NavController navController, NavDestination navDestination, Bundle bundle) {
        Intrinsics.checkNotNullParameter(navController, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(navDestination, "destination");
        this.this$0.toggleBottomAndDrawer(navDestination.getId() == R.id.navigation_explore);
    }
}
