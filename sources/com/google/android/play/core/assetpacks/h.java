package com.google.android.play.core.assetpacks;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.google.android.play.core.tasks.i;

final class h extends ResultReceiver {
    final /* synthetic */ i a;
    final /* synthetic */ i b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    h(i iVar, Handler handler, i iVar2) {
        super(handler);
        this.b = iVar;
        this.a = iVar2;
    }

    public final void onReceiveResult(int i, Bundle bundle) {
        if (i == 1) {
            this.a.a(-1);
            this.b.h.a((PendingIntent) null);
        } else if (i != 2) {
            this.a.a((Exception) new AssetPackException(-100));
        } else {
            this.a.a(0);
        }
    }
}
