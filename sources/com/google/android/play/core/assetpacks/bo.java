package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.cg;
import com.google.android.play.core.internal.ci;

public final class bo implements ci<bn> {
    private final ci<w> a;

    public bo(ci<w> ciVar) {
        this.a = ciVar;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        return new bn(cg.b(this.a));
    }
}
