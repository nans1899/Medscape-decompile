package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

final class m {
    private final Context a;

    public m(Context context) {
        this.a = context;
    }

    static String a(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                return bundle.getString("local_testing_dir");
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final Context a() {
        return this.a;
    }
}
