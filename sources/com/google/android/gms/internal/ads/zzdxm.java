package com.google.android.gms.internal.ads;

import java.lang.Throwable;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
abstract class zzdxm<V, X extends Throwable, F, T> extends zzdyk<V> implements Runnable {
    @NullableDecl
    private zzdzc<? extends V> zzhrl;
    @NullableDecl
    private Class<X> zzhrm;
    @NullableDecl
    private F zzhrn;

    static <X extends Throwable, V> zzdzc<V> zza(zzdzc<? extends V> zzdzc, Class<X> cls, zzdya<? super X, ? extends V> zzdya, Executor executor) {
        zzdxp zzdxp = new zzdxp(zzdzc, cls, zzdya);
        zzdzc.addListener(zzdxp, zzdze.zza(executor, zzdxp));
        return zzdxp;
    }

    /* access modifiers changed from: package-private */
    public abstract void setResult(@NullableDecl T t);

    /* access modifiers changed from: package-private */
    @NullableDecl
    public abstract T zza(F f, X x) throws Exception;

    zzdxm(zzdzc<? extends V> zzdzc, Class<X> cls, F f) {
        this.zzhrl = (zzdzc) zzdwd.checkNotNull(zzdzc);
        this.zzhrm = (Class) zzdwd.checkNotNull(cls);
        this.zzhrn = zzdwd.checkNotNull(f);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0091  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r9 = this;
            com.google.android.gms.internal.ads.zzdzc<? extends V> r0 = r9.zzhrl
            java.lang.Class<X> r1 = r9.zzhrm
            F r2 = r9.zzhrn
            r3 = 1
            r4 = 0
            if (r0 != 0) goto L_0x000c
            r5 = 1
            goto L_0x000d
        L_0x000c:
            r5 = 0
        L_0x000d:
            if (r1 != 0) goto L_0x0011
            r6 = 1
            goto L_0x0012
        L_0x0011:
            r6 = 0
        L_0x0012:
            r5 = r5 | r6
            if (r2 != 0) goto L_0x0016
            goto L_0x0017
        L_0x0016:
            r3 = 0
        L_0x0017:
            r3 = r3 | r5
            if (r3 != 0) goto L_0x00b6
            boolean r3 = r9.isCancelled()
            if (r3 == 0) goto L_0x0022
            goto L_0x00b6
        L_0x0022:
            r3 = 0
            r9.zzhrl = r3
            boolean r4 = r0 instanceof com.google.android.gms.internal.ads.zzdzt     // Catch:{ ExecutionException -> 0x003c, all -> 0x0039 }
            if (r4 == 0) goto L_0x0031
            r4 = r0
            com.google.android.gms.internal.ads.zzdzt r4 = (com.google.android.gms.internal.ads.zzdzt) r4     // Catch:{ ExecutionException -> 0x003c, all -> 0x0039 }
            java.lang.Throwable r4 = com.google.android.gms.internal.ads.zzdzw.zza(r4)     // Catch:{ ExecutionException -> 0x003c, all -> 0x0039 }
            goto L_0x0032
        L_0x0031:
            r4 = r3
        L_0x0032:
            if (r4 != 0) goto L_0x003a
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzdyq.zza(r0)     // Catch:{ ExecutionException -> 0x003c, all -> 0x0039 }
            goto L_0x008b
        L_0x0039:
            r4 = move-exception
        L_0x003a:
            r5 = r3
            goto L_0x008b
        L_0x003c:
            r4 = move-exception
            java.lang.Throwable r5 = r4.getCause()
            if (r5 != 0) goto L_0x0089
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.Class r6 = r0.getClass()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.String r7 = java.lang.String.valueOf(r6)
            int r7 = r7.length()
            int r7 = r7 + 35
            java.lang.String r8 = java.lang.String.valueOf(r4)
            int r8 = r8.length()
            int r7 = r7 + r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r7)
            java.lang.String r7 = "Future type "
            r8.append(r7)
            r8.append(r6)
            java.lang.String r6 = " threw "
            r8.append(r6)
            r8.append(r4)
            java.lang.String r4 = " without a cause"
            r8.append(r4)
            java.lang.String r4 = r8.toString()
            r5.<init>(r4)
        L_0x0089:
            r4 = r5
            goto L_0x003a
        L_0x008b:
            if (r4 != 0) goto L_0x0091
            r9.set(r5)
            return
        L_0x0091:
            boolean r1 = r1.isInstance(r4)
            if (r1 != 0) goto L_0x009b
            r9.setFuture(r0)
            return
        L_0x009b:
            java.lang.Object r0 = r9.zza(r2, r4)     // Catch:{ all -> 0x00a7 }
            r9.zzhrm = r3
            r9.zzhrn = r3
            r9.setResult(r0)
            return
        L_0x00a7:
            r0 = move-exception
            r9.setException(r0)     // Catch:{ all -> 0x00b0 }
            r9.zzhrm = r3
            r9.zzhrn = r3
            return
        L_0x00b0:
            r0 = move-exception
            r9.zzhrm = r3
            r9.zzhrn = r3
            throw r0
        L_0x00b6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdxm.run():void");
    }

    /* access modifiers changed from: protected */
    public final String pendingToString() {
        String str;
        zzdzc<? extends V> zzdzc = this.zzhrl;
        Class<X> cls = this.zzhrm;
        F f = this.zzhrn;
        String pendingToString = super.pendingToString();
        if (zzdzc != null) {
            String valueOf = String.valueOf(zzdzc);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16);
            sb.append("inputFuture=[");
            sb.append(valueOf);
            sb.append("], ");
            str = sb.toString();
        } else {
            str = "";
        }
        if (cls != null && f != null) {
            String valueOf2 = String.valueOf(cls);
            String valueOf3 = String.valueOf(f);
            StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 29 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
            sb2.append(str);
            sb2.append("exceptionType=[");
            sb2.append(valueOf2);
            sb2.append("], fallback=[");
            sb2.append(valueOf3);
            sb2.append("]");
            return sb2.toString();
        } else if (pendingToString == null) {
            return null;
        } else {
            String valueOf4 = String.valueOf(str);
            String valueOf5 = String.valueOf(pendingToString);
            return valueOf5.length() != 0 ? valueOf4.concat(valueOf5) : new String(valueOf4);
        }
    }

    /* access modifiers changed from: protected */
    public final void afterDone() {
        maybePropagateCancellationTo(this.zzhrl);
        this.zzhrl = null;
        this.zzhrm = null;
        this.zzhrn = null;
    }
}
