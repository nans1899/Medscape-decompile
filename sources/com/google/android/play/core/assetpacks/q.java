package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.bl;
import com.google.android.play.core.internal.cg;
import com.google.android.play.core.internal.ci;

public final class q implements ci<w> {
    private final ci<Context> a;
    private final ci<ar> b;
    private final ci<db> c;

    public q(ci<Context> ciVar, ci<ar> ciVar2, ci<db> ciVar3) {
        this.a = ciVar;
        this.b = ciVar2;
        this.c = ciVar3;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        w wVar = (w) (m.a(((r) this.a).a()) == null ? cg.b(this.b).a() : cg.b(this.c).a());
        bl.a(wVar, "Cannot return null from a non-@Nullable @Provides method");
        return wVar;
    }
}
