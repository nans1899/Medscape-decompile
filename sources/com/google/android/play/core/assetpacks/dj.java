package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.ce;
import java.io.File;
import java.util.concurrent.Executor;

final class dj {
    private final bb a;
    private final ce<w> b;
    private final cr c;
    private final ce<Executor> d;
    private final cb e;

    dj(bb bbVar, ce<w> ceVar, cr crVar, ce<Executor> ceVar2, cb cbVar) {
        this.a = bbVar;
        this.b = ceVar;
        this.c = crVar;
        this.d = ceVar2;
        this.e = cbVar;
    }

    public final void a(dh dhVar) {
        File c2 = this.a.c(dhVar.k, dhVar.a, dhVar.b);
        File e2 = this.a.e(dhVar.k, dhVar.a, dhVar.b);
        if (!c2.exists() || !e2.exists()) {
            throw new by(String.format("Cannot find pack files to move for pack %s.", new Object[]{dhVar.k}), dhVar.j);
        }
        File a2 = this.a.a(dhVar.k, dhVar.a, dhVar.b);
        a2.mkdirs();
        if (c2.renameTo(a2)) {
            File b2 = this.a.b(dhVar.k, dhVar.a, dhVar.b);
            b2.mkdirs();
            if (e2.renameTo(b2)) {
                bb bbVar = this.a;
                bbVar.getClass();
                this.d.a().execute(di.a(bbVar));
                this.c.a(dhVar.k, dhVar.a, dhVar.b);
                this.e.a(dhVar.k);
                this.b.a().a(dhVar.j, dhVar.k);
                return;
            }
            throw new by("Cannot move metadata files to final location.", dhVar.j);
        }
        throw new by("Cannot move merged pack files to final location.", dhVar.j);
    }
}
