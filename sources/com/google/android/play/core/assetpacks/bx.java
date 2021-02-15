package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.cg;
import com.google.android.play.core.internal.ci;

public final class bx implements ci<bw> {
    private final ci<bb> a;
    private final ci<w> b;
    private final ci<aw> c;
    private final ci<cb> d;

    public bx(ci<bb> ciVar, ci<w> ciVar2, ci<aw> ciVar3, ci<cb> ciVar4) {
        this.a = ciVar;
        this.b = ciVar2;
        this.c = ciVar3;
        this.d = ciVar4;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        return new bw(this.a.a(), cg.b(this.b), cg.b(this.c), this.d.a());
    }
}
