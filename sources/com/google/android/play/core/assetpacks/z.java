package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.OnSuccessListener;

final /* synthetic */ class z implements OnSuccessListener {
    private final ar a;

    z(ar arVar) {
        this.a = arVar;
    }

    public final void onSuccess(Object obj) {
        AssetPackStates assetPackStates = (AssetPackStates) obj;
        this.a.b();
    }
}
