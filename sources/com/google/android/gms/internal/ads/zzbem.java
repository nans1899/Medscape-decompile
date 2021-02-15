package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.common.util.IOUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbem implements zzom {
    private boolean isOpen;
    private Uri uri;
    private InputStream zzekd;
    private final zzom zzeke;
    private final zzpa<zzom> zzekf;
    private final zzbep zzekg;
    private final Context zzvr;

    public zzbem(Context context, zzom zzom, zzpa<zzom> zzpa, zzbep zzbep) {
        this.zzvr = context;
        this.zzeke = zzom;
        this.zzekf = zzpa;
        this.zzekg = zzbep;
    }

    public final void close() throws IOException {
        if (this.isOpen) {
            this.isOpen = false;
            this.uri = null;
            InputStream inputStream = this.zzekd;
            if (inputStream != null) {
                IOUtils.closeQuietly((Closeable) inputStream);
                this.zzekd = null;
            } else {
                this.zzeke.close();
            }
            zzpa<zzom> zzpa = this.zzekf;
            if (zzpa != null) {
                zzpa.zze(this);
                return;
            }
            return;
        }
        throw new IOException("Attempt to close an already closed CacheDataSource.");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x009d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r6.cancel(true);
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r6.cancel(true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00d0, code lost:
        r6 = com.google.android.gms.ads.internal.zzp.zzky().elapsedRealtime() - r11;
        r1.zzekg.zzb(false, r6);
        r4 = new java.lang.StringBuilder(44);
        r4.append("Cache connection took ");
        r4.append(r6);
        r4.append("ms");
        com.google.android.gms.internal.ads.zzayp.zzei(r4.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00f4, code lost:
        r4 = com.google.android.gms.ads.internal.zzp.zzky().elapsedRealtime() - r11;
        r1.zzekg.zzb(false, r4);
        r6 = new java.lang.StringBuilder(44);
        r6.append("Cache connection took ");
        r6.append(r4);
        r6.append("ms");
        com.google.android.gms.internal.ads.zzayp.zzei(r6.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0117, code lost:
        throw r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:18:0x009f, B:21:0x00cd] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x009f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x00cd */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:21:0x00cd=Splitter:B:21:0x00cd, B:18:0x009f=Splitter:B:18:0x009f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long zza(com.google.android.gms.internal.ads.zzon r21) throws java.io.IOException {
        /*
            r20 = this;
            r1 = r20
            r0 = r21
            java.lang.String r2 = "ms"
            java.lang.String r3 = "Cache connection took "
            boolean r4 = r1.isOpen
            if (r4 != 0) goto L_0x015e
            r4 = 1
            r1.isOpen = r4
            android.net.Uri r5 = r0.uri
            r1.uri = r5
            com.google.android.gms.internal.ads.zzpa<com.google.android.gms.internal.ads.zzom> r5 = r1.zzekf
            if (r5 == 0) goto L_0x001a
            r5.zza(r1, r0)
        L_0x001a:
            android.net.Uri r5 = r0.uri
            com.google.android.gms.internal.ads.zzte r5 = com.google.android.gms.internal.ads.zzte.zzd(r5)
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r6 = com.google.android.gms.internal.ads.zzabb.zzctd
            com.google.android.gms.internal.ads.zzaax r7 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r6 = r7.zzd(r6)
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            r7 = -1
            if (r6 == 0) goto L_0x0118
            if (r5 == 0) goto L_0x0136
            long r9 = r0.position
            r5.zzbvk = r9
            boolean r6 = r5.zzbvj
            if (r6 == 0) goto L_0x004b
            com.google.android.gms.internal.ads.zzaaq<java.lang.Long> r6 = com.google.android.gms.internal.ads.zzabb.zzctf
            com.google.android.gms.internal.ads.zzaax r9 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r6 = r9.zzd(r6)
            java.lang.Long r6 = (java.lang.Long) r6
            goto L_0x0057
        L_0x004b:
            com.google.android.gms.internal.ads.zzaaq<java.lang.Long> r6 = com.google.android.gms.internal.ads.zzabb.zzcte
            com.google.android.gms.internal.ads.zzaax r9 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r6 = r9.zzd(r6)
            java.lang.Long r6 = (java.lang.Long) r6
        L_0x0057:
            long r9 = r6.longValue()
            com.google.android.gms.common.util.Clock r6 = com.google.android.gms.ads.internal.zzp.zzky()
            long r11 = r6.elapsedRealtime()
            com.google.android.gms.ads.internal.zzp.zzll()
            android.content.Context r6 = r1.zzvr
            java.util.concurrent.Future r6 = com.google.android.gms.internal.ads.zztp.zza(r6, r5)
            r13 = 0
            r14 = 44
            java.util.concurrent.TimeUnit r15 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ ExecutionException | TimeoutException -> 0x00cd, InterruptedException -> 0x009f }
            java.lang.Object r9 = r6.get(r9, r15)     // Catch:{ ExecutionException | TimeoutException -> 0x00cd, InterruptedException -> 0x009f }
            java.io.InputStream r9 = (java.io.InputStream) r9     // Catch:{ ExecutionException | TimeoutException -> 0x00cd, InterruptedException -> 0x009f }
            r1.zzekd = r9     // Catch:{ ExecutionException | TimeoutException -> 0x00cd, InterruptedException -> 0x009f }
            com.google.android.gms.common.util.Clock r0 = com.google.android.gms.ads.internal.zzp.zzky()
            long r5 = r0.elapsedRealtime()
            long r5 = r5 - r11
            com.google.android.gms.internal.ads.zzbep r0 = r1.zzekg
            r0.zzb(r4, r5)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r14)
            r0.append(r3)
            r0.append(r5)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.google.android.gms.internal.ads.zzayp.zzei(r0)
            return r7
        L_0x009d:
            r0 = move-exception
            goto L_0x00f4
        L_0x009f:
            r6.cancel(r4)     // Catch:{ all -> 0x009d }
            java.lang.Thread r4 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x009d }
            r4.interrupt()     // Catch:{ all -> 0x009d }
            com.google.android.gms.common.util.Clock r4 = com.google.android.gms.ads.internal.zzp.zzky()
            long r6 = r4.elapsedRealtime()
            long r6 = r6 - r11
            com.google.android.gms.internal.ads.zzbep r4 = r1.zzekg
            r4.zzb(r13, r6)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r14)
            r4.append(r3)
            r4.append(r6)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            com.google.android.gms.internal.ads.zzayp.zzei(r2)
            goto L_0x0136
        L_0x00cd:
            r6.cancel(r4)     // Catch:{ all -> 0x009d }
            com.google.android.gms.common.util.Clock r4 = com.google.android.gms.ads.internal.zzp.zzky()
            long r6 = r4.elapsedRealtime()
            long r6 = r6 - r11
            com.google.android.gms.internal.ads.zzbep r4 = r1.zzekg
            r4.zzb(r13, r6)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r14)
            r4.append(r3)
            r4.append(r6)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            com.google.android.gms.internal.ads.zzayp.zzei(r2)
            goto L_0x0136
        L_0x00f4:
            com.google.android.gms.common.util.Clock r4 = com.google.android.gms.ads.internal.zzp.zzky()
            long r4 = r4.elapsedRealtime()
            long r4 = r4 - r11
            com.google.android.gms.internal.ads.zzbep r6 = r1.zzekg
            r6.zzb(r13, r4)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r14)
            r6.append(r3)
            r6.append(r4)
            r6.append(r2)
            java.lang.String r2 = r6.toString()
            com.google.android.gms.internal.ads.zzayp.zzei(r2)
            throw r0
        L_0x0118:
            r2 = 0
            if (r5 == 0) goto L_0x0127
            long r2 = r0.position
            r5.zzbvk = r2
            com.google.android.gms.internal.ads.zzsw r2 = com.google.android.gms.ads.internal.zzp.zzkx()
            com.google.android.gms.internal.ads.zzsz r2 = r2.zza((com.google.android.gms.internal.ads.zzte) r5)
        L_0x0127:
            if (r2 == 0) goto L_0x0136
            boolean r3 = r2.zzmw()
            if (r3 == 0) goto L_0x0136
            java.io.InputStream r0 = r2.zzmx()
            r1.zzekd = r0
            return r7
        L_0x0136:
            if (r5 == 0) goto L_0x0157
            com.google.android.gms.internal.ads.zzon r2 = new com.google.android.gms.internal.ads.zzon
            java.lang.String r3 = r5.url
            android.net.Uri r10 = android.net.Uri.parse(r3)
            byte[] r11 = r0.zzbic
            long r12 = r0.zzbid
            long r14 = r0.position
            long r3 = r0.zzcp
            java.lang.String r5 = r0.zzcn
            int r0 = r0.flags
            r9 = r2
            r16 = r3
            r18 = r5
            r19 = r0
            r9.<init>(r10, r11, r12, r14, r16, r18, r19)
            r0 = r2
        L_0x0157:
            com.google.android.gms.internal.ads.zzom r2 = r1.zzeke
            long r2 = r2.zza(r0)
            return r2
        L_0x015e:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r2 = "Attempt to open an already open CacheDataSource."
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbem.zza(com.google.android.gms.internal.ads.zzon):long");
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (this.isOpen) {
            InputStream inputStream = this.zzekd;
            if (inputStream != null) {
                i3 = inputStream.read(bArr, i, i2);
            } else {
                i3 = this.zzeke.read(bArr, i, i2);
            }
            zzpa<zzom> zzpa = this.zzekf;
            if (zzpa != null) {
                zzpa.zzc(this, i3);
            }
            return i3;
        }
        throw new IOException("Attempt to read closed CacheDataSource.");
    }

    public final Uri getUri() {
        return this.uri;
    }
}
