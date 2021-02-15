package com.google.android.play.core.assetpacks;

import android.content.Context;

public class AssetPackManagerFactory {
    public static synchronized AssetPackManager getInstance(Context context) {
        AssetPackManager a;
        synchronized (AssetPackManagerFactory.class) {
            a = dd.a(context).a();
        }
        return a;
    }
}
