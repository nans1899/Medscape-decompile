package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.OnSuccessListener;
import java.util.List;

final /* synthetic */ class e implements OnSuccessListener {
    private final bb a;

    private e(bb bbVar) {
        this.a = bbVar;
    }

    static OnSuccessListener a(bb bbVar) {
        return new e(bbVar);
    }

    public final void onSuccess(Object obj) {
        this.a.a((List<String>) (List) obj);
    }
}
