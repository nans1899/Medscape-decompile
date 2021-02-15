package com.google.android.play.core.assetpacks;

import android.content.ComponentName;
import android.content.Context;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;
import com.google.android.play.core.internal.bl;
import com.google.android.play.core.internal.ci;

public final class p implements ci<AssetPackManager> {
    private final ci<i> a;
    private final ci<Context> b;

    public p(ci<i> ciVar, ci<Context> ciVar2) {
        this.a = ciVar;
        this.b = ciVar2;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        i a2 = this.a.a();
        Context b2 = ((r) this.b).a();
        i iVar = a2;
        bl.a(b2.getPackageManager(), new ComponentName(b2.getPackageName(), "com.google.android.play.core.assetpacks.AssetPackExtractionService"));
        PlayCoreDialogWrapperActivity.a(b2);
        bl.a(iVar, "Cannot return null from a non-@Nullable @Provides method");
        return iVar;
    }
}
