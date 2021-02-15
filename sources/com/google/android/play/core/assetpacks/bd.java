package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.ci;

public final class bd implements ci<bb> {
    private final ci<Context> a;
    private final ci<dl> b;

    public bd(ci<Context> ciVar, ci<dl> ciVar2) {
        this.a = ciVar;
        this.b = ciVar2;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        return new bb(((r) this.a).a(), this.b.a());
    }
}
