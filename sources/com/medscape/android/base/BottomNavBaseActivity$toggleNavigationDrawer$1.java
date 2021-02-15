package com.medscape.android.base;

import androidx.drawerlayout.widget.DrawerLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
/* compiled from: BottomNavBaseActivity.kt */
final /* synthetic */ class BottomNavBaseActivity$toggleNavigationDrawer$1 extends MutablePropertyReference0Impl {
    BottomNavBaseActivity$toggleNavigationDrawer$1(BottomNavBaseActivity bottomNavBaseActivity) {
        super(bottomNavBaseActivity, BottomNavBaseActivity.class, "navigationDrawer", "getNavigationDrawer()Landroidx/drawerlayout/widget/DrawerLayout;", 0);
    }

    public Object get() {
        return BottomNavBaseActivity.access$getNavigationDrawer$p((BottomNavBaseActivity) this.receiver);
    }

    public void set(Object obj) {
        ((BottomNavBaseActivity) this.receiver).navigationDrawer = (DrawerLayout) obj;
    }
}
