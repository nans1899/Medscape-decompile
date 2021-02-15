package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.bl;
import com.google.android.play.core.internal.ci;

public final class r implements ci<Context> {
    private final m a;

    public r(m mVar) {
        this.a = mVar;
    }

    public static Context a(m mVar) {
        Context a2 = mVar.a();
        bl.a(a2, "Cannot return null from a non-@Nullable @Provides method");
        return a2;
    }

    /* renamed from: b */
    public final Context a() {
        return a(this.a);
    }
}
