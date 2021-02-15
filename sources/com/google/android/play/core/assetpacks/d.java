package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.i;

final /* synthetic */ class d implements Runnable {
    private final i a;
    private final String b;
    private final i c;

    d(i iVar, String str, i iVar2) {
        this.a = iVar;
        this.b = str;
        this.c = iVar2;
    }

    public final void run() {
        this.a.a(this.b, this.c);
    }
}
