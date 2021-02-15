package com.google.android.play.core.assetpacks;

import android.content.Intent;

final /* synthetic */ class da implements Runnable {
    private final db a;
    private final Intent b;

    da(db dbVar, Intent intent) {
        this.a = dbVar;
        this.b = intent;
    }

    public final void run() {
        this.a.a(this.b);
    }
}
