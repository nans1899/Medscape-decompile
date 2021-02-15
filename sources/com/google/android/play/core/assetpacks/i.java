package com.google.android.play.core.assetpacks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.facebook.internal.NativeProtocol;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;
import com.google.android.play.core.internal.aa;
import com.google.android.play.core.internal.ce;
import com.google.android.play.core.internal.h;
import com.google.android.play.core.splitinstall.z;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

final class i implements AssetPackManager {
    private static final aa a = new aa("AssetPackManager");
    private final bb b;
    private final ce<w> c;
    private final aw d;
    private final z e;
    private final cr f;
    private final cb g;
    /* access modifiers changed from: private */
    public final bq h;
    private final ce<Executor> i;
    private final Handler j = new Handler(Looper.getMainLooper());
    private boolean k;

    i(bb bbVar, ce<w> ceVar, aw awVar, z zVar, cr crVar, cb cbVar, bq bqVar, ce<Executor> ceVar2) {
        this.b = bbVar;
        this.c = ceVar;
        this.d = awVar;
        this.e = zVar;
        this.f = crVar;
        this.g = cbVar;
        this.h = bqVar;
        this.i = ceVar2;
    }

    private final void b() {
        bb bbVar = this.b;
        bbVar.getClass();
        this.c.a().a().addOnSuccessListener(this.i.a(), e.a(bbVar)).addOnFailureListener(this.i.a(), f.a);
    }

    private final void c() {
        this.i.a().execute(new g(this));
        this.k = true;
    }

    /* access modifiers changed from: package-private */
    public final int a(int i2, String str) {
        if (!this.b.a(str) && i2 == 4) {
            return 8;
        }
        if (!this.b.a(str) || i2 == 4) {
            return i2;
        }
        return 4;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a() {
        this.b.d();
        this.b.c();
        this.b.e();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void a(String str, com.google.android.play.core.tasks.i iVar) {
        if (this.b.d(str)) {
            iVar.a(null);
            this.c.a().a(str);
            return;
        }
        iVar.a((Exception) new IOException(String.format("Failed to remove pack %s.", new Object[]{str})));
    }

    /* access modifiers changed from: package-private */
    public final void a(boolean z) {
        boolean b2 = this.d.b();
        this.d.a(z);
        if (z && !b2) {
            b();
        }
    }

    public final AssetPackStates cancel(List<String> list) {
        Map<String, Integer> a2 = this.f.a(list);
        HashMap hashMap = new HashMap();
        for (String next : list) {
            Integer num = a2.get(next);
            hashMap.put(next, AssetPackState.a(next, num != null ? num.intValue() : 0, 0, 0, 0, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
        }
        this.c.a().a(list);
        return AssetPackStates.a(0, (Map<String, AssetPackState>) hashMap);
    }

    public final void clearListeners() {
        this.d.a();
    }

    public final Task<AssetPackStates> fetch(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String next : list) {
            if (!this.b.a(next)) {
                arrayList.add(next);
            }
        }
        if (arrayList.isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putInt("session_id", 0);
            bundle.putInt(NativeProtocol.BRIDGE_ARG_ERROR_CODE, 0);
            for (String next2 : list) {
                bundle.putInt(h.a("status", next2), 4);
                bundle.putInt(h.a(NativeProtocol.BRIDGE_ARG_ERROR_CODE, next2), 0);
                bundle.putLong(h.a("total_bytes_to_download", next2), 0);
                bundle.putLong(h.a("bytes_downloaded", next2), 0);
            }
            bundle.putStringArrayList("pack_names", new ArrayList(list));
            bundle.putLong("total_bytes_to_download", 0);
            bundle.putLong("bytes_downloaded", 0);
            return Tasks.a(AssetPackStates.a(bundle, this.g));
        }
        ArrayList arrayList2 = new ArrayList(list);
        arrayList2.removeAll(arrayList);
        return this.c.a().a((List<String>) arrayList2, (List<String>) arrayList);
    }

    public final AssetLocation getAssetLocation(String str, String str2) {
        AssetPackLocation assetPackLocation;
        if (!this.k) {
            this.i.a().execute(new g(this));
            this.k = true;
        }
        if (this.b.a(str)) {
            try {
                assetPackLocation = this.b.b(str);
            } catch (IOException unused) {
            }
        } else {
            if (this.e.a().contains(str)) {
                assetPackLocation = AssetPackLocation.a();
            }
            assetPackLocation = null;
        }
        if (assetPackLocation != null) {
            if (assetPackLocation.packStorageMethod() == 1) {
                return this.b.a(str, str2);
            }
            if (assetPackLocation.packStorageMethod() == 0) {
                return this.b.a(str, str2, assetPackLocation);
            }
            a.a("The asset %s is not present in Asset Pack %s", str2, str);
        }
        return null;
    }

    public final AssetPackLocation getPackLocation(String str) {
        if (!this.k) {
            c();
        }
        if (this.b.a(str)) {
            try {
                return this.b.b(str);
            } catch (IOException unused) {
                return null;
            }
        } else if (this.e.a().contains(str)) {
            return AssetPackLocation.a();
        } else {
            return null;
        }
    }

    public final Map<String, AssetPackLocation> getPackLocations() {
        Map<String, AssetPackLocation> b2 = this.b.b();
        HashMap hashMap = new HashMap();
        for (String put : this.e.a()) {
            hashMap.put(put, AssetPackLocation.a());
        }
        b2.putAll(hashMap);
        return b2;
    }

    public final Task<AssetPackStates> getPackStates(List<String> list) {
        return this.c.a().a(list, (az) new c(this));
    }

    public final synchronized void registerListener(AssetPackStateUpdateListener assetPackStateUpdateListener) {
        boolean b2 = this.d.b();
        this.d.a(assetPackStateUpdateListener);
        if (!b2) {
            b();
        }
    }

    public final Task<Void> removePack(String str) {
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.i.a().execute(new d(this, str, iVar));
        return iVar.a();
    }

    public final Task<Integer> showCellularDataConfirmation(Activity activity) {
        if (this.h.a() == null) {
            return Tasks.a((Exception) new AssetPackException(-12));
        }
        Intent intent = new Intent(activity, PlayCoreDialogWrapperActivity.class);
        intent.putExtra("confirmation_intent", this.h.a());
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        intent.putExtra("result_receiver", new h(this, this.j, iVar));
        activity.startActivity(intent);
        return iVar.a();
    }

    public final void unregisterListener(AssetPackStateUpdateListener assetPackStateUpdateListener) {
        this.d.b(assetPackStateUpdateListener);
    }
}
