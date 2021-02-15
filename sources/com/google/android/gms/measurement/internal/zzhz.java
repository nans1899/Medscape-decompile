package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzhz implements Application.ActivityLifecycleCallbacks {
    private final /* synthetic */ zzgy zza;

    private zzhz(zzgy zzgy) {
        this.zza = zzgy;
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        try {
            this.zza.zzq().zzw().zza("onActivityCreated");
            Intent intent = activity.getIntent();
            if (intent != null) {
                Uri data = intent.getData();
                if (data != null) {
                    if (data.isHierarchical()) {
                        this.zza.zzo();
                        this.zza.zzp().zza((Runnable) new zzhy(this, bundle == null, data, zzkw.zza(intent) ? "gs" : DebugKt.DEBUG_PROPERTY_VALUE_AUTO, data.getQueryParameter("referrer")));
                        this.zza.zzh().zza(activity, bundle);
                        return;
                    }
                }
                this.zza.zzh().zza(activity, bundle);
            }
        } catch (Exception e) {
            this.zza.zzq().zze().zza("Throwable caught in onActivityCreated", e);
        } finally {
            this.zza.zzh().zza(activity, bundle);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a1 A[SYNTHETIC, Splitter:B:33:0x00a1] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00fe A[Catch:{ Exception -> 0x01d6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x010f A[SYNTHETIC, Splitter:B:51:0x010f] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x013e A[Catch:{ Exception -> 0x01d6 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x013f A[Catch:{ Exception -> 0x01d6 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(boolean r18, android.net.Uri r19, java.lang.String r20, java.lang.String r21) {
        /*
            r17 = this;
            r1 = r17
            r0 = r20
            r2 = r21
            com.google.android.gms.measurement.internal.zzgy r3 = r1.zza
            r3.zzc()
            com.google.android.gms.measurement.internal.zzgy r3 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzy r3 = r3.zzs()     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzat.zzbd     // Catch:{ Exception -> 0x01d6 }
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r4)     // Catch:{ Exception -> 0x01d6 }
            java.lang.String r4 = "Activity created with data 'referrer' without required params"
            java.lang.String r5 = "utm_medium"
            java.lang.String r6 = "_cis"
            java.lang.String r7 = "utm_source"
            java.lang.String r8 = "utm_campaign"
            java.lang.String r10 = "gclid"
            if (r3 != 0) goto L_0x0044
            com.google.android.gms.measurement.internal.zzgy r3 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzy r3 = r3.zzs()     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzat.zzbf     // Catch:{ Exception -> 0x01d6 }
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r11)     // Catch:{ Exception -> 0x01d6 }
            if (r3 != 0) goto L_0x0044
            com.google.android.gms.measurement.internal.zzgy r3 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzy r3 = r3.zzs()     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzat.zzbe     // Catch:{ Exception -> 0x01d6 }
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r11)     // Catch:{ Exception -> 0x01d6 }
            if (r3 == 0) goto L_0x0042
            goto L_0x0044
        L_0x0042:
            r3 = 0
            goto L_0x009b
        L_0x0044:
            com.google.android.gms.measurement.internal.zzgy r3 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzkw r3 = r3.zzo()     // Catch:{ Exception -> 0x01d6 }
            boolean r11 = android.text.TextUtils.isEmpty(r21)     // Catch:{ Exception -> 0x01d6 }
            if (r11 == 0) goto L_0x0051
            goto L_0x0042
        L_0x0051:
            boolean r11 = r2.contains(r10)     // Catch:{ Exception -> 0x01d6 }
            if (r11 != 0) goto L_0x0075
            boolean r11 = r2.contains(r8)     // Catch:{ Exception -> 0x01d6 }
            if (r11 != 0) goto L_0x0075
            boolean r11 = r2.contains(r7)     // Catch:{ Exception -> 0x01d6 }
            if (r11 != 0) goto L_0x0075
            boolean r11 = r2.contains(r5)     // Catch:{ Exception -> 0x01d6 }
            if (r11 != 0) goto L_0x0075
            com.google.android.gms.measurement.internal.zzer r3 = r3.zzq()     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzet r3 = r3.zzv()     // Catch:{ Exception -> 0x01d6 }
            r3.zza(r4)     // Catch:{ Exception -> 0x01d6 }
            goto L_0x0042
        L_0x0075:
            java.lang.String r11 = "https://google.com/search?"
            java.lang.String r12 = java.lang.String.valueOf(r21)     // Catch:{ Exception -> 0x01d6 }
            int r13 = r12.length()     // Catch:{ Exception -> 0x01d6 }
            if (r13 == 0) goto L_0x0086
            java.lang.String r11 = r11.concat(r12)     // Catch:{ Exception -> 0x01d6 }
            goto L_0x008c
        L_0x0086:
            java.lang.String r12 = new java.lang.String     // Catch:{ Exception -> 0x01d6 }
            r12.<init>(r11)     // Catch:{ Exception -> 0x01d6 }
            r11 = r12
        L_0x008c:
            android.net.Uri r11 = android.net.Uri.parse(r11)     // Catch:{ Exception -> 0x01d6 }
            android.os.Bundle r3 = r3.zza((android.net.Uri) r11)     // Catch:{ Exception -> 0x01d6 }
            if (r3 == 0) goto L_0x009b
            java.lang.String r11 = "referrer"
            r3.putString(r6, r11)     // Catch:{ Exception -> 0x01d6 }
        L_0x009b:
            r11 = 0
            java.lang.String r12 = "_cmp"
            r13 = 1
            if (r18 == 0) goto L_0x00fe
            com.google.android.gms.measurement.internal.zzgy r14 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzkw r14 = r14.zzo()     // Catch:{ Exception -> 0x01d6 }
            r15 = r19
            android.os.Bundle r14 = r14.zza((android.net.Uri) r15)     // Catch:{ Exception -> 0x01d6 }
            if (r14 == 0) goto L_0x00ff
            java.lang.String r15 = "intent"
            r14.putString(r6, r15)     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzgy r6 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzy r6 = r6.zzs()     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r15 = com.google.android.gms.measurement.internal.zzat.zzbd     // Catch:{ Exception -> 0x01d6 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r15)     // Catch:{ Exception -> 0x01d6 }
            if (r6 == 0) goto L_0x00e3
            boolean r6 = r14.containsKey(r10)     // Catch:{ Exception -> 0x01d6 }
            if (r6 != 0) goto L_0x00e3
            if (r3 == 0) goto L_0x00e3
            boolean r6 = r3.containsKey(r10)     // Catch:{ Exception -> 0x01d6 }
            if (r6 == 0) goto L_0x00e3
            java.lang.String r6 = "_cer"
            java.lang.String r15 = "gclid=%s"
            java.lang.Object[] r9 = new java.lang.Object[r13]     // Catch:{ Exception -> 0x01d6 }
            java.lang.String r16 = r3.getString(r10)     // Catch:{ Exception -> 0x01d6 }
            r9[r11] = r16     // Catch:{ Exception -> 0x01d6 }
            java.lang.String r9 = java.lang.String.format(r15, r9)     // Catch:{ Exception -> 0x01d6 }
            r14.putString(r6, r9)     // Catch:{ Exception -> 0x01d6 }
        L_0x00e3:
            com.google.android.gms.measurement.internal.zzgy r6 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            r6.zza((java.lang.String) r0, (java.lang.String) r12, (android.os.Bundle) r14)     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzgy r6 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzy r6 = r6.zzs()     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzat.zzcc     // Catch:{ Exception -> 0x01d6 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r9)     // Catch:{ Exception -> 0x01d6 }
            if (r6 == 0) goto L_0x00ff
            com.google.android.gms.measurement.internal.zzgy r6 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzp r6 = r6.zzb     // Catch:{ Exception -> 0x01d6 }
            r6.zza(r0, r14)     // Catch:{ Exception -> 0x01d6 }
            goto L_0x00ff
        L_0x00fe:
            r14 = 0
        L_0x00ff:
            com.google.android.gms.measurement.internal.zzgy r6 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzy r6 = r6.zzs()     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzat.zzbf     // Catch:{ Exception -> 0x01d6 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r9)     // Catch:{ Exception -> 0x01d6 }
            java.lang.String r9 = "auto"
            if (r6 == 0) goto L_0x0138
            com.google.android.gms.measurement.internal.zzgy r6 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzy r6 = r6.zzs()     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r15 = com.google.android.gms.measurement.internal.zzat.zzbe     // Catch:{ Exception -> 0x01d6 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r15)     // Catch:{ Exception -> 0x01d6 }
            if (r6 != 0) goto L_0x0138
            if (r3 == 0) goto L_0x0138
            boolean r6 = r3.containsKey(r10)     // Catch:{ Exception -> 0x01d6 }
            if (r6 == 0) goto L_0x0138
            if (r14 == 0) goto L_0x012d
            boolean r6 = r14.containsKey(r10)     // Catch:{ Exception -> 0x01d6 }
            if (r6 != 0) goto L_0x0138
        L_0x012d:
            com.google.android.gms.measurement.internal.zzgy r6 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            java.lang.String r14 = "_lgclid"
            java.lang.String r15 = r3.getString(r10)     // Catch:{ Exception -> 0x01d6 }
            r6.zza((java.lang.String) r9, (java.lang.String) r14, (java.lang.Object) r15, (boolean) r13)     // Catch:{ Exception -> 0x01d6 }
        L_0x0138:
            boolean r6 = android.text.TextUtils.isEmpty(r21)     // Catch:{ Exception -> 0x01d6 }
            if (r6 == 0) goto L_0x013f
            return
        L_0x013f:
            com.google.android.gms.measurement.internal.zzgy r6 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzer r6 = r6.zzq()     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzet r6 = r6.zzv()     // Catch:{ Exception -> 0x01d6 }
            java.lang.String r14 = "Activity created with referrer"
            r6.zza(r14, r2)     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzgy r6 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzy r6 = r6.zzs()     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r14 = com.google.android.gms.measurement.internal.zzat.zzbe     // Catch:{ Exception -> 0x01d6 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r14)     // Catch:{ Exception -> 0x01d6 }
            java.lang.String r14 = "_ldl"
            if (r6 == 0) goto L_0x0191
            if (r3 == 0) goto L_0x017b
            com.google.android.gms.measurement.internal.zzgy r2 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            r2.zza((java.lang.String) r0, (java.lang.String) r12, (android.os.Bundle) r3)     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzgy r2 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzy r2 = r2.zzs()     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzat.zzcc     // Catch:{ Exception -> 0x01d6 }
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzeg<java.lang.Boolean>) r4)     // Catch:{ Exception -> 0x01d6 }
            if (r2 == 0) goto L_0x018a
            com.google.android.gms.measurement.internal.zzgy r2 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzp r2 = r2.zzb     // Catch:{ Exception -> 0x01d6 }
            r2.zza(r0, r3)     // Catch:{ Exception -> 0x01d6 }
            goto L_0x018a
        L_0x017b:
            com.google.android.gms.measurement.internal.zzgy r0 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzer r0 = r0.zzq()     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzv()     // Catch:{ Exception -> 0x01d6 }
            java.lang.String r3 = "Referrer does not contain valid parameters"
            r0.zza(r3, r2)     // Catch:{ Exception -> 0x01d6 }
        L_0x018a:
            com.google.android.gms.measurement.internal.zzgy r0 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            r2 = 0
            r0.zza((java.lang.String) r9, (java.lang.String) r14, (java.lang.Object) r2, (boolean) r13)     // Catch:{ Exception -> 0x01d6 }
            return
        L_0x0191:
            boolean r0 = r2.contains(r10)     // Catch:{ Exception -> 0x01d6 }
            if (r0 == 0) goto L_0x01ba
            boolean r0 = r2.contains(r8)     // Catch:{ Exception -> 0x01d6 }
            if (r0 != 0) goto L_0x01b9
            boolean r0 = r2.contains(r7)     // Catch:{ Exception -> 0x01d6 }
            if (r0 != 0) goto L_0x01b9
            boolean r0 = r2.contains(r5)     // Catch:{ Exception -> 0x01d6 }
            if (r0 != 0) goto L_0x01b9
            java.lang.String r0 = "utm_term"
            boolean r0 = r2.contains(r0)     // Catch:{ Exception -> 0x01d6 }
            if (r0 != 0) goto L_0x01b9
            java.lang.String r0 = "utm_content"
            boolean r0 = r2.contains(r0)     // Catch:{ Exception -> 0x01d6 }
            if (r0 == 0) goto L_0x01ba
        L_0x01b9:
            r11 = 1
        L_0x01ba:
            if (r11 != 0) goto L_0x01ca
            com.google.android.gms.measurement.internal.zzgy r0 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzer r0 = r0.zzq()     // Catch:{ Exception -> 0x01d6 }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzv()     // Catch:{ Exception -> 0x01d6 }
            r0.zza(r4)     // Catch:{ Exception -> 0x01d6 }
            return
        L_0x01ca:
            boolean r0 = android.text.TextUtils.isEmpty(r21)     // Catch:{ Exception -> 0x01d6 }
            if (r0 != 0) goto L_0x01d5
            com.google.android.gms.measurement.internal.zzgy r0 = r1.zza     // Catch:{ Exception -> 0x01d6 }
            r0.zza((java.lang.String) r9, (java.lang.String) r14, (java.lang.Object) r2, (boolean) r13)     // Catch:{ Exception -> 0x01d6 }
        L_0x01d5:
            return
        L_0x01d6:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgy r2 = r1.zza
            com.google.android.gms.measurement.internal.zzer r2 = r2.zzq()
            com.google.android.gms.measurement.internal.zzet r2 = r2.zze()
            java.lang.String r3 = "Throwable caught in handleReferrerForOnActivityCreated"
            r2.zza(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhz.zza(boolean, android.net.Uri, java.lang.String, java.lang.String):void");
    }

    public final void onActivityDestroyed(Activity activity) {
        this.zza.zzh().zzc(activity);
    }

    public final void onActivityPaused(Activity activity) {
        this.zza.zzh().zzb(activity);
        zzju zzj = this.zza.zzj();
        zzj.zzp().zza((Runnable) new zzjw(zzj, zzj.zzl().elapsedRealtime()));
    }

    public final void onActivityResumed(Activity activity) {
        zzju zzj = this.zza.zzj();
        zzj.zzp().zza((Runnable) new zzjx(zzj, zzj.zzl().elapsedRealtime()));
        this.zza.zzh().zza(activity);
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zza.zzh().zzb(activity, bundle);
    }

    /* synthetic */ zzhz(zzgy zzgy, zzhd zzhd) {
        this(zzgy);
    }
}
