package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.cg;
import com.google.android.play.core.internal.ci;

public final class dp implements ci<Cdo> {
    private final ci<bb> a;
    private final ci<w> b;

    public dp(ci<bb> ciVar, ci<w> ciVar2) {
        this.a = ciVar;
        this.b = ciVar2;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        return new Cdo(this.a.a(), cg.b(this.b));
    }
}
