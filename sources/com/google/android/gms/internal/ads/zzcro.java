package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcro implements zzdrp<zzcrn, zzcrq> {
    private final String zzdrm;
    private final zzatt zzglb;
    private final String zzglr;
    private final int zzgls;
    private final Context zzvr;

    public zzcro(Context context, String str, zzatt zzatt, String str2, int i) {
        this.zzvr = context;
        this.zzglr = str;
        this.zzglb = zzatt;
        this.zzdrm = str2;
        this.zzgls = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        r2 = new java.io.InputStreamReader(r6.getInputStream());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        com.google.android.gms.ads.internal.zzp.zzkr();
        r0 = com.google.android.gms.internal.ads.zzayu.zza(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r2);
        r7.zzez(r0);
        r5.zzglt = r11;
        r5.zzdsl = r0;
        r5.zzam = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01b5, code lost:
        if (android.text.TextUtils.isEmpty(r0) == false) goto L_0x01d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01c7, code lost:
        if (((java.lang.Boolean) com.google.android.gms.internal.ads.zzwm.zzpx().zzd(com.google.android.gms.internal.ads.zzabb.zzcuy)).booleanValue() == false) goto L_0x01ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01d1, code lost:
        throw new com.google.android.gms.internal.ads.zzcof(com.google.android.gms.internal.ads.zzdpg.NO_FILL);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01d2, code lost:
        r5.zzglu = com.google.android.gms.ads.internal.zzp.zzky().elapsedRealtime() - r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:?, code lost:
        r6.disconnect();
        r1.zzglb.zzvp();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01e5, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01e6, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01e7, code lost:
        r7 = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzcrq zza(java.lang.String r21, com.google.android.gms.internal.ads.zzati r22, org.json.JSONObject r23, java.lang.String r24) throws com.google.android.gms.internal.ads.zzcof {
        /*
            r20 = this;
            r1 = r20
            java.lang.String r0 = "Received error HTTP response code: "
            java.lang.String r2 = "doritos_v2"
            java.lang.String r3 = "doritos"
            java.lang.String r4 = ""
            int r5 = r22.getErrorCode()     // Catch:{ IOException -> 0x0280 }
            r6 = -2
            r7 = 1
            if (r5 == r6) goto L_0x003d
            int r0 = r22.getErrorCode()     // Catch:{ IOException -> 0x0280 }
            if (r0 != r7) goto L_0x0035
            java.util.List r0 = r22.zzvh()     // Catch:{ IOException -> 0x0280 }
            if (r0 == 0) goto L_0x002b
            java.lang.String r0 = ", "
            java.util.List r2 = r22.zzvh()     // Catch:{ IOException -> 0x0280 }
            java.lang.String r0 = android.text.TextUtils.join(r0, r2)     // Catch:{ IOException -> 0x0280 }
            com.google.android.gms.internal.ads.zzayp.zzfc(r0)     // Catch:{ IOException -> 0x0280 }
        L_0x002b:
            com.google.android.gms.internal.ads.zzcof r0 = new com.google.android.gms.internal.ads.zzcof     // Catch:{ IOException -> 0x0280 }
            com.google.android.gms.internal.ads.zzdpg r2 = com.google.android.gms.internal.ads.zzdpg.INVALID_REQUEST     // Catch:{ IOException -> 0x0280 }
            java.lang.String r3 = "Error building request URL."
            r0.<init>(r2, r3)     // Catch:{ IOException -> 0x0280 }
            throw r0     // Catch:{ IOException -> 0x0280 }
        L_0x0035:
            com.google.android.gms.internal.ads.zzcof r0 = new com.google.android.gms.internal.ads.zzcof     // Catch:{ IOException -> 0x0280 }
            com.google.android.gms.internal.ads.zzdpg r2 = com.google.android.gms.internal.ads.zzdpg.INTERNAL_ERROR     // Catch:{ IOException -> 0x0280 }
            r0.<init>(r2)     // Catch:{ IOException -> 0x0280 }
            throw r0     // Catch:{ IOException -> 0x0280 }
        L_0x003d:
            com.google.android.gms.internal.ads.zzcrq r5 = new com.google.android.gms.internal.ads.zzcrq     // Catch:{ IOException -> 0x0280 }
            r5.<init>()     // Catch:{ IOException -> 0x0280 }
            java.lang.String r6 = "SDK version: "
            java.lang.String r8 = r1.zzglr     // Catch:{ IOException -> 0x0280 }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ IOException -> 0x0280 }
            int r9 = r8.length()     // Catch:{ IOException -> 0x0280 }
            if (r9 == 0) goto L_0x0055
            java.lang.String r6 = r6.concat(r8)     // Catch:{ IOException -> 0x0280 }
            goto L_0x005b
        L_0x0055:
            java.lang.String r8 = new java.lang.String     // Catch:{ IOException -> 0x0280 }
            r8.<init>(r6)     // Catch:{ IOException -> 0x0280 }
            r6 = r8
        L_0x005b:
            com.google.android.gms.internal.ads.zzayp.zzfd(r6)     // Catch:{ IOException -> 0x0280 }
            java.lang.String r6 = "AdRequestServiceImpl: Sending request: "
            java.lang.String r8 = java.lang.String.valueOf(r21)     // Catch:{ IOException -> 0x0280 }
            int r9 = r8.length()     // Catch:{ IOException -> 0x0280 }
            if (r9 == 0) goto L_0x006f
            java.lang.String r6 = r6.concat(r8)     // Catch:{ IOException -> 0x0280 }
            goto L_0x0075
        L_0x006f:
            java.lang.String r8 = new java.lang.String     // Catch:{ IOException -> 0x0280 }
            r8.<init>(r6)     // Catch:{ IOException -> 0x0280 }
            r6 = r8
        L_0x0075:
            com.google.android.gms.internal.ads.zzayp.zzef(r6)     // Catch:{ IOException -> 0x0280 }
            java.net.URL r6 = new java.net.URL     // Catch:{ IOException -> 0x0280 }
            r8 = r21
            r6.<init>(r8)     // Catch:{ IOException -> 0x0280 }
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ IOException -> 0x0280 }
            r8.<init>()     // Catch:{ IOException -> 0x0280 }
            com.google.android.gms.common.util.Clock r9 = com.google.android.gms.ads.internal.zzp.zzky()     // Catch:{ IOException -> 0x0280 }
            long r9 = r9.elapsedRealtime()     // Catch:{ IOException -> 0x0280 }
            r11 = 0
            r12 = 0
        L_0x008e:
            com.google.android.gms.internal.ads.zzatt r13 = r1.zzglb     // Catch:{ IOException -> 0x0280 }
            int r14 = r1.zzgls     // Catch:{ IOException -> 0x0280 }
            r13.zzde(r14)     // Catch:{ IOException -> 0x0280 }
            java.net.URLConnection r6 = r6.openConnection()     // Catch:{ IOException -> 0x0280 }
            java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6     // Catch:{ IOException -> 0x0280 }
            com.google.android.gms.internal.ads.zzayu r13 = com.google.android.gms.ads.internal.zzp.zzkr()     // Catch:{ all -> 0x0276 }
            android.content.Context r14 = r1.zzvr     // Catch:{ all -> 0x0276 }
            java.lang.String r15 = r1.zzglr     // Catch:{ all -> 0x0276 }
            r13.zza((android.content.Context) r14, (java.lang.String) r15, (boolean) r11, (java.net.HttpURLConnection) r6)     // Catch:{ all -> 0x0276 }
            boolean r13 = android.text.TextUtils.isEmpty(r24)     // Catch:{ all -> 0x0276 }
            if (r13 != 0) goto L_0x00b4
            java.lang.String r13 = "Cookie"
            r14 = r24
            r6.addRequestProperty(r13, r14)     // Catch:{ all -> 0x0276 }
            goto L_0x00b6
        L_0x00b4:
            r14 = r24
        L_0x00b6:
            boolean r13 = r22.zzvk()     // Catch:{ all -> 0x0276 }
            if (r13 == 0) goto L_0x00f3
            java.lang.String r13 = "pii"
            r15 = r23
            org.json.JSONObject r13 = r15.optJSONObject(r13)     // Catch:{ all -> 0x0276 }
            if (r13 == 0) goto L_0x00ed
            java.lang.String r16 = r13.optString(r3, r4)     // Catch:{ all -> 0x0276 }
            boolean r16 = android.text.TextUtils.isEmpty(r16)     // Catch:{ all -> 0x0276 }
            if (r16 != 0) goto L_0x00d9
            java.lang.String r11 = "x-afma-drt-cookie"
            java.lang.String r7 = r13.optString(r3, r4)     // Catch:{ all -> 0x0276 }
            r6.addRequestProperty(r11, r7)     // Catch:{ all -> 0x0276 }
        L_0x00d9:
            java.lang.String r7 = r13.optString(r2, r4)     // Catch:{ all -> 0x0276 }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x0276 }
            if (r7 != 0) goto L_0x00f5
            java.lang.String r7 = "x-afma-drt-v2-cookie"
            java.lang.String r11 = r13.optString(r2, r4)     // Catch:{ all -> 0x0276 }
            r6.addRequestProperty(r7, r11)     // Catch:{ all -> 0x0276 }
            goto L_0x00f5
        L_0x00ed:
            java.lang.String r7 = "DSID signal does not exist."
            com.google.android.gms.internal.ads.zzayp.zzei(r7)     // Catch:{ all -> 0x0276 }
            goto L_0x00f5
        L_0x00f3:
            r15 = r23
        L_0x00f5:
            if (r22 == 0) goto L_0x012a
            java.lang.String r11 = r22.zzvj()     // Catch:{ all -> 0x0276 }
            boolean r11 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x0276 }
            if (r11 != 0) goto L_0x012a
            r11 = 1
            r6.setDoOutput(r11)     // Catch:{ all -> 0x0276 }
            java.lang.String r11 = r22.zzvj()     // Catch:{ all -> 0x0276 }
            byte[] r11 = r11.getBytes()     // Catch:{ all -> 0x0276 }
            int r13 = r11.length     // Catch:{ all -> 0x0276 }
            r6.setFixedLengthStreamingMode(r13)     // Catch:{ all -> 0x0276 }
            java.io.BufferedOutputStream r13 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0124 }
            java.io.OutputStream r7 = r6.getOutputStream()     // Catch:{ all -> 0x0124 }
            r13.<init>(r7)     // Catch:{ all -> 0x0124 }
            r13.write(r11)     // Catch:{ all -> 0x0121 }
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r13)     // Catch:{ all -> 0x0276 }
            goto L_0x012b
        L_0x0121:
            r0 = move-exception
            r7 = r13
            goto L_0x0126
        L_0x0124:
            r0 = move-exception
            r7 = 0
        L_0x0126:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r7)     // Catch:{ all -> 0x0276 }
            throw r0     // Catch:{ all -> 0x0276 }
        L_0x012a:
            r11 = 0
        L_0x012b:
            com.google.android.gms.internal.ads.zzbbk r7 = new com.google.android.gms.internal.ads.zzbbk     // Catch:{ all -> 0x0276 }
            r7.<init>()     // Catch:{ all -> 0x0276 }
            r7.zza((java.net.HttpURLConnection) r6, (byte[]) r11)     // Catch:{ all -> 0x0276 }
            int r11 = r6.getResponseCode()     // Catch:{ all -> 0x0276 }
            java.util.Map r13 = r6.getHeaderFields()     // Catch:{ all -> 0x0276 }
            java.util.Set r13 = r13.entrySet()     // Catch:{ all -> 0x0276 }
            java.util.Iterator r13 = r13.iterator()     // Catch:{ all -> 0x0276 }
        L_0x0143:
            boolean r17 = r13.hasNext()     // Catch:{ all -> 0x0276 }
            if (r17 == 0) goto L_0x0184
            java.lang.Object r17 = r13.next()     // Catch:{ all -> 0x0276 }
            java.util.Map$Entry r17 = (java.util.Map.Entry) r17     // Catch:{ all -> 0x0276 }
            java.lang.Object r18 = r17.getKey()     // Catch:{ all -> 0x0276 }
            r19 = r2
            r2 = r18
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0276 }
            java.lang.Object r17 = r17.getValue()     // Catch:{ all -> 0x0276 }
            r18 = r3
            r3 = r17
            java.util.List r3 = (java.util.List) r3     // Catch:{ all -> 0x0276 }
            boolean r17 = r8.containsKey(r2)     // Catch:{ all -> 0x0276 }
            if (r17 == 0) goto L_0x0173
            java.lang.Object r2 = r8.get(r2)     // Catch:{ all -> 0x0276 }
            java.util.List r2 = (java.util.List) r2     // Catch:{ all -> 0x0276 }
            r2.addAll(r3)     // Catch:{ all -> 0x0276 }
            goto L_0x017f
        L_0x0173:
            r17 = r4
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x0276 }
            r4.<init>(r3)     // Catch:{ all -> 0x0276 }
            r8.put(r2, r4)     // Catch:{ all -> 0x0276 }
            r4 = r17
        L_0x017f:
            r3 = r18
            r2 = r19
            goto L_0x0143
        L_0x0184:
            r19 = r2
            r18 = r3
            r17 = r4
            r7.zza((java.net.HttpURLConnection) r6, (int) r11)     // Catch:{ all -> 0x0276 }
            r2 = 200(0xc8, float:2.8E-43)
            r3 = 300(0x12c, float:4.2E-43)
            if (r11 < r2) goto L_0x01ef
            if (r11 >= r3) goto L_0x01ef
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ all -> 0x01e9 }
            java.io.InputStream r0 = r6.getInputStream()     // Catch:{ all -> 0x01e9 }
            r2.<init>(r0)     // Catch:{ all -> 0x01e9 }
            com.google.android.gms.ads.internal.zzp.zzkr()     // Catch:{ all -> 0x01e6 }
            java.lang.String r0 = com.google.android.gms.internal.ads.zzayu.zza((java.io.InputStreamReader) r2)     // Catch:{ all -> 0x01e6 }
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r2)     // Catch:{ all -> 0x0276 }
            r7.zzez(r0)     // Catch:{ all -> 0x0276 }
            r5.zzglt = r11     // Catch:{ all -> 0x0276 }
            r5.zzdsl = r0     // Catch:{ all -> 0x0276 }
            r5.zzam = r8     // Catch:{ all -> 0x0276 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0276 }
            if (r0 == 0) goto L_0x01d2
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzabb.zzcuy     // Catch:{ all -> 0x0276 }
            com.google.android.gms.internal.ads.zzaax r2 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x0276 }
            java.lang.Object r0 = r2.zzd(r0)     // Catch:{ all -> 0x0276 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0276 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0276 }
            if (r0 == 0) goto L_0x01ca
            goto L_0x01d2
        L_0x01ca:
            com.google.android.gms.internal.ads.zzcof r0 = new com.google.android.gms.internal.ads.zzcof     // Catch:{ all -> 0x0276 }
            com.google.android.gms.internal.ads.zzdpg r2 = com.google.android.gms.internal.ads.zzdpg.NO_FILL     // Catch:{ all -> 0x0276 }
            r0.<init>(r2)     // Catch:{ all -> 0x0276 }
            throw r0     // Catch:{ all -> 0x0276 }
        L_0x01d2:
            com.google.android.gms.common.util.Clock r0 = com.google.android.gms.ads.internal.zzp.zzky()     // Catch:{ all -> 0x0276 }
            long r2 = r0.elapsedRealtime()     // Catch:{ all -> 0x0276 }
            long r2 = r2 - r9
            r5.zzglu = r2     // Catch:{ all -> 0x0276 }
            r6.disconnect()     // Catch:{ IOException -> 0x0280 }
            com.google.android.gms.internal.ads.zzatt r0 = r1.zzglb     // Catch:{ IOException -> 0x0280 }
            r0.zzvp()     // Catch:{ IOException -> 0x0280 }
            return r5
        L_0x01e6:
            r0 = move-exception
            r7 = r2
            goto L_0x01eb
        L_0x01e9:
            r0 = move-exception
            r7 = 0
        L_0x01eb:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r7)     // Catch:{ all -> 0x0276 }
            throw r0     // Catch:{ all -> 0x0276 }
        L_0x01ef:
            if (r11 < r3) goto L_0x024b
            r2 = 400(0x190, float:5.6E-43)
            if (r11 >= r2) goto L_0x024b
            java.lang.String r2 = "Location"
            java.lang.String r2 = r6.getHeaderField(r2)     // Catch:{ all -> 0x0276 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0276 }
            if (r3 != 0) goto L_0x023c
            java.net.URL r3 = new java.net.URL     // Catch:{ all -> 0x0276 }
            r3.<init>(r2)     // Catch:{ all -> 0x0276 }
            r2 = 1
            int r12 = r12 + r2
            com.google.android.gms.internal.ads.zzaaq<java.lang.Integer> r4 = com.google.android.gms.internal.ads.zzabb.zzcub     // Catch:{ all -> 0x0276 }
            com.google.android.gms.internal.ads.zzaax r7 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x0276 }
            java.lang.Object r4 = r7.zzd(r4)     // Catch:{ all -> 0x0276 }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ all -> 0x0276 }
            int r4 = r4.intValue()     // Catch:{ all -> 0x0276 }
            if (r12 > r4) goto L_0x022d
            r6.disconnect()     // Catch:{ IOException -> 0x0280 }
            com.google.android.gms.internal.ads.zzatt r4 = r1.zzglb     // Catch:{ IOException -> 0x0280 }
            r4.zzvp()     // Catch:{ IOException -> 0x0280 }
            r6 = r3
            r4 = r17
            r3 = r18
            r2 = r19
            r7 = 1
            r11 = 0
            goto L_0x008e
        L_0x022d:
            java.lang.String r0 = "Too many redirects."
            com.google.android.gms.internal.ads.zzayp.zzfe(r0)     // Catch:{ all -> 0x0276 }
            com.google.android.gms.internal.ads.zzcof r0 = new com.google.android.gms.internal.ads.zzcof     // Catch:{ all -> 0x0276 }
            com.google.android.gms.internal.ads.zzdpg r2 = com.google.android.gms.internal.ads.zzdpg.INTERNAL_ERROR     // Catch:{ all -> 0x0276 }
            java.lang.String r3 = "Too many redirects"
            r0.<init>(r2, r3)     // Catch:{ all -> 0x0276 }
            throw r0     // Catch:{ all -> 0x0276 }
        L_0x023c:
            java.lang.String r0 = "No location header to follow redirect."
            com.google.android.gms.internal.ads.zzayp.zzfe(r0)     // Catch:{ all -> 0x0276 }
            com.google.android.gms.internal.ads.zzcof r0 = new com.google.android.gms.internal.ads.zzcof     // Catch:{ all -> 0x0276 }
            com.google.android.gms.internal.ads.zzdpg r2 = com.google.android.gms.internal.ads.zzdpg.INTERNAL_ERROR     // Catch:{ all -> 0x0276 }
            java.lang.String r3 = "No location header to follow redirect"
            r0.<init>(r2, r3)     // Catch:{ all -> 0x0276 }
            throw r0     // Catch:{ all -> 0x0276 }
        L_0x024b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0276 }
            r3 = 46
            r2.<init>(r3)     // Catch:{ all -> 0x0276 }
            r2.append(r0)     // Catch:{ all -> 0x0276 }
            r2.append(r11)     // Catch:{ all -> 0x0276 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0276 }
            com.google.android.gms.internal.ads.zzayp.zzfe(r2)     // Catch:{ all -> 0x0276 }
            com.google.android.gms.internal.ads.zzcof r2 = new com.google.android.gms.internal.ads.zzcof     // Catch:{ all -> 0x0276 }
            com.google.android.gms.internal.ads.zzdpg r4 = com.google.android.gms.internal.ads.zzdpg.INTERNAL_ERROR     // Catch:{ all -> 0x0276 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0276 }
            r5.<init>(r3)     // Catch:{ all -> 0x0276 }
            r5.append(r0)     // Catch:{ all -> 0x0276 }
            r5.append(r11)     // Catch:{ all -> 0x0276 }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x0276 }
            r2.<init>(r4, r0)     // Catch:{ all -> 0x0276 }
            throw r2     // Catch:{ all -> 0x0276 }
        L_0x0276:
            r0 = move-exception
            r6.disconnect()     // Catch:{ IOException -> 0x0280 }
            com.google.android.gms.internal.ads.zzatt r2 = r1.zzglb     // Catch:{ IOException -> 0x0280 }
            r2.zzvp()     // Catch:{ IOException -> 0x0280 }
            throw r0     // Catch:{ IOException -> 0x0280 }
        L_0x0280:
            r0 = move-exception
            java.lang.String r2 = "Error while connecting to ad server: "
            java.lang.String r3 = r0.getMessage()
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r4 = r3.length()
            if (r4 == 0) goto L_0x0296
            java.lang.String r2 = r2.concat(r3)
            goto L_0x029c
        L_0x0296:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r2)
            r2 = r3
        L_0x029c:
            com.google.android.gms.internal.ads.zzayp.zzfe(r2)
            com.google.android.gms.internal.ads.zzcof r3 = new com.google.android.gms.internal.ads.zzcof
            com.google.android.gms.internal.ads.zzdpg r4 = com.google.android.gms.internal.ads.zzdpg.INTERNAL_ERROR
            r3.<init>(r4, r2, r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcro.zza(java.lang.String, com.google.android.gms.internal.ads.zzati, org.json.JSONObject, java.lang.String):com.google.android.gms.internal.ads.zzcrq");
    }

    public final /* synthetic */ Object apply(Object obj) throws Exception {
        zzcrn zzcrn = (zzcrn) obj;
        return zza(zzcrn.zzgll.getUrl(), zzcrn.zzgll, zzcrn.zzglk, this.zzdrm);
    }
}
