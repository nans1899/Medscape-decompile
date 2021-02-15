package com.google.android.play.core.assetpacks;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.internal.aa;
import com.google.android.play.core.internal.ce;
import com.google.android.play.core.listener.b;
import java.util.ArrayList;
import java.util.concurrent.Executor;

final class aw extends b<AssetPackState> {
    private final cr c;
    private final bz d;
    private final ce<w> e;
    private final bq f;
    private final cb g;
    private final ce<Executor> h;
    private final ce<Executor> i;
    private final Handler j = new Handler(Looper.getMainLooper());

    aw(Context context, cr crVar, bz bzVar, ce<w> ceVar, cb cbVar, bq bqVar, ce<Executor> ceVar2, ce<Executor> ceVar3) {
        super(new aa("AssetPackServiceListenerRegistry"), new IntentFilter("com.google.android.play.core.assetpacks.receiver.ACTION_SESSION_UPDATE"), context);
        this.c = crVar;
        this.d = bzVar;
        this.e = ceVar;
        this.g = cbVar;
        this.f = bqVar;
        this.h = ceVar2;
        this.i = ceVar3;
    }

    /* access modifiers changed from: protected */
    public final void a(Context context, Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("com.google.android.play.core.assetpacks.receiver.EXTRA_SESSION_STATE");
        if (bundleExtra != null) {
            ArrayList<String> stringArrayList = bundleExtra.getStringArrayList("pack_names");
            if (stringArrayList == null || stringArrayList.size() != 1) {
                this.a.b("Corrupt bundle received from broadcast.", new Object[0]);
                return;
            }
            AssetPackState a = AssetPackState.a(bundleExtra, stringArrayList.get(0), this.g, ay.a);
            this.a.a("ListenerRegistryBroadcastReceiver.onReceive: %s", a);
            PendingIntent pendingIntent = (PendingIntent) bundleExtra.getParcelable("confirmation_intent");
            if (pendingIntent != null) {
                this.f.a(pendingIntent);
            }
            this.i.a().execute(new au(this, bundleExtra, a));
            this.h.a().execute(new av(this, bundleExtra));
            return;
        }
        this.a.b("Empty bundle received from broadcast.", new Object[0]);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(Bundle bundle) {
        if (this.c.a(bundle)) {
            this.d.a();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(Bundle bundle, AssetPackState assetPackState) {
        if (this.c.b(bundle)) {
            a(assetPackState);
            this.e.a().b();
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(AssetPackState assetPackState) {
        this.j.post(new at(this, assetPackState));
    }
}
