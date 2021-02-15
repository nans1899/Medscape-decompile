package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.OnFailureListener;

final /* synthetic */ class f implements OnFailureListener {
    static final OnFailureListener a = new f();

    private f() {
    }

    public final void onFailure(Exception exc) {
        i.a.d(String.format("Could not sync active asset packs. %s", new Object[]{exc}), new Object[0]);
    }
}
