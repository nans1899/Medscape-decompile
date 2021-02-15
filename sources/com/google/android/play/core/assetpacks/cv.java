package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.ci;

public final class cv implements ci<cu> {
    private final ci<cr> a;
    private final ci<bb> b;
    private final ci<bn> c;

    public cv(ci<cr> ciVar, ci<bb> ciVar2, ci<bn> ciVar3) {
        this.a = ciVar;
        this.b = ciVar2;
        this.c = ciVar3;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        return new cu(this.a.a(), this.b.a(), this.c.a());
    }
}
