package bo.app;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.appboy.support.AppboyLogger;

public final class o implements s {
    private static final String a = AppboyLogger.getAppboyLogTag(o.class);
    private z b = z.UNKNOWN;
    private boolean c = false;
    private boolean d = false;

    public z a() {
        return this.b;
    }

    public void a(Intent intent, ConnectivityManager connectivityManager) {
        String action = intent.getAction();
        if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
                if (activeNetworkInfo == null || booleanExtra) {
                    this.b = z.NONE;
                    this.d = false;
                    this.c = false;
                    return;
                }
                this.d = activeNetworkInfo.isConnectedOrConnecting();
                this.c = activeNetworkInfo.isRoaming();
                switch (activeNetworkInfo.getType()) {
                    case 0:
                        int subtype = activeNetworkInfo.getSubtype();
                        if (subtype == 3) {
                            this.b = z.THREE_G;
                            return;
                        } else if (subtype != 13) {
                            this.b = z.TWO_G;
                            return;
                        } else {
                            this.b = z.FOUR_G;
                            return;
                        }
                    case 1:
                        this.b = z.WIFI;
                        return;
                    case 2:
                        this.b = z.UNKNOWN;
                        return;
                    case 3:
                        this.b = z.UNKNOWN;
                        return;
                    case 4:
                        this.b = z.UNKNOWN;
                        return;
                    case 5:
                        this.b = z.UNKNOWN;
                        return;
                    case 6:
                        this.b = z.WIFI;
                        return;
                    case 7:
                        this.b = z.UNKNOWN;
                        return;
                    case 8:
                        this.b = z.UNKNOWN;
                        return;
                    case 9:
                        this.b = z.UNKNOWN;
                        return;
                    default:
                        this.b = z.UNKNOWN;
                        return;
                }
            } catch (SecurityException e) {
                AppboyLogger.e(a, "Failed to get active network information. Ensure the permission android.permission.ACCESS_NETWORK_STATE is defined in your AndroidManifest.xml", e);
            }
        } else {
            String str = a;
            AppboyLogger.w(str, "Unexpected system broadcast received [" + action + "]");
        }
    }
}
