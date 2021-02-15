package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.ce;
import com.google.android.play.core.internal.cg;
import com.google.android.play.core.internal.ci;
import com.google.android.play.core.splitinstall.z;
import java.util.concurrent.Executor;

public final class j implements ci<i> {
    private final ci<bb> a;
    private final ci<w> b;
    private final ci<aw> c;
    private final ci<z> d;
    private final ci<cr> e;
    private final ci<cb> f;
    private final ci<bq> g;
    private final ci<Executor> h;

    public j(ci<bb> ciVar, ci<w> ciVar2, ci<aw> ciVar3, ci<z> ciVar4, ci<cr> ciVar5, ci<cb> ciVar6, ci<bq> ciVar7, ci<Executor> ciVar8) {
        this.a = ciVar;
        this.b = ciVar2;
        this.c = ciVar3;
        this.d = ciVar4;
        this.e = ciVar5;
        this.f = ciVar6;
        this.g = ciVar7;
        this.h = ciVar8;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        bb a2 = this.a.a();
        ce<w> b2 = cg.b(this.b);
        aw a3 = this.c.a();
        return new i(a2, b2, a3, this.d.a(), this.e.a(), this.f.a(), this.g.a(), cg.b(this.h));
    }
}
