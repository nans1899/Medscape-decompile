package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.util.Log;
import com.google.common.net.HttpHeaders;
import com.tapstream.sdk.http.RequestBuilders;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzop implements zzom {
    private static final Pattern zzbie = Pattern.compile("^bytes (\\d+)-(\\d+)/(\\d+)$");
    private static final AtomicReference<byte[]> zzbif = new AtomicReference<>();
    private final boolean zzbig;
    private final int zzbih;
    private final int zzbii;
    private final String zzbij;
    private final zzpm<String> zzbik = null;
    private final zzou zzbil;
    private final zzou zzbim;
    private final zzpa<? super zzop> zzbin;
    private zzon zzbio;
    private HttpURLConnection zzbip;
    private InputStream zzbiq;
    private boolean zzbir;
    private long zzbis;
    private long zzbit;
    private long zzbiu;
    private long zzcq;

    public zzop(String str, zzpm<String> zzpm, zzpa<? super zzop> zzpa, int i, int i2, boolean z, zzou zzou) {
        this.zzbij = zzoz.checkNotEmpty(str);
        this.zzbin = zzpa;
        this.zzbim = new zzou();
        this.zzbih = i;
        this.zzbii = i2;
        this.zzbig = true;
        this.zzbil = null;
    }

    public final Uri getUri() {
        HttpURLConnection httpURLConnection = this.zzbip;
        if (httpURLConnection == null) {
            return null;
        }
        return Uri.parse(httpURLConnection.getURL().toString());
    }

    public final Map<String, List<String>> getResponseHeaders() {
        HttpURLConnection httpURLConnection = this.zzbip;
        if (httpURLConnection == null) {
            return null;
        }
        return httpURLConnection.getHeaderFields();
    }

    public final long zza(zzon zzon) throws zzos {
        HttpURLConnection httpURLConnection;
        HttpURLConnection zza;
        zzon zzon2 = zzon;
        this.zzbio = zzon2;
        long j = 0;
        this.zzcq = 0;
        this.zzbiu = 0;
        try {
            URL url = new URL(zzon2.uri.toString());
            byte[] bArr = zzon2.zzbic;
            long j2 = zzon2.position;
            long j3 = zzon2.zzcp;
            boolean isFlagSet = zzon2.isFlagSet(1);
            if (!this.zzbig) {
                httpURLConnection = zza(url, bArr, j2, j3, isFlagSet, true);
            } else {
                URL url2 = url;
                byte[] bArr2 = bArr;
                int i = 0;
                while (true) {
                    int i2 = i + 1;
                    if (i <= 20) {
                        URL url3 = url2;
                        int i3 = i2;
                        long j4 = j3;
                        long j5 = j2;
                        zza = zza(url2, bArr2, j2, j3, isFlagSet, false);
                        int responseCode = zza.getResponseCode();
                        if (!(responseCode == 300 || responseCode == 301 || responseCode == 302 || responseCode == 303)) {
                            if (bArr2 == null) {
                                if (responseCode != 307) {
                                    if (responseCode != 308) {
                                        break;
                                    }
                                }
                            } else {
                                break;
                            }
                        }
                        bArr2 = null;
                        String headerField = zza.getHeaderField(HttpHeaders.LOCATION);
                        zza.disconnect();
                        if (headerField != null) {
                            url2 = new URL(url3, headerField);
                            String protocol = url2.getProtocol();
                            if (RequestBuilders.DEFAULT_SCHEME.equals(protocol) || "http".equals(protocol)) {
                                i = i3;
                                j3 = j4;
                                j2 = j5;
                            } else {
                                String valueOf = String.valueOf(protocol);
                                throw new ProtocolException(valueOf.length() != 0 ? "Unsupported protocol redirect: ".concat(valueOf) : new String("Unsupported protocol redirect: "));
                            }
                        } else {
                            throw new ProtocolException("Null location redirect");
                        }
                    } else {
                        StringBuilder sb = new StringBuilder(31);
                        sb.append("Too many redirects: ");
                        sb.append(i2);
                        throw new NoRouteToHostException(sb.toString());
                    }
                }
                httpURLConnection = zza;
            }
            this.zzbip = httpURLConnection;
            try {
                int responseCode2 = httpURLConnection.getResponseCode();
                if (responseCode2 < 200 || responseCode2 > 299) {
                    Map headerFields = this.zzbip.getHeaderFields();
                    zziq();
                    zzor zzor = new zzor(responseCode2, headerFields, zzon2);
                    if (responseCode2 == 416) {
                        zzor.initCause(new zzoo(0));
                    }
                    throw zzor;
                }
                this.zzbip.getContentType();
                if (responseCode2 == 200 && zzon2.position != 0) {
                    j = zzon2.position;
                }
                this.zzbis = j;
                if (!zzon2.isFlagSet(1)) {
                    long j6 = -1;
                    if (zzon2.zzcp != -1) {
                        this.zzbit = zzon2.zzcp;
                    } else {
                        long zzc = zzc(this.zzbip);
                        if (zzc != -1) {
                            j6 = zzc - this.zzbis;
                        }
                        this.zzbit = j6;
                    }
                } else {
                    this.zzbit = zzon2.zzcp;
                }
                try {
                    this.zzbiq = this.zzbip.getInputStream();
                    this.zzbir = true;
                    zzpa<? super zzop> zzpa = this.zzbin;
                    if (zzpa != null) {
                        zzpa.zza(this, zzon2);
                    }
                    return this.zzbit;
                } catch (IOException e) {
                    zziq();
                    throw new zzos(e, zzon2, 1);
                }
            } catch (IOException e2) {
                IOException iOException = e2;
                zziq();
                String valueOf2 = String.valueOf(zzon2.uri.toString());
                throw new zzos(valueOf2.length() != 0 ? "Unable to connect to ".concat(valueOf2) : new String("Unable to connect to "), iOException, zzon2, 1);
            }
        } catch (IOException e3) {
            String valueOf3 = String.valueOf(zzon2.uri.toString());
            throw new zzos(valueOf3.length() != 0 ? "Unable to connect to ".concat(valueOf3) : new String("Unable to connect to "), e3, zzon2, 1);
        }
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
            java.io.InputStream r2 = r9.zzbiq     // Catch:{ all -> 0x008e }
            if (r2 == 0) goto L_0x007b
            java.net.HttpURLConnection r2 = r9.zzbip     // Catch:{ all -> 0x008e }
            long r3 = r9.zzbit     // Catch:{ all -> 0x008e }
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0013
            long r3 = r9.zzbit     // Catch:{ all -> 0x008e }
            goto L_0x0018
        L_0x0013:
            long r3 = r9.zzbit     // Catch:{ all -> 0x008e }
            long r7 = r9.zzcq     // Catch:{ all -> 0x008e }
            long r3 = r3 - r7
        L_0x0018:
            int r7 = com.google.android.gms.internal.ads.zzpq.SDK_INT     // Catch:{ all -> 0x008e }
            r8 = 19
            if (r7 == r8) goto L_0x0024
            int r7 = com.google.android.gms.internal.ads.zzpq.SDK_INT     // Catch:{ all -> 0x008e }
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
            com.google.android.gms.internal.ads.zzos r3 = new com.google.android.gms.internal.ads.zzos     // Catch:{ all -> 0x008e }
            com.google.android.gms.internal.ads.zzon r4 = r9.zzbio     // Catch:{ all -> 0x008e }
            r5 = 3
            r3.<init>((java.io.IOException) r2, (com.google.android.gms.internal.ads.zzon) r4, (int) r5)     // Catch:{ all -> 0x008e }
            throw r3     // Catch:{ all -> 0x008e }
        L_0x007b:
            r9.zzbiq = r0
            r9.zziq()
            boolean r0 = r9.zzbir
            if (r0 == 0) goto L_0x008d
            r9.zzbir = r1
            com.google.android.gms.internal.ads.zzpa<? super com.google.android.gms.internal.ads.zzop> r0 = r9.zzbin
            if (r0 == 0) goto L_0x008d
            r0.zze(r9)
        L_0x008d:
            return
        L_0x008e:
            r2 = move-exception
            r9.zzbiq = r0
            r9.zziq()
            boolean r0 = r9.zzbir
            if (r0 == 0) goto L_0x00a1
            r9.zzbir = r1
            com.google.android.gms.internal.ads.zzpa<? super com.google.android.gms.internal.ads.zzop> r0 = r9.zzbin
            if (r0 == 0) goto L_0x00a1
            r0.zze(r9)
        L_0x00a1:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzop.close():void");
    }

    private final HttpURLConnection zza(URL url, byte[] bArr, long j, long j2, boolean z, boolean z2) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(this.zzbih);
        httpURLConnection.setReadTimeout(this.zzbii);
        for (Map.Entry next : this.zzbim.zzit().entrySet()) {
            httpURLConnection.setRequestProperty((String) next.getKey(), (String) next.getValue());
        }
        if (!(j == 0 && j2 == -1)) {
            StringBuilder sb = new StringBuilder(27);
            sb.append("bytes=");
            sb.append(j);
            sb.append("-");
            String sb2 = sb.toString();
            if (j2 != -1) {
                String valueOf = String.valueOf(sb2);
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 20);
                sb3.append(valueOf);
                sb3.append((j + j2) - 1);
                sb2 = sb3.toString();
            }
            httpURLConnection.setRequestProperty(HttpHeaders.RANGE, sb2);
        }
        httpURLConnection.setRequestProperty("User-Agent", this.zzbij);
        if (!z) {
            httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "identity");
        }
        httpURLConnection.setInstanceFollowRedirects(z2);
        httpURLConnection.setDoOutput(bArr != null);
        if (bArr != null) {
            httpURLConnection.setRequestMethod("POST");
            if (bArr.length != 0) {
                httpURLConnection.setFixedLengthStreamingMode(bArr.length);
                httpURLConnection.connect();
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.close();
                return httpURLConnection;
            }
        }
        httpURLConnection.connect();
        return httpURLConnection;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long zzc(java.net.HttpURLConnection r10) {
        /*
            java.lang.String r0 = "Content-Length"
            java.lang.String r0 = r10.getHeaderField(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            java.lang.String r2 = "]"
            java.lang.String r3 = "DefaultHttpDataSource"
            if (r1 != 0) goto L_0x0036
            long r4 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x0015 }
            goto L_0x0038
        L_0x0015:
            java.lang.String r1 = java.lang.String.valueOf(r0)
            int r1 = r1.length()
            int r1 = r1 + 28
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r1)
            java.lang.String r1 = "Unexpected Content-Length ["
            r4.append(r1)
            r4.append(r0)
            r4.append(r2)
            java.lang.String r1 = r4.toString()
            android.util.Log.e(r3, r1)
        L_0x0036:
            r4 = -1
        L_0x0038:
            java.lang.String r1 = "Content-Range"
            java.lang.String r10 = r10.getHeaderField(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r10)
            if (r1 != 0) goto L_0x00cb
            java.util.regex.Pattern r1 = zzbie
            java.util.regex.Matcher r1 = r1.matcher(r10)
            boolean r6 = r1.find()
            if (r6 == 0) goto L_0x00cb
            r6 = 2
            java.lang.String r6 = r1.group(r6)     // Catch:{ NumberFormatException -> 0x00aa }
            long r6 = java.lang.Long.parseLong(r6)     // Catch:{ NumberFormatException -> 0x00aa }
            r8 = 1
            java.lang.String r1 = r1.group(r8)     // Catch:{ NumberFormatException -> 0x00aa }
            long r8 = java.lang.Long.parseLong(r1)     // Catch:{ NumberFormatException -> 0x00aa }
            long r6 = r6 - r8
            r8 = 1
            long r6 = r6 + r8
            r8 = 0
            int r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r1 >= 0) goto L_0x006e
            r4 = r6
            goto L_0x00cb
        L_0x006e:
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 == 0) goto L_0x00cb
            java.lang.String r1 = java.lang.String.valueOf(r0)     // Catch:{ NumberFormatException -> 0x00aa }
            int r1 = r1.length()     // Catch:{ NumberFormatException -> 0x00aa }
            int r1 = r1 + 26
            java.lang.String r8 = java.lang.String.valueOf(r10)     // Catch:{ NumberFormatException -> 0x00aa }
            int r8 = r8.length()     // Catch:{ NumberFormatException -> 0x00aa }
            int r1 = r1 + r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x00aa }
            r8.<init>(r1)     // Catch:{ NumberFormatException -> 0x00aa }
            java.lang.String r1 = "Inconsistent headers ["
            r8.append(r1)     // Catch:{ NumberFormatException -> 0x00aa }
            r8.append(r0)     // Catch:{ NumberFormatException -> 0x00aa }
            java.lang.String r0 = "] ["
            r8.append(r0)     // Catch:{ NumberFormatException -> 0x00aa }
            r8.append(r10)     // Catch:{ NumberFormatException -> 0x00aa }
            r8.append(r2)     // Catch:{ NumberFormatException -> 0x00aa }
            java.lang.String r0 = r8.toString()     // Catch:{ NumberFormatException -> 0x00aa }
            android.util.Log.w(r3, r0)     // Catch:{ NumberFormatException -> 0x00aa }
            long r0 = java.lang.Math.max(r4, r6)     // Catch:{ NumberFormatException -> 0x00aa }
            r4 = r0
            goto L_0x00cb
        L_0x00aa:
            java.lang.String r0 = java.lang.String.valueOf(r10)
            int r0 = r0.length()
            int r0 = r0 + 27
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            java.lang.String r0 = "Unexpected Content-Range ["
            r1.append(r0)
            r1.append(r10)
            r1.append(r2)
            java.lang.String r10 = r1.toString()
            android.util.Log.e(r3, r10)
        L_0x00cb:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzop.zzc(java.net.HttpURLConnection):long");
    }

    private final void zziq() {
        HttpURLConnection httpURLConnection = this.zzbip;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e) {
                Log.e("DefaultHttpDataSource", "Unexpected error while disconnecting", e);
            }
            this.zzbip = null;
        }
    }
}
