package com.google.android.play.core.assetpacks;

final /* synthetic */ class di implements Runnable {
    private final bb a;

    private di(bb bbVar) {
        this.a = bbVar;
    }

    static Runnable a(bb bbVar) {
        return new di(bbVar);
    }

    public final void run() {
        this.a.c();
    }
}
