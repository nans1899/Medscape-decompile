package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.i;
import java.util.List;

final /* synthetic */ class cx implements Runnable {
    private final db a;
    private final List b;
    private final az c;
    private final i d;

    cx(db dbVar, List list, az azVar, i iVar) {
        this.a = dbVar;
        this.b = list;
        this.c = azVar;
        this.d = iVar;
    }

    public final void run() {
        this.a.a(this.b, this.c, this.d);
    }
}
