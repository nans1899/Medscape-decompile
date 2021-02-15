package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.play.core.tasks.i;

final class ap extends ak<AssetPackStates> {
    private final cb c;
    private final az d;

    ap(ar arVar, i<AssetPackStates> iVar, cb cbVar, az azVar) {
        super(arVar, iVar);
        this.c = cbVar;
        this.d = azVar;
    }

    public final void c(Bundle bundle, Bundle bundle2) {
        super.c(bundle, bundle2);
        this.a.b(AssetPackStates.a(bundle, this.c, this.d));
    }
}
