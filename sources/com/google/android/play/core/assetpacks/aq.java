package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.play.core.tasks.i;
import java.util.List;

final class aq extends ak<AssetPackStates> {
    private final List<String> c;
    private final cb d;

    aq(ar arVar, i<AssetPackStates> iVar, cb cbVar, List<String> list) {
        super(arVar, iVar);
        this.d = cbVar;
        this.c = list;
    }

    public final void a(int i, Bundle bundle) {
        super.a(i, bundle);
        this.a.b(AssetPackStates.a(bundle, this.d, this.c));
    }
}
