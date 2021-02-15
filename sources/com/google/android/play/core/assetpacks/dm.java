package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.ci;

public final class dm implements ci<dl> {
    private final ci<Context> a;

    public dm(ci<Context> ciVar) {
        this.a = ciVar;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        return new dl(((r) this.a).a());
    }
}
