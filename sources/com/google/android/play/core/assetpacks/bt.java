package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.bl;

public final class bt {
    private m a;

    private bt() {
    }

    /* synthetic */ bt(byte[] bArr) {
    }

    public final a a() {
        m mVar = this.a;
        if (mVar != null) {
            return new bu(mVar);
        }
        throw new IllegalStateException(String.valueOf(m.class.getCanonicalName()).concat(" must be set"));
    }

    public final void a(m mVar) {
        bl.a(mVar);
        this.a = mVar;
    }
}
