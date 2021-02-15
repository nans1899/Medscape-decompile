package com.medscape.android.activity.events;

import androidx.navigation.NavController;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
/* compiled from: LiveEventExploreActivity.kt */
final /* synthetic */ class LiveEventExploreActivity$initNavController$1 extends MutablePropertyReference0Impl {
    LiveEventExploreActivity$initNavController$1(LiveEventExploreActivity liveEventExploreActivity) {
        super(liveEventExploreActivity, LiveEventExploreActivity.class, "navController", "getNavController()Landroidx/navigation/NavController;", 0);
    }

    public Object get() {
        return LiveEventExploreActivity.access$getNavController$p((LiveEventExploreActivity) this.receiver);
    }

    public void set(Object obj) {
        ((LiveEventExploreActivity) this.receiver).navController = (NavController) obj;
    }
}
