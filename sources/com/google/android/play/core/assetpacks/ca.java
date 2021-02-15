package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.cg;
import com.google.android.play.core.internal.ci;

public final class ca implements ci<bz> {
    private final ci<cr> a;
    private final ci<w> b;
    private final ci<bw> c;
    private final ci<dv> d;
    private final ci<df> e;
    private final ci<dj> f;
    private final ci<Cdo> g;
    private final ci<cu> h;

    public ca(ci<cr> ciVar, ci<w> ciVar2, ci<bw> ciVar3, ci<dv> ciVar4, ci<df> ciVar5, ci<dj> ciVar6, ci<Cdo> ciVar7, ci<cu> ciVar8) {
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
        return new bz(this.a.a(), cg.b(this.b), this.c.a(), this.d.a(), this.e.a(), this.f.a(), this.g.a(), this.h.a());
    }
}
