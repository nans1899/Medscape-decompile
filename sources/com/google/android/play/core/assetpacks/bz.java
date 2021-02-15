package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.aa;
import com.google.android.play.core.internal.ce;
import java.util.concurrent.atomic.AtomicBoolean;

final class bz {
    private static final aa a = new aa("ExtractorLooper");
    private final cr b;
    private final bw c;
    private final dv d;
    private final df e;
    private final dj f;
    private final Cdo g;
    private final ce<w> h;
    private final cu i;
    private final AtomicBoolean j = new AtomicBoolean(false);

    bz(cr crVar, ce<w> ceVar, bw bwVar, dv dvVar, df dfVar, dj djVar, Cdo doVar, cu cuVar) {
        this.b = crVar;
        this.h = ceVar;
        this.c = bwVar;
        this.d = dvVar;
        this.e = dfVar;
        this.f = djVar;
        this.g = doVar;
        this.i = cuVar;
    }

    private final void a(int i2, Exception exc) {
        try {
            this.b.d(i2);
            this.b.a(i2);
        } catch (by unused) {
            a.b("Error during error handling: %s", exc.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        a.a("Run extractor loop", new Object[0]);
        if (this.j.compareAndSet(false, true)) {
            while (true) {
                ct ctVar = null;
                try {
                    ctVar = this.i.a();
                } catch (by e2) {
                    a.b("Error while getting next extraction task: %s", e2.getMessage());
                    if (e2.a >= 0) {
                        this.h.a().a(e2.a);
                        a(e2.a, e2);
                    }
                }
                if (ctVar != null) {
                    try {
                        if (ctVar instanceof bv) {
                            this.c.a((bv) ctVar);
                        } else if (ctVar instanceof du) {
                            this.d.a((du) ctVar);
                        } else if (ctVar instanceof de) {
                            this.e.a((de) ctVar);
                        } else if (ctVar instanceof dh) {
                            this.f.a((dh) ctVar);
                        } else if (!(ctVar instanceof dn)) {
                            a.b("Unknown task type: %s", ctVar.getClass().getName());
                        } else {
                            this.g.a((dn) ctVar);
                        }
                    } catch (Exception e3) {
                        a.b("Error during extraction task: %s", e3.getMessage());
                        this.h.a().a(ctVar.j);
                        a(ctVar.j, e3);
                    }
                } else {
                    this.j.set(false);
                    return;
                }
            }
        } else {
            a.d("runLoop already looping; return", new Object[0]);
        }
    }
}
