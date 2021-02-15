package com.google.android.play.core.assetpacks;

final /* synthetic */ class at implements Runnable {
    private final aw a;
    private final AssetPackState b;

    at(aw awVar, AssetPackState assetPackState) {
        this.a = awVar;
        this.b = assetPackState;
    }

    public final void run() {
        this.a.a(this.b);
    }
}
