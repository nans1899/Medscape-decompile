package mnetinternal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.places.model.PlaceFields;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;
import net.media.android.bidder.base.common.b;

public final class cx {
    private static final Pattern a = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
    private static final Pattern b = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
    private static final Pattern c = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");

    public static String a(Context context) {
        if (b.a().e()) {
            return "";
        }
        if (!cy.a().d()) {
            return "unknown";
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return a(context, activeNetworkInfo.getType());
            }
            return "unknown";
        } catch (Exception unused) {
            return "unknown";
        }
    }

    public static boolean b(Context context) {
        if (cy.a().d()) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                return false;
            }
            return true;
        }
        try {
            return ((Boolean) aa.a((Callable) new Callable<Boolean>() {
                /* renamed from: a */
                public Boolean call() {
                    try {
                        return Boolean.valueOf(!TextUtils.isEmpty(InetAddress.getByName("www.google.com").getHostAddress()));
                    } catch (UnknownHostException unused) {
                        return false;
                    }
                }
            }).get()).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    private static String a(Context context, int i) {
        if (i != 0) {
            if (i == 1) {
                return "wifi";
            }
            if (i != 4) {
                return "unknown";
            }
        }
        return c(context);
    }

    private static String c(Context context) {
        switch (((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "mobile_data_2g";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "mobile_data_3g";
            case 13:
                return "mobile_data_4g";
            default:
                return "mobile_data_unknown";
        }
    }

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        char c2 = 65535;
        switch (str.hashCode()) {
            case -2127599310:
                if (str.equals("mobile_data_unknown")) {
                    c2 = 1;
                    break;
                }
                break;
            case 3649301:
                if (str.equals("wifi")) {
                    c2 = 0;
                    break;
                }
                break;
            case 178116749:
                if (str.equals("mobile_data_2g")) {
                    c2 = 2;
                    break;
                }
                break;
            case 178116780:
                if (str.equals("mobile_data_3g")) {
                    c2 = 3;
                    break;
                }
                break;
            case 178116811:
                if (str.equals("mobile_data_4g")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        if (c2 == 0) {
            return 2;
        }
        if (c2 == 1) {
            return 3;
        }
        if (c2 == 2) {
            return 4;
        }
        if (c2 == 3) {
            return 5;
        }
        if (c2 != 4) {
            return 0;
        }
        return 6;
    }
}
