package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.ci;

public final class as implements ci<ar> {
    private final ci<Context> a;
    private final ci<cb> b;

    public as(ci<Context> ciVar, ci<cb> ciVar2) {
        this.a = ciVar;
        this.b = ciVar2;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        return new ar(((r) this.a).a(), this.b.a());
    }
}
