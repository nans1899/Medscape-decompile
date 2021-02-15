package com.medscape.android.homescreen.home_nav_tray.views;

import com.medscape.android.homescreen.home_nav_tray.models.NavItem;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/medscape/android/homescreen/home_nav_tray/models/NavItem;", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: MenuMoveCallcbacks.kt */
final class MenuMoveCallcbacks$clearView$order$1 extends Lambda implements Function1<NavItem, CharSequence> {
    public static final MenuMoveCallcbacks$clearView$order$1 INSTANCE = new MenuMoveCallcbacks$clearView$order$1();

    MenuMoveCallcbacks$clearView$order$1() {
        super(1);
    }

    public final CharSequence invoke(NavItem navItem) {
        Intrinsics.checkNotNullParameter(navItem, "it");
        return String.valueOf(navItem.getId());
    }
}
