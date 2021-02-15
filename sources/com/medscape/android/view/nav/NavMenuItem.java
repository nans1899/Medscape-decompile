package com.medscape.android.view.nav;

import android.content.Context;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.view.nav.NavMenu;

public class NavMenuItem {
    public final boolean mHasSubtitle;
    public final boolean mIsSeparator;
    public final int mMenuAction;
    public final int mPosition;
    public String mSubtitle;
    public String mTitle;

    public NavMenuItem(NavMenu.NavMenuPosition navMenuPosition, String str, int i, String str2, boolean z) {
        this.mPosition = navMenuPosition.getPosition();
        this.mTitle = str;
        this.mSubtitle = str2;
        this.mHasSubtitle = str2 != null;
        this.mIsSeparator = z;
        this.mMenuAction = i;
    }

    public NavMenuItem(Context context, NavMenu.NavMenuPosition navMenuPosition, int i, int i2, String str, boolean z) {
        this.mPosition = navMenuPosition.getPosition();
        context = context == null ? MedscapeApplication.get() : context;
        if (context != null) {
            this.mTitle = context.getResources().getString(i);
        }
        this.mSubtitle = str;
        this.mHasSubtitle = str != null;
        this.mIsSeparator = z;
        this.mMenuAction = i2;
    }

    public String toString() {
        return this.mTitle;
    }
}
