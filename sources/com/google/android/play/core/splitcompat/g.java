package com.google.android.play.core.splitcompat;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.ZipFile;

final class g implements j {
    final /* synthetic */ r a;
    final /* synthetic */ Set b;
    final /* synthetic */ AtomicBoolean c;
    final /* synthetic */ m d;

    g(m mVar, r rVar, Set set, AtomicBoolean atomicBoolean) {
        this.d = mVar;
        this.a = rVar;
        this.b = set;
        this.c = atomicBoolean;
    }

    public final void a(ZipFile zipFile, Set<l> set) throws IOException {
        this.d.a(this.a, set, new f(this));
    }
}
