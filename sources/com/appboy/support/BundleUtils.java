package com.appboy.support;

import android.os.Bundle;
import java.util.Map;

public class BundleUtils {
    public static Bundle mapToBundle(Map<String, String> map) {
        Bundle bundle = new Bundle();
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                bundle.putString((String) next.getKey(), (String) next.getValue());
            }
        }
        return bundle;
    }
}
