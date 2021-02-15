package com.google.android.play.core.assetpacks.model;

import java.util.HashMap;
import java.util.Map;

public final class a {
    private static final Map<Integer, String> a = new HashMap();
    private static final Map<Integer, String> b = new HashMap();

    static {
        a.put(-1, "The requesting app is unavailable (e.g. unpublished, nonexistent version code).");
        a.put(-2, "The requested pack is not available.");
        a.put(-3, "The request is invalid.");
        a.put(-4, "The requested download is not found.");
        a.put(-5, "The Asset Delivery API is not available.");
        a.put(-6, "Network error. Unable to obtain the asset pack details.");
        a.put(-7, "Download not permitted under current device circumstances (e.g. in background).");
        a.put(-10, "Asset pack download failed due to insufficient storage.");
        a.put(-11, "The Play Store app is either not installed or not the official version.");
        a.put(-12, "Tried to show the cellular data confirmation but no asset packs are waiting for Wi-Fi.");
        a.put(-100, "Unknown error downloading an asset pack.");
        b.put(-1, "APP_UNAVAILABLE");
        b.put(-2, "PACK_UNAVAILABLE");
        b.put(-3, "INVALID_REQUEST");
        b.put(-4, "DOWNLOAD_NOT_FOUND");
        b.put(-5, "API_NOT_AVAILABLE");
        b.put(-6, "NETWORK_ERROR");
        b.put(-7, "ACCESS_DENIED");
        b.put(-10, "INSUFFICIENT_STORAGE");
        b.put(-11, "PLAY_STORE_NOT_FOUND");
        b.put(-12, "NETWORK_UNRESTRICTED");
        b.put(-100, "INTERNAL_ERROR");
    }

    public static String a(int i) {
        Map<Integer, String> map = a;
        Integer valueOf = Integer.valueOf(i);
        if (!map.containsKey(valueOf)) {
            return "";
        }
        String str = a.get(valueOf);
        String str2 = b.get(valueOf);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 113 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(" (https://developer.android.com/reference/com/google/android/play/core/assetpacks/model/AssetPackErrorCode.html#");
        sb.append(str2);
        sb.append(")");
        return sb.toString();
    }
}
