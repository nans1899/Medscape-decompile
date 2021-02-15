package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.cg;
import com.google.android.play.core.internal.ci;
import java.util.concurrent.Executor;

public final class cs implements ci<cr> {
    private final ci<bb> a;
    private final ci<w> b;
    private final ci<cb> c;
    private final ci<Executor> d;

    public cs(ci<bb> ciVar, ci<w> ciVar2, ci<cb> ciVar3, ci<Executor> ciVar4) {
        this.a = ciVar;
        this.b = ciVar2;
        this.c = ciVar3;
        this.d = ciVar4;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        bb a2 = this.a.a();
        return new cr(a2, cg.b(this.b), this.c.a(), cg.b(this.d));
    }
}
