package mnetinternal;

import android.content.Context;
import android.text.TextUtils;
import net.media.android.bidder.base.MNet;
import net.media.android.bidder.base.configs.AdUnitConfig;

public final class cy {
    private static cy a;

    private cy() {
    }

    public static cy a() {
        if (a == null) {
            a = new cy();
        }
        return a;
    }

    private boolean a(String str) {
        Context context;
        if (!TextUtils.isEmpty(str) && (context = MNet.getContext()) != null && context.getPackageManager().checkPermission(str, context.getPackageName()) == 0 && AdUnitConfig.getInstance().getPublisherConfig().getPermissions().contains(str)) {
            return true;
        }
        return false;
    }

    public boolean b() {
        return a("android.permission.ACCESS_FINE_LOCATION");
    }

    public boolean c() {
        return a("android.permission.ACCESS_COARSE_LOCATION");
    }

    public boolean d() {
        return a("android.permission.ACCESS_NETWORK_STATE");
    }

    public boolean e() {
        return a("com.android.browser.permission.READ_HISTORY_BOOKMARKS");
    }
}
