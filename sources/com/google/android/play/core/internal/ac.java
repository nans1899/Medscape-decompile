package com.google.android.play.core.internal;

import android.os.IBinder;

final /* synthetic */ class ac implements IBinder.DeathRecipient {
    private final ak a;

    ac(ak akVar) {
        this.a = akVar;
    }

    public final void binderDied() {
        this.a.c();
    }
}
