package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.i;
import java.util.List;

final /* synthetic */ class cw implements Runnable {
    private final db a;
    private final List b;
    private final i c;
    private final List d;

    cw(db dbVar, List list, i iVar, List list2) {
        this.a = dbVar;
        this.b = list;
        this.c = iVar;
        this.d = list2;
    }

    public final void run() {
        this.a.a(this.b, this.c, this.d);
    }
}
