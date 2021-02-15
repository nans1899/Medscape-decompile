package com.google.android.play.core.splitcompat;

import java.io.File;
import java.io.IOException;

final class f implements k {
    final /* synthetic */ g a;

    f(g gVar) {
        this.a = gVar;
    }

    public final void a(l lVar, File file, boolean z) throws IOException {
        this.a.b.add(file);
        if (!z) {
            this.a.c.set(false);
        }
    }
}
