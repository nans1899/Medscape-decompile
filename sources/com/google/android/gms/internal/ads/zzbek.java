package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import javax.net.ssl.SSLSocketFactory;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbek implements zzom {
    private static final Pattern zzbie = Pattern.compile("^bytes (\\d+)-(\\d+)/(\\d+)$");
    private static final AtomicReference<byte[]> zzbif = new AtomicReference<>();
    private final int zzbih;
    private final int zzbii;
    private final String zzbij;
    private final zzou zzbim;
    private final zzpa<? super zzbek> zzbin;
    private zzon zzbio;
    private HttpURLConnection zzbip;
    private InputStream zzbiq;
    private boolean zzbir;
    private long zzbis;
    private long zzbit;
    private long zzbiu;
    private long zzcq;
    private SSLSocketFactory zzejz = new zzben(this);
    /* access modifiers changed from: private */
    public int zzeka;
    private Set<Socket> zzekb = new HashSet();

    zzbek(String str, zzpa<? super zzbek> zzpa, int i, int i2, int i3) {
        this.zzbij = zzoz.checkNotEmpty(str);
        this.zzbin = zzpa;
        this.zzbim = new zzou();
        this.zzbih = i;
        this.zzbii = i2;
        this.zzeka = i3;
    }

    public final Uri getUri() {
        HttpURLConnection httpURLConnection = this.zzbip;
        if (httpURLConnection == null) {
            return null;
        }
        return Uri.parse(httpURLConnection.getURL().toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:122:0x012b A[EDGE_INSN: B:122:0x012b->B:50:0x012b ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00d9 A[Catch:{ IOException -> 0x0248 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00e6 A[Catch:{ IOException -> 0x0248 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00e8 A[Catch:{ IOException -> 0x0248 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ee A[Catch:{ IOException -> 0x0248 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0121 A[Catch:{ IOException -> 0x0248 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0186  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long zza(com.google.android.gms.internal.ads.zzon r24) throws com.google.android.gms.internal.ads.zzos {
        /*
            r23 = this;
            r1 = r23
            r2 = r24
            java.lang.String r3 = "Unable to connect to "
            r1.zzbio = r2
            r4 = 0
            r1.zzcq = r4
            r1.zzbiu = r4
            r6 = 1
            java.net.URL r0 = new java.net.URL     // Catch:{ IOException -> 0x0248 }
            android.net.Uri r7 = r2.uri     // Catch:{ IOException -> 0x0248 }
            java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x0248 }
            r0.<init>(r7)     // Catch:{ IOException -> 0x0248 }
            byte[] r7 = r2.zzbic     // Catch:{ IOException -> 0x0248 }
            long r8 = r2.position     // Catch:{ IOException -> 0x0248 }
            long r10 = r2.zzcp     // Catch:{ IOException -> 0x0248 }
            boolean r12 = r2.isFlagSet(r6)     // Catch:{ IOException -> 0x0248 }
            r14 = 0
        L_0x0025:
            int r15 = r14 + 1
            r6 = 20
            if (r14 > r6) goto L_0x022f
            java.net.URLConnection r14 = r0.openConnection()     // Catch:{ IOException -> 0x0248 }
            java.net.HttpURLConnection r14 = (java.net.HttpURLConnection) r14     // Catch:{ IOException -> 0x0248 }
            boolean r13 = r14 instanceof javax.net.ssl.HttpsURLConnection     // Catch:{ IOException -> 0x0248 }
            if (r13 == 0) goto L_0x003d
            r13 = r14
            javax.net.ssl.HttpsURLConnection r13 = (javax.net.ssl.HttpsURLConnection) r13     // Catch:{ IOException -> 0x0248 }
            javax.net.ssl.SSLSocketFactory r6 = r1.zzejz     // Catch:{ IOException -> 0x0248 }
            r13.setSSLSocketFactory(r6)     // Catch:{ IOException -> 0x0248 }
        L_0x003d:
            int r6 = r1.zzbih     // Catch:{ IOException -> 0x0248 }
            r14.setConnectTimeout(r6)     // Catch:{ IOException -> 0x0248 }
            int r6 = r1.zzbii     // Catch:{ IOException -> 0x0248 }
            r14.setReadTimeout(r6)     // Catch:{ IOException -> 0x0248 }
            com.google.android.gms.internal.ads.zzou r6 = r1.zzbim     // Catch:{ IOException -> 0x0248 }
            java.util.Map r6 = r6.zzit()     // Catch:{ IOException -> 0x0248 }
            java.util.Set r6 = r6.entrySet()     // Catch:{ IOException -> 0x0248 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ IOException -> 0x0248 }
        L_0x0055:
            boolean r13 = r6.hasNext()     // Catch:{ IOException -> 0x0248 }
            if (r13 == 0) goto L_0x0075
            java.lang.Object r13 = r6.next()     // Catch:{ IOException -> 0x0248 }
            java.util.Map$Entry r13 = (java.util.Map.Entry) r13     // Catch:{ IOException -> 0x0248 }
            java.lang.Object r17 = r13.getKey()     // Catch:{ IOException -> 0x0248 }
            r4 = r17
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ IOException -> 0x0248 }
            java.lang.Object r5 = r13.getValue()     // Catch:{ IOException -> 0x0248 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ IOException -> 0x0248 }
            r14.setRequestProperty(r4, r5)     // Catch:{ IOException -> 0x0248 }
            r4 = 0
            goto L_0x0055
        L_0x0075:
            r4 = -1
            r17 = 0
            int r6 = (r8 > r17 ? 1 : (r8 == r17 ? 0 : -1))
            if (r6 != 0) goto L_0x0085
            int r6 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x0082
            goto L_0x0085
        L_0x0082:
            r16 = r8
            goto L_0x00d0
        L_0x0085:
            r6 = 27
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0248 }
            r13.<init>(r6)     // Catch:{ IOException -> 0x0248 }
            java.lang.String r6 = "bytes="
            r13.append(r6)     // Catch:{ IOException -> 0x0248 }
            r13.append(r8)     // Catch:{ IOException -> 0x0248 }
            java.lang.String r6 = "-"
            r13.append(r6)     // Catch:{ IOException -> 0x0248 }
            java.lang.String r6 = r13.toString()     // Catch:{ IOException -> 0x0248 }
            int r13 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x00c9
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ IOException -> 0x0248 }
            long r19 = r8 + r10
            r21 = 1
            long r4 = r19 - r21
            java.lang.String r13 = java.lang.String.valueOf(r6)     // Catch:{ IOException -> 0x0248 }
            int r13 = r13.length()     // Catch:{ IOException -> 0x0248 }
            r16 = 20
            int r13 = r13 + 20
            r16 = r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0248 }
            r8.<init>(r13)     // Catch:{ IOException -> 0x0248 }
            r8.append(r6)     // Catch:{ IOException -> 0x0248 }
            r8.append(r4)     // Catch:{ IOException -> 0x0248 }
            java.lang.String r6 = r8.toString()     // Catch:{ IOException -> 0x0248 }
            goto L_0x00cb
        L_0x00c9:
            r16 = r8
        L_0x00cb:
            java.lang.String r4 = "Range"
            r14.setRequestProperty(r4, r6)     // Catch:{ IOException -> 0x0248 }
        L_0x00d0:
            java.lang.String r4 = "User-Agent"
            java.lang.String r5 = r1.zzbij     // Catch:{ IOException -> 0x0248 }
            r14.setRequestProperty(r4, r5)     // Catch:{ IOException -> 0x0248 }
            if (r12 != 0) goto L_0x00e0
            java.lang.String r4 = "Accept-Encoding"
            java.lang.String r5 = "identity"
            r14.setRequestProperty(r4, r5)     // Catch:{ IOException -> 0x0248 }
        L_0x00e0:
            r4 = 0
            r14.setInstanceFollowRedirects(r4)     // Catch:{ IOException -> 0x0248 }
            if (r7 == 0) goto L_0x00e8
            r4 = 1
            goto L_0x00e9
        L_0x00e8:
            r4 = 0
        L_0x00e9:
            r14.setDoOutput(r4)     // Catch:{ IOException -> 0x0248 }
            if (r7 == 0) goto L_0x0108
            java.lang.String r4 = "POST"
            r14.setRequestMethod(r4)     // Catch:{ IOException -> 0x0248 }
            int r4 = r7.length     // Catch:{ IOException -> 0x0248 }
            if (r4 == 0) goto L_0x0108
            int r4 = r7.length     // Catch:{ IOException -> 0x0248 }
            r14.setFixedLengthStreamingMode(r4)     // Catch:{ IOException -> 0x0248 }
            r14.connect()     // Catch:{ IOException -> 0x0248 }
            java.io.OutputStream r4 = r14.getOutputStream()     // Catch:{ IOException -> 0x0248 }
            r4.write(r7)     // Catch:{ IOException -> 0x0248 }
            r4.close()     // Catch:{ IOException -> 0x0248 }
            goto L_0x010b
        L_0x0108:
            r14.connect()     // Catch:{ IOException -> 0x0248 }
        L_0x010b:
            int r4 = r14.getResponseCode()     // Catch:{ IOException -> 0x0248 }
            r5 = 300(0x12c, float:4.2E-43)
            if (r4 == r5) goto L_0x01da
            r5 = 301(0x12d, float:4.22E-43)
            if (r4 == r5) goto L_0x01da
            r5 = 302(0x12e, float:4.23E-43)
            if (r4 == r5) goto L_0x01da
            r5 = 303(0x12f, float:4.25E-43)
            if (r4 == r5) goto L_0x01da
            if (r7 != 0) goto L_0x012b
            r5 = 307(0x133, float:4.3E-43)
            if (r4 == r5) goto L_0x01da
            r5 = 308(0x134, float:4.32E-43)
            if (r4 != r5) goto L_0x012b
            goto L_0x01da
        L_0x012b:
            r1.zzbip = r14     // Catch:{ IOException -> 0x0248 }
            int r0 = r14.getResponseCode()     // Catch:{ IOException -> 0x01b3 }
            r3 = 200(0xc8, float:2.8E-43)
            if (r0 < r3) goto L_0x0197
            r4 = 299(0x12b, float:4.19E-43)
            if (r0 <= r4) goto L_0x013a
            goto L_0x0197
        L_0x013a:
            if (r0 != r3) goto L_0x0147
            long r3 = r2.position
            r5 = 0
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 == 0) goto L_0x0149
            long r4 = r2.position
            goto L_0x014a
        L_0x0147:
            r5 = 0
        L_0x0149:
            r4 = r5
        L_0x014a:
            r1.zzbis = r4
            r3 = 1
            boolean r0 = r2.isFlagSet(r3)
            if (r0 != 0) goto L_0x0173
            long r3 = r2.zzcp
            r5 = -1
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 == 0) goto L_0x0160
            long r3 = r2.zzcp
            r1.zzbit = r3
            goto L_0x0177
        L_0x0160:
            java.net.HttpURLConnection r0 = r1.zzbip
            long r3 = zzc(r0)
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 == 0) goto L_0x016f
            long r5 = r1.zzbis
            long r4 = r3 - r5
            goto L_0x0170
        L_0x016f:
            r4 = r5
        L_0x0170:
            r1.zzbit = r4
            goto L_0x0177
        L_0x0173:
            long r3 = r2.zzcp
            r1.zzbit = r3
        L_0x0177:
            java.net.HttpURLConnection r0 = r1.zzbip     // Catch:{ IOException -> 0x018c }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ IOException -> 0x018c }
            r1.zzbiq = r0     // Catch:{ IOException -> 0x018c }
            r3 = 1
            r1.zzbir = r3
            com.google.android.gms.internal.ads.zzpa<? super com.google.android.gms.internal.ads.zzbek> r0 = r1.zzbin
            if (r0 == 0) goto L_0x0189
            r0.zza(r1, r2)
        L_0x0189:
            long r2 = r1.zzbit
            return r2
        L_0x018c:
            r0 = move-exception
            r23.zziq()
            com.google.android.gms.internal.ads.zzos r3 = new com.google.android.gms.internal.ads.zzos
            r4 = 1
            r3.<init>((java.io.IOException) r0, (com.google.android.gms.internal.ads.zzon) r2, (int) r4)
            throw r3
        L_0x0197:
            java.net.HttpURLConnection r3 = r1.zzbip
            java.util.Map r3 = r3.getHeaderFields()
            r23.zziq()
            com.google.android.gms.internal.ads.zzor r4 = new com.google.android.gms.internal.ads.zzor
            r4.<init>(r0, r3, r2)
            r2 = 416(0x1a0, float:5.83E-43)
            if (r0 != r2) goto L_0x01b2
            com.google.android.gms.internal.ads.zzoo r0 = new com.google.android.gms.internal.ads.zzoo
            r7 = 0
            r0.<init>(r7)
            r4.initCause(r0)
        L_0x01b2:
            throw r4
        L_0x01b3:
            r0 = move-exception
            r4 = r0
            r23.zziq()
            com.google.android.gms.internal.ads.zzos r0 = new com.google.android.gms.internal.ads.zzos
            android.net.Uri r5 = r2.uri
            java.lang.String r5 = r5.toString()
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r6 = r5.length()
            if (r6 == 0) goto L_0x01cf
            java.lang.String r3 = r3.concat(r5)
            goto L_0x01d5
        L_0x01cf:
            java.lang.String r5 = new java.lang.String
            r5.<init>(r3)
            r3 = r5
        L_0x01d5:
            r5 = 1
            r0.<init>(r3, r4, r2, r5)
            throw r0
        L_0x01da:
            r5 = 0
            r7 = 0
            r4 = 0
            java.lang.String r8 = "Location"
            java.lang.String r8 = r14.getHeaderField(r8)     // Catch:{ IOException -> 0x0248 }
            r14.disconnect()     // Catch:{ IOException -> 0x0248 }
            if (r8 == 0) goto L_0x0227
            java.net.URL r9 = new java.net.URL     // Catch:{ IOException -> 0x0248 }
            r9.<init>(r0, r8)     // Catch:{ IOException -> 0x0248 }
            java.lang.String r0 = r9.getProtocol()     // Catch:{ IOException -> 0x0248 }
            java.lang.String r8 = "https"
            boolean r8 = r8.equals(r0)     // Catch:{ IOException -> 0x0248 }
            if (r8 != 0) goto L_0x021e
            java.lang.String r8 = "http"
            boolean r8 = r8.equals(r0)     // Catch:{ IOException -> 0x0248 }
            if (r8 != 0) goto L_0x021e
            java.net.ProtocolException r4 = new java.net.ProtocolException     // Catch:{ IOException -> 0x0248 }
            java.lang.String r5 = "Unsupported protocol redirect: "
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ IOException -> 0x0248 }
            int r6 = r0.length()     // Catch:{ IOException -> 0x0248 }
            if (r6 == 0) goto L_0x0215
            java.lang.String r0 = r5.concat(r0)     // Catch:{ IOException -> 0x0248 }
            goto L_0x021a
        L_0x0215:
            java.lang.String r0 = new java.lang.String     // Catch:{ IOException -> 0x0248 }
            r0.<init>(r5)     // Catch:{ IOException -> 0x0248 }
        L_0x021a:
            r4.<init>(r0)     // Catch:{ IOException -> 0x0248 }
            throw r4     // Catch:{ IOException -> 0x0248 }
        L_0x021e:
            r7 = r4
            r4 = r5
            r0 = r9
            r14 = r15
            r8 = r16
            r6 = 1
            goto L_0x0025
        L_0x0227:
            java.net.ProtocolException r0 = new java.net.ProtocolException     // Catch:{ IOException -> 0x0248 }
            java.lang.String r4 = "Null location redirect"
            r0.<init>(r4)     // Catch:{ IOException -> 0x0248 }
            throw r0     // Catch:{ IOException -> 0x0248 }
        L_0x022f:
            java.net.NoRouteToHostException r0 = new java.net.NoRouteToHostException     // Catch:{ IOException -> 0x0248 }
            r4 = 31
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0248 }
            r5.<init>(r4)     // Catch:{ IOException -> 0x0248 }
            java.lang.String r4 = "Too many redirects: "
            r5.append(r4)     // Catch:{ IOException -> 0x0248 }
            r5.append(r15)     // Catch:{ IOException -> 0x0248 }
            java.lang.String r4 = r5.toString()     // Catch:{ IOException -> 0x0248 }
            r0.<init>(r4)     // Catch:{ IOException -> 0x0248 }
            throw r0     // Catch:{ IOException -> 0x0248 }
        L_0x0248:
            r0 = move-exception
            com.google.android.gms.internal.ads.zzos r4 = new com.google.android.gms.internal.ads.zzos
            android.net.Uri r5 = r2.uri
            java.lang.String r5 = r5.toString()
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r6 = r5.length()
            if (r6 == 0) goto L_0x0260
            java.lang.String r3 = r3.concat(r5)
            goto L_0x0266
        L_0x0260:
            java.lang.String r5 = new java.lang.String
            r5.<init>(r3)
            r3 = r5
        L_0x0266:
            r5 = 1
            r4.<init>(r3, r0, r2, r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbek.zza(com.google.android.gms.internal.ads.zzon):long");
    }

    public final int read(byte[] bArr, int i, int i2) throws zzos {
        try {
            if (this.zzbiu != this.zzbis) {
                byte[] andSet = zzbif.getAndSet((Object) null);
                if (andSet == null) {
                    andSet = new byte[4096];
                }
                while (this.zzbiu != this.zzbis) {
                    int read = this.zzbiq.read(andSet, 0, (int) Math.min(this.zzbis - this.zzbiu, (long) andSet.length));
                    if (Thread.interrupted()) {
                        throw new InterruptedIOException();
                    } else if (read != -1) {
                        this.zzbiu += (long) read;
                        if (this.zzbin != null) {
                            this.zzbin.zzc(this, read);
                        }
                    } else {
                        throw new EOFException();
                    }
                }
                zzbif.set(andSet);
            }
            if (i2 == 0) {
                return 0;
            }
            if (this.zzbit != -1) {
                long j = this.zzbit - this.zzcq;
                if (j == 0) {
                    return -1;
                }
                i2 = (int) Math.min((long) i2, j);
            }
            int read2 = this.zzbiq.read(bArr, i, i2);
            if (read2 != -1) {
                this.zzcq += (long) read2;
                if (this.zzbin != null) {
                    this.zzbin.zzc(this, read2);
                }
                return read2;
            } else if (this.zzbit == -1) {
                return -1;
            } else {
                throw new EOFException();
            }
        } catch (IOException e) {
            throw new zzos(e, this.zzbio, 2);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:4|(1:6)(1:7)|8|(5:13|14|(2:16|(1:18))(1:19)|21|(1:25))|26|27) */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0038, code lost:
        if (r3 > android.support.v4.media.session.PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH) goto L_0x003a;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x006b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void close() throws com.google.android.gms.internal.ads.zzos {
        /*
            r9 = this;
            r0 = 0
            r1 = 0
            java.io.InputStream r2 = r9.zzbiq     // Catch:{ all -> 0x0093 }
            if (r2 == 0) goto L_0x007b
            java.net.HttpURLConnection r2 = r9.zzbip     // Catch:{ all -> 0x0093 }
            long r3 = r9.zzbit     // Catch:{ all -> 0x0093 }
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0013
            long r3 = r9.zzbit     // Catch:{ all -> 0x0093 }
            goto L_0x0018
        L_0x0013:
            long r3 = r9.zzbit     // Catch:{ all -> 0x0093 }
            long r7 = r9.zzcq     // Catch:{ all -> 0x0093 }
            long r3 = r3 - r7
        L_0x0018:
            int r7 = com.google.android.gms.internal.ads.zzpq.SDK_INT     // Catch:{ all -> 0x0093 }
            r8 = 19
            if (r7 == r8) goto L_0x0024
            int r7 = com.google.android.gms.internal.ads.zzpq.SDK_INT     // Catch:{ all -> 0x0093 }
            r8 = 20
            if (r7 != r8) goto L_0x006b
        L_0x0024:
            java.io.InputStream r2 = r2.getInputStream()     // Catch:{ Exception -> 0x006b }
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0034
            int r3 = r2.read()     // Catch:{ Exception -> 0x006b }
            r4 = -1
            if (r3 != r4) goto L_0x003a
            goto L_0x006b
        L_0x0034:
            r5 = 2048(0x800, double:1.0118E-320)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x006b
        L_0x003a:
            java.lang.Class r3 = r2.getClass()     // Catch:{ Exception -> 0x006b }
            java.lang.String r3 = r3.getName()     // Catch:{ Exception -> 0x006b }
            java.lang.String r4 = "com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream"
            boolean r4 = r3.equals(r4)     // Catch:{ Exception -> 0x006b }
            if (r4 != 0) goto L_0x0052
            java.lang.String r4 = "com.android.okhttp.internal.http.HttpTransport$FixedLengthInputStream"
            boolean r3 = r3.equals(r4)     // Catch:{ Exception -> 0x006b }
            if (r3 == 0) goto L_0x006b
        L_0x0052:
            java.lang.Class r3 = r2.getClass()     // Catch:{ Exception -> 0x006b }
            java.lang.Class r3 = r3.getSuperclass()     // Catch:{ Exception -> 0x006b }
            java.lang.String r4 = "unexpectedEndOfInput"
            java.lang.Class[] r5 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x006b }
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r5)     // Catch:{ Exception -> 0x006b }
            r4 = 1
            r3.setAccessible(r4)     // Catch:{ Exception -> 0x006b }
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x006b }
            r3.invoke(r2, r4)     // Catch:{ Exception -> 0x006b }
        L_0x006b:
            java.io.InputStream r2 = r9.zzbiq     // Catch:{ IOException -> 0x0071 }
            r2.close()     // Catch:{ IOException -> 0x0071 }
            goto L_0x007b
        L_0x0071:
            r2 = move-exception
            com.google.android.gms.internal.ads.zzos r3 = new com.google.android.gms.internal.ads.zzos     // Catch:{ all -> 0x0093 }
            com.google.android.gms.internal.ads.zzon r4 = r9.zzbio     // Catch:{ all -> 0x0093 }
            r5 = 3
            r3.<init>((java.io.IOException) r2, (com.google.android.gms.internal.ads.zzon) r4, (int) r5)     // Catch:{ all -> 0x0093 }
            throw r3     // Catch:{ all -> 0x0093 }
        L_0x007b:
            r9.zzbiq = r0
            r9.zziq()
            boolean r0 = r9.zzbir
            if (r0 == 0) goto L_0x008d
            r9.zzbir = r1
            com.google.android.gms.internal.ads.zzpa<? super com.google.android.gms.internal.ads.zzbek> r0 = r9.zzbin
            if (r0 == 0) goto L_0x008d
            r0.zze(r9)
        L_0x008d:
            java.util.Set<java.net.Socket> r0 = r9.zzekb
            r0.clear()
            return
        L_0x0093:
            r2 = move-exception
            r9.zzbiq = r0
            r9.zziq()
            boolean r0 = r9.zzbir
            if (r0 == 0) goto L_0x00a6
            r9.zzbir = r1
            com.google.android.gms.internal.ads.zzpa<? super com.google.android.gms.internal.ads.zzbek> r0 = r9.zzbin
            if (r0 == 0) goto L_0x00a6
            r0.zze(r9)
        L_0x00a6:
            java.util.Set<java.net.Socket> r0 = r9.zzekb
            r0.clear()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbek.close():void");
    }

    /* access modifiers changed from: package-private */
    public final void setReceiveBufferSize(int i) {
        this.zzeka = i;
        for (Socket next : this.zzekb) {
            if (!next.isClosed()) {
                try {
                    next.setReceiveBufferSize(this.zzeka);
                } catch (SocketException e) {
                    zzayp.zzd("Failed to update receive buffer size.", e);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zza(Socket socket) {
        this.zzekb.add(socket);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0042  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long zzc(java.net.HttpURLConnection r9) {
        /*
            java.lang.String r0 = "Content-Length"
            java.lang.String r0 = r9.getHeaderField(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            java.lang.String r2 = "]"
            if (r1 != 0) goto L_0x0034
            long r3 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x0013 }
            goto L_0x0036
        L_0x0013:
            java.lang.String r1 = java.lang.String.valueOf(r0)
            int r1 = r1.length()
            int r1 = r1 + 28
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r1)
            java.lang.String r1 = "Unexpected Content-Length ["
            r3.append(r1)
            r3.append(r0)
            r3.append(r2)
            java.lang.String r1 = r3.toString()
            com.google.android.gms.internal.ads.zzayp.zzfc(r1)
        L_0x0034:
            r3 = -1
        L_0x0036:
            java.lang.String r1 = "Content-Range"
            java.lang.String r9 = r9.getHeaderField(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r9)
            if (r1 != 0) goto L_0x00c9
            java.util.regex.Pattern r1 = zzbie
            java.util.regex.Matcher r1 = r1.matcher(r9)
            boolean r5 = r1.find()
            if (r5 == 0) goto L_0x00c9
            r5 = 2
            java.lang.String r5 = r1.group(r5)     // Catch:{ NumberFormatException -> 0x00a8 }
            long r5 = java.lang.Long.parseLong(r5)     // Catch:{ NumberFormatException -> 0x00a8 }
            r7 = 1
            java.lang.String r1 = r1.group(r7)     // Catch:{ NumberFormatException -> 0x00a8 }
            long r7 = java.lang.Long.parseLong(r1)     // Catch:{ NumberFormatException -> 0x00a8 }
            long r5 = r5 - r7
            r7 = 1
            long r5 = r5 + r7
            r7 = 0
            int r1 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r1 >= 0) goto L_0x006c
            r3 = r5
            goto L_0x00c9
        L_0x006c:
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 == 0) goto L_0x00c9
            java.lang.String r1 = java.lang.String.valueOf(r0)     // Catch:{ NumberFormatException -> 0x00a8 }
            int r1 = r1.length()     // Catch:{ NumberFormatException -> 0x00a8 }
            int r1 = r1 + 26
            java.lang.String r7 = java.lang.String.valueOf(r9)     // Catch:{ NumberFormatException -> 0x00a8 }
            int r7 = r7.length()     // Catch:{ NumberFormatException -> 0x00a8 }
            int r1 = r1 + r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x00a8 }
            r7.<init>(r1)     // Catch:{ NumberFormatException -> 0x00a8 }
            java.lang.String r1 = "Inconsistent headers ["
            r7.append(r1)     // Catch:{ NumberFormatException -> 0x00a8 }
            r7.append(r0)     // Catch:{ NumberFormatException -> 0x00a8 }
            java.lang.String r0 = "] ["
            r7.append(r0)     // Catch:{ NumberFormatException -> 0x00a8 }
            r7.append(r9)     // Catch:{ NumberFormatException -> 0x00a8 }
            r7.append(r2)     // Catch:{ NumberFormatException -> 0x00a8 }
            java.lang.String r0 = r7.toString()     // Catch:{ NumberFormatException -> 0x00a8 }
            com.google.android.gms.internal.ads.zzayp.zzfe(r0)     // Catch:{ NumberFormatException -> 0x00a8 }
            long r0 = java.lang.Math.max(r3, r5)     // Catch:{ NumberFormatException -> 0x00a8 }
            r3 = r0
            goto L_0x00c9
        L_0x00a8:
            java.lang.String r0 = java.lang.String.valueOf(r9)
            int r0 = r0.length()
            int r0 = r0 + 27
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            java.lang.String r0 = "Unexpected Content-Range ["
            r1.append(r0)
            r1.append(r9)
            r1.append(r2)
            java.lang.String r9 = r1.toString()
            com.google.android.gms.internal.ads.zzayp.zzfc(r9)
        L_0x00c9:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbek.zzc(java.net.HttpURLConnection):long");
    }

    private final void zziq() {
        HttpURLConnection httpURLConnection = this.zzbip;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e) {
                zzayp.zzc("Unexpected error while disconnecting", e);
            }
            this.zzbip = null;
        }
    }
}
