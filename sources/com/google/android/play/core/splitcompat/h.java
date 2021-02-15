package com.google.android.play.core.splitcompat;

import java.io.IOException;
import java.util.Set;
import java.util.zip.ZipFile;

final class h implements j {
    final /* synthetic */ Set a;
    final /* synthetic */ r b;
    final /* synthetic */ m c;

    h(m mVar, Set set, r rVar) {
        this.c = mVar;
        this.a = set;
        this.b = rVar;
    }

    public final void a(ZipFile zipFile, Set<l> set) throws IOException {
        this.a.addAll(m.a(this.c, (Set) set, this.b, zipFile));
    }
}
