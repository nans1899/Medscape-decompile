package com.google.android.play.core.assetpacks;

import android.os.Bundle;

final /* synthetic */ class au implements Runnable {
    private final aw a;
    private final Bundle b;
    private final AssetPackState c;

    au(aw awVar, Bundle bundle, AssetPackState assetPackState) {
        this.a = awVar;
        this.b = bundle;
        this.c = assetPackState;
    }

    public final void run() {
        this.a.a(this.b, this.c);
    }
}
