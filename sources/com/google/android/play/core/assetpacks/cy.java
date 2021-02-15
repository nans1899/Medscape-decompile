package com.google.android.play.core.assetpacks;

final /* synthetic */ class cy implements Runnable {
    private final db a;
    private final int b;
    private final String c;

    cy(db dbVar, int i, String str) {
        this.a = dbVar;
        this.b = i;
        this.c = str;
    }

    public final void run() {
        this.a.b(this.b, this.c);
    }
}
