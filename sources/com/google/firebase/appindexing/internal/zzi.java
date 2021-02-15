package com.google.firebase.appindexing.internal;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.firebase.appindexing.FirebaseAppIndex;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzi extends FirebaseAppIndex {
    private static String[] zzez = {"com.google.android.googlequicksearchbox", "com.google.android.gms"};
    private final Context zzcs;
    private final GoogleApi<?> zzfa;
    private final zzk zzfb;

    public zzi(Context context) {
        this(context, new zzh(context));
    }

    private zzi(Context context, GoogleApi<Api.ApiOptions.NoOptions> googleApi) {
        this.zzfa = googleApi;
        this.zzcs = context;
        this.zzfb = new zzk(googleApi);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0066 A[Catch:{ ArrayStoreException -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0068 A[Catch:{ ArrayStoreException -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0075 A[Catch:{ ArrayStoreException -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0095 A[Catch:{ ArrayStoreException -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0097 A[Catch:{ ArrayStoreException -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00a4 A[Catch:{ ArrayStoreException -> 0x00eb }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.tasks.Task<java.lang.Void> update(com.google.firebase.appindexing.Indexable... r14) {
        /*
            r13 = this;
            r0 = 0
            r1 = 0
            if (r14 != 0) goto L_0x0006
            r2 = r0
            goto L_0x000d
        L_0x0006:
            int r2 = r14.length     // Catch:{ ArrayStoreException -> 0x00eb }
            com.google.firebase.appindexing.internal.Thing[] r2 = new com.google.firebase.appindexing.internal.Thing[r2]     // Catch:{ ArrayStoreException -> 0x00eb }
            int r3 = r14.length     // Catch:{ ArrayStoreException -> 0x00eb }
            java.lang.System.arraycopy(r14, r1, r2, r1, r3)     // Catch:{ ArrayStoreException -> 0x00eb }
        L_0x000d:
            boolean r14 = com.google.android.gms.internal.icing.zzhl.zzeb()     // Catch:{ ArrayStoreException -> 0x00eb }
            r3 = 1
            if (r14 == 0) goto L_0x00e1
            boolean r14 = com.google.android.gms.common.util.PlatformVersion.isAtLeastKitKat()     // Catch:{ ArrayStoreException -> 0x00eb }
            if (r14 == 0) goto L_0x00e1
            android.content.Context r14 = r13.zzcs     // Catch:{ ArrayStoreException -> 0x00eb }
            if (r14 == 0) goto L_0x00e1
            if (r2 == 0) goto L_0x00e1
            int r14 = r2.length     // Catch:{ ArrayStoreException -> 0x00eb }
            if (r14 <= 0) goto L_0x00e1
            android.content.Context r14 = r13.zzcs     // Catch:{ ArrayStoreException -> 0x00eb }
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ ArrayStoreException -> 0x00eb }
            r5 = 28
            if (r4 < r5) goto L_0x0031
            com.google.firebase.appindexing.internal.zzab r4 = new com.google.firebase.appindexing.internal.zzab     // Catch:{ ArrayStoreException -> 0x00eb }
            r4.<init>(r14)     // Catch:{ ArrayStoreException -> 0x00eb }
            goto L_0x0036
        L_0x0031:
            com.google.firebase.appindexing.internal.zzz r4 = new com.google.firebase.appindexing.internal.zzz     // Catch:{ ArrayStoreException -> 0x00eb }
            r4.<init>(r14)     // Catch:{ ArrayStoreException -> 0x00eb }
        L_0x0036:
            int r14 = r2.length     // Catch:{ ArrayStoreException -> 0x00eb }
            r5 = 0
        L_0x0038:
            if (r5 >= r14) goto L_0x00e1
            r6 = r2[r5]     // Catch:{ ArrayStoreException -> 0x00eb }
            if (r6 == 0) goto L_0x00dd
            com.google.firebase.appindexing.internal.Thing$zza r7 = r6.zzac()     // Catch:{ ArrayStoreException -> 0x00eb }
            java.lang.String r8 = "sliceUri"
            android.os.Bundle r9 = r7.zze()     // Catch:{ ArrayStoreException -> 0x00eb }
            if (r9 == 0) goto L_0x0060
            android.os.Bundle r9 = r7.zze()     // Catch:{ ArrayStoreException -> 0x00eb }
            java.lang.Object r9 = r9.get(r8)     // Catch:{ ArrayStoreException -> 0x00eb }
            boolean r9 = r9 instanceof java.lang.String[]     // Catch:{ ArrayStoreException -> 0x00eb }
            if (r9 != 0) goto L_0x0057
            goto L_0x0060
        L_0x0057:
            android.os.Bundle r7 = r7.zze()     // Catch:{ ArrayStoreException -> 0x00eb }
            java.lang.String[] r7 = r7.getStringArray(r8)     // Catch:{ ArrayStoreException -> 0x00eb }
            goto L_0x0061
        L_0x0060:
            r7 = r0
        L_0x0061:
            if (r7 == 0) goto L_0x0068
            int r8 = r7.length     // Catch:{ ArrayStoreException -> 0x00eb }
            if (r8 <= 0) goto L_0x0068
            r8 = 1
            goto L_0x0069
        L_0x0068:
            r8 = 0
        L_0x0069:
            com.google.firebase.appindexing.internal.Thing$zza r6 = r6.zzac()     // Catch:{ ArrayStoreException -> 0x00eb }
            java.lang.String r9 = "grantSlicePermission"
            android.os.Bundle r10 = r6.zze()     // Catch:{ ArrayStoreException -> 0x00eb }
            if (r10 == 0) goto L_0x008b
            android.os.Bundle r10 = r6.zze()     // Catch:{ ArrayStoreException -> 0x00eb }
            java.lang.Object r10 = r10.get(r9)     // Catch:{ ArrayStoreException -> 0x00eb }
            boolean r10 = r10 instanceof boolean[]     // Catch:{ ArrayStoreException -> 0x00eb }
            if (r10 != 0) goto L_0x0082
            goto L_0x008b
        L_0x0082:
            android.os.Bundle r6 = r6.zze()     // Catch:{ ArrayStoreException -> 0x00eb }
            boolean[] r6 = r6.getBooleanArray(r9)     // Catch:{ ArrayStoreException -> 0x00eb }
            goto L_0x008c
        L_0x008b:
            r6 = r0
        L_0x008c:
            if (r6 == 0) goto L_0x0097
            int r9 = r6.length     // Catch:{ ArrayStoreException -> 0x00eb }
            if (r9 <= 0) goto L_0x0097
            boolean r6 = r6[r1]     // Catch:{ ArrayStoreException -> 0x00eb }
            if (r6 == 0) goto L_0x0097
            r6 = 1
            goto L_0x0098
        L_0x0097:
            r6 = 0
        L_0x0098:
            if (r8 == 0) goto L_0x00dd
            if (r6 == 0) goto L_0x00dd
            r6 = r7[r1]     // Catch:{ ArrayStoreException -> 0x00eb }
            java.lang.String[] r7 = zzez     // Catch:{ ArrayStoreException -> 0x00eb }
            int r8 = r7.length     // Catch:{ ArrayStoreException -> 0x00eb }
            r9 = 0
        L_0x00a2:
            if (r9 >= r8) goto L_0x00dd
            r10 = r7[r9]     // Catch:{ ArrayStoreException -> 0x00eb }
            android.net.Uri r11 = android.net.Uri.parse(r6)     // Catch:{ Exception -> 0x00ae }
            r4.grantSlicePermission(r10, r11)     // Catch:{ Exception -> 0x00ae }
            goto L_0x00da
        L_0x00ae:
            r10 = move-exception
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ ArrayStoreException -> 0x00eb }
            java.lang.String r11 = java.lang.String.valueOf(r10)     // Catch:{ ArrayStoreException -> 0x00eb }
            int r11 = r11.length()     // Catch:{ ArrayStoreException -> 0x00eb }
            int r11 = r11 + 48
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ ArrayStoreException -> 0x00eb }
            r12.<init>(r11)     // Catch:{ ArrayStoreException -> 0x00eb }
            java.lang.String r11 = "Error trying to grant permission to Slice Uris: "
            r12.append(r11)     // Catch:{ ArrayStoreException -> 0x00eb }
            r12.append(r10)     // Catch:{ ArrayStoreException -> 0x00eb }
            java.lang.String r10 = r12.toString()     // Catch:{ ArrayStoreException -> 0x00eb }
            r11 = 5
            boolean r11 = com.google.firebase.appindexing.internal.zzt.isLoggable(r11)     // Catch:{ ArrayStoreException -> 0x00eb }
            if (r11 == 0) goto L_0x00da
            java.lang.String r11 = "FirebaseAppIndex"
            android.util.Log.w(r11, r10)     // Catch:{ ArrayStoreException -> 0x00eb }
        L_0x00da:
            int r9 = r9 + 1
            goto L_0x00a2
        L_0x00dd:
            int r5 = r5 + 1
            goto L_0x0038
        L_0x00e1:
            com.google.firebase.appindexing.internal.zzy r14 = new com.google.firebase.appindexing.internal.zzy
            r14.<init>(r3, r2)
            com.google.android.gms.tasks.Task r14 = r13.zza(r14)
            return r14
        L_0x00eb:
            com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException r14 = new com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException
            java.lang.String r0 = "Custom Indexable-objects are not allowed. Please use the 'Indexables'-class for creating the objects."
            r14.<init>(r0)
            com.google.android.gms.tasks.Task r14 = com.google.android.gms.tasks.Tasks.forException(r14)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.appindexing.internal.zzi.update(com.google.firebase.appindexing.Indexable[]):com.google.android.gms.tasks.Task");
    }

    public final Task<Void> remove(String... strArr) {
        return zza(new zzy(3, (Thing[]) null, strArr, (String[]) null, (zza) null, (String) null, (String) null));
    }

    public final Task<Void> removeAll() {
        return zza(new zzy(4, (Thing[]) null, (String[]) null, (String[]) null, (zza) null, (String) null, (String) null));
    }

    private final Task<Void> zza(zzy zzy) {
        return this.zzfb.zzb(zzy);
    }
}
