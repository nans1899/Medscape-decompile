package com.google.android.play.core.internal;

import java.io.IOException;
import java.io.InputStream;

public final class bw extends bv {
    private final bv a;
    private final long b;
    private final long c;

    public bw(bv bvVar, long j, long j2) {
        this.a = bvVar;
        long a2 = a(j);
        this.b = a2;
        this.c = a(a2 + j2);
    }

    private final long a(long j) {
        if (j >= 0) {
            return j > this.a.a() ? this.a.a() : j;
        }
        return 0;
    }

    public final long a() {
        return this.c - this.b;
    }

    /* access modifiers changed from: protected */
    public final InputStream a(long j, long j2) throws IOException {
        long a2 = a(this.b + j);
        return this.a.a(a2, a(j2 + a2) - a2);
    }

    public final void close() throws IOException {
    }
}
