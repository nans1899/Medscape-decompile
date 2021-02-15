package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbbc {
    private Context zzaai;
    private final BroadcastReceiver zzedl = new zzbbf(this);
    private final Map<BroadcastReceiver, IntentFilter> zzedm = new WeakHashMap();
    private boolean zzedn;
    private boolean zzzh = false;

    public final synchronized void initialize(Context context) {
        if (!this.zzzh) {
            Context applicationContext = context.getApplicationContext();
            this.zzaai = applicationContext;
            if (applicationContext == null) {
                this.zzaai = context;
            }
            zzabb.initialize(this.zzaai);
            this.zzedn = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcsf)).booleanValue();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zzaai.registerReceiver(this.zzedl, intentFilter);
            this.zzzh = true;
        }
    }

    public final synchronized void zza(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (this.zzedn) {
            this.zzedm.put(broadcastReceiver, intentFilter);
        } else {
            context.registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    public final synchronized void zza(Context context, BroadcastReceiver broadcastReceiver) {
        if (this.zzedn) {
            this.zzedm.remove(broadcastReceiver);
        } else {
            context.unregisterReceiver(broadcastReceiver);
        }
    }

    /* access modifiers changed from: private */
    public final synchronized void zzc(Context context, Intent intent) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : this.zzedm.entrySet()) {
            if (((IntentFilter) next.getValue()).hasAction(intent.getAction())) {
                arrayList.add((BroadcastReceiver) next.getKey());
            }
        }
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            ((BroadcastReceiver) obj).onReceive(context, intent);
        }
    }
}
