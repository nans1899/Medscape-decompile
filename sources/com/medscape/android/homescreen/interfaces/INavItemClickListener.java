package com.medscape.android.homescreen.interfaces;

import com.medscape.android.homescreen.home_nav_tray.models.NavItem;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/medscape/android/homescreen/interfaces/INavItemClickListener;", "", "onNavItemClicked", "", "navItem", "Lcom/medscape/android/homescreen/home_nav_tray/models/NavItem;", "position", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: INavItemClickListener.kt */
public interface INavItemClickListener {
    void onNavItemClicked(NavItem navItem, int i);
}
