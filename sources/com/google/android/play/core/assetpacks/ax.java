package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.cg;
import com.google.android.play.core.internal.ci;
import java.util.concurrent.Executor;

public final class ax implements ci<aw> {
    private final ci<Context> a;
    private final ci<cr> b;
    private final ci<bz> c;
    private final ci<w> d;
    private final ci<cb> e;
    private final ci<bq> f;
    private final ci<Executor> g;
    private final ci<Executor> h;

    public ax(ci<Context> ciVar, ci<cr> ciVar2, ci<bz> ciVar3, ci<w> ciVar4, ci<cb> ciVar5, ci<bq> ciVar6, ci<Executor> ciVar7, ci<Executor> ciVar8) {
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
        return new aw(((r) this.a).a(), this.b.a(), this.c.a(), cg.b(this.d), this.e.a(), this.f.a(), cg.b(this.g), cg.b(this.h));
    }
}
