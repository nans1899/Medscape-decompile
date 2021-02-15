package com.comscore.util.jni;

import com.comscore.util.Base64Coder;
import com.comscore.util.TcfDataLoader;
import com.comscore.util.crashreport.CrashReport;
import com.comscore.util.crashreport.CrashReportDecorator;
import com.comscore.util.crashreport.CrashReportManager;
import com.comscore.util.log.Logger;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import javax.crypto.Cipher;
import org.apache.commons.io.IOUtils;

public abstract class JniComScoreHelper implements Logger.OnErrorLogListener {
    protected CrashReportManager crashReporter = new CrashReportManager(createCrashReportDecorator());

    public static class HTTPStream {
        private HttpURLConnection a;
        private InputStream b;
        private long c;

        public HTTPStream(HttpURLConnection httpURLConnection, int[] iArr, StringBuffer stringBuffer) throws IOException {
            this.a = httpURLConnection;
            try {
                this.b = new BufferedInputStream(this.a.getInputStream());
            } catch (IOException e) {
                if (this.a.getResponseCode() < 400) {
                    throw e;
                }
            } finally {
                iArr[0] = this.a.getResponseCode();
            }
            this.b = iArr[0] >= 400 ? this.a.getErrorStream() : this.a.getInputStream();
            for (Map.Entry entry : this.a.getHeaderFields().entrySet()) {
                if (!(entry.getKey() == null || entry.getValue() == null)) {
                    stringBuffer.append(((String) entry.getKey()) + ": " + a(",", (Iterable) entry.getValue()) + IOUtils.LINE_SEPARATOR_UNIX);
                }
            }
        }

        private String a(CharSequence charSequence, Iterable<?> iterable) {
            StringBuilder sb = new StringBuilder();
            boolean z = true;
            for (Object next : iterable) {
                if (z) {
                    z = false;
                } else {
                    sb.append(charSequence);
                }
                sb.append(next);
            }
            return sb.toString();
        }

        public final long getPosition() {
            return this.c;
        }

        public final long getTotalLength() {
            return -1;
        }

        public final boolean isExhausted() {
            return false;
        }

        public final int read(byte[] bArr, int i) {
            int i2 = 0;
            try {
                i2 = this.b.read(bArr, 0, i);
            } catch (IOException unused) {
            }
            if (i2 > 0) {
                this.c += (long) i2;
            }
            return i2;
        }

        public final void release() {
            try {
                this.b.close();
            } catch (IOException unused) {
            }
            this.a.disconnect();
        }

        public final boolean setPosition(long j) {
            return false;
        }
    }

    public JniComScoreHelper() {
        Logger.setOnErrorLogListener(this);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:43|44|56) */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r1.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00b4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.comscore.util.jni.JniComScoreHelper.HTTPStream createHTTPStream(java.lang.String r6, boolean r7, byte[] r8, java.lang.String r9, int r10, int[] r11, java.lang.StringBuffer r12, int r13, java.lang.String r14) {
        /*
            r0 = 0
            if (r10 >= 0) goto L_0x0005
            r10 = 0
            goto L_0x0009
        L_0x0005:
            if (r10 != 0) goto L_0x0009
            r10 = 30000(0x7530, float:4.2039E-41)
        L_0x0009:
            java.lang.String r1 = "\\n"
            java.lang.String[] r9 = r9.split(r1)
        L_0x000f:
            java.net.URL r1 = new java.net.URL     // Catch:{ all -> 0x00b7 }
            r1.<init>(r6)     // Catch:{ all -> 0x00b7 }
            java.net.URLConnection r1 = r1.openConnection()     // Catch:{ all -> 0x00b7 }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ all -> 0x00b7 }
            if (r1 == 0) goto L_0x00b7
            r1.setInstanceFollowRedirects(r0)     // Catch:{ all -> 0x00b4 }
            r1.setConnectTimeout(r10)     // Catch:{ all -> 0x00b4 }
            r1.setReadTimeout(r10)     // Catch:{ all -> 0x00b4 }
            r2 = 0
        L_0x0026:
            int r3 = r9.length     // Catch:{ all -> 0x00b4 }
            if (r2 >= r3) goto L_0x0055
            r3 = r9[r2]     // Catch:{ all -> 0x00b4 }
            java.lang.String r4 = ":"
            int r3 = r3.indexOf(r4)     // Catch:{ all -> 0x00b4 }
            if (r3 <= 0) goto L_0x0052
            r4 = r9[r2]     // Catch:{ all -> 0x00b4 }
            int r4 = r4.length()     // Catch:{ all -> 0x00b4 }
            if (r3 >= r4) goto L_0x0052
            r4 = r9[r2]     // Catch:{ all -> 0x00b4 }
            java.lang.String r4 = r4.substring(r0, r3)     // Catch:{ all -> 0x00b4 }
            r5 = r9[r2]     // Catch:{ all -> 0x00b4 }
            int r3 = r3 + 1
            java.lang.String r3 = r5.substring(r3)     // Catch:{ all -> 0x00b4 }
            int r5 = r3.length()     // Catch:{ all -> 0x00b4 }
            if (r5 <= 0) goto L_0x0052
            r1.setRequestProperty(r4, r3)     // Catch:{ all -> 0x00b4 }
        L_0x0052:
            int r2 = r2 + 1
            goto L_0x0026
        L_0x0055:
            r1.setRequestMethod(r14)     // Catch:{ all -> 0x00b4 }
            if (r7 == 0) goto L_0x006a
            r2 = 1
            r1.setDoOutput(r2)     // Catch:{ all -> 0x00b4 }
            if (r8 == 0) goto L_0x006a
            java.io.OutputStream r2 = r1.getOutputStream()     // Catch:{ all -> 0x00b4 }
            r2.write(r8)     // Catch:{ all -> 0x00b4 }
            r2.flush()     // Catch:{ all -> 0x00b4 }
        L_0x006a:
            com.comscore.util.jni.JniComScoreHelper$HTTPStream r2 = new com.comscore.util.jni.JniComScoreHelper$HTTPStream     // Catch:{ all -> 0x00b4 }
            r2.<init>(r1, r11, r12)     // Catch:{ all -> 0x00b4 }
            r3 = r11[r0]     // Catch:{ all -> 0x00b4 }
            int r13 = r13 + -1
            if (r13 < 0) goto L_0x00b3
            r4 = 301(0x12d, float:4.22E-43)
            if (r3 == r4) goto L_0x0085
            r4 = 302(0x12e, float:4.23E-43)
            if (r3 == r4) goto L_0x0085
            r4 = 303(0x12f, float:4.25E-43)
            if (r3 == r4) goto L_0x0085
            r4 = 307(0x133, float:4.3E-43)
            if (r3 != r4) goto L_0x00b3
        L_0x0085:
            java.lang.String r3 = "Location:"
            int r3 = r12.indexOf(r3)     // Catch:{ all -> 0x00b4 }
            int r3 = r3 + 10
            java.lang.String r4 = "\n"
            int r4 = r12.indexOf(r4, r3)     // Catch:{ all -> 0x00b4 }
            if (r4 <= r3) goto L_0x00b3
            java.lang.String r3 = r12.substring(r3, r4)     // Catch:{ all -> 0x00b4 }
            java.net.URL r4 = new java.net.URL     // Catch:{ all -> 0x00b4 }
            r4.<init>(r6)     // Catch:{ all -> 0x00b4 }
            java.net.URL r5 = new java.net.URL     // Catch:{ all -> 0x00b4 }
            r5.<init>(r4, r3)     // Catch:{ all -> 0x00b4 }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x00b4 }
            if (r3 == r6) goto L_0x00b3
            int r6 = r12.length()     // Catch:{ all -> 0x00b4 }
            r12.delete(r0, r6)     // Catch:{ all -> 0x00b4 }
            r6 = r3
            goto L_0x000f
        L_0x00b3:
            return r2
        L_0x00b4:
            r1.disconnect()     // Catch:{ all -> 0x00b7 }
        L_0x00b7:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.comscore.util.jni.JniComScoreHelper.createHTTPStream(java.lang.String, boolean, byte[], java.lang.String, int, int[], java.lang.StringBuffer, int, java.lang.String):com.comscore.util.jni.JniComScoreHelper$HTTPStream");
    }

    protected static String getLocaleValue(boolean z) {
        Locale locale = Locale.getDefault();
        return z ? locale.getDisplayCountry(Locale.US) : locale.getDisplayLanguage(Locale.US);
    }

    private static native void shutdownSdkNative();

    /* access modifiers changed from: protected */
    public CrashReportDecorator createCrashReportDecorator() {
        return new CrashReportDecorator(this);
    }

    /* access modifiers changed from: protected */
    public String decryptRSA(String str, String str2) {
        try {
            PrivateKey generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64Coder.decode(str2.replace(IOUtils.LINE_SEPARATOR_UNIX, "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", ""))));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
            instance.init(2, generatePrivate);
            return new String(instance.doFinal(Base64Coder.decode(str)));
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String encryptRSA(String str, String str2) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64Coder.decode(str2.replace(IOUtils.LINE_SEPARATOR_UNIX, "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", ""))));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
            instance.init(1, generatePublic);
            return new String(Base64Coder.encode(instance.doFinal(str.getBytes())));
        } catch (Exception unused) {
            return null;
        }
    }

    public abstract String getAppDataDir();

    public abstract String getApplicationId();

    public abstract String getApplicationName();

    public abstract String getApplicationVersion();

    public abstract String getArchitecture();

    /* access modifiers changed from: protected */
    public abstract int getConnectivityType();

    /* access modifiers changed from: protected */
    public String getCrossPublisherUniqueDeviceId() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract int getCurrentHostApplicationState();

    public abstract String[] getDeviceIds();

    public abstract String getDeviceModel();

    /* access modifiers changed from: protected */
    public String[] getInvalidIds() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract String getLanguage();

    public abstract String getOsName();

    public abstract String getOsVersion();

    /* access modifiers changed from: protected */
    public abstract Map<String, String> getPlatformLabels(Map<String, String> map);

    public abstract String getRuntimeName();

    public abstract String getRuntimeVersion();

    /* access modifiers changed from: protected */
    public abstract int getScreenHeight();

    /* access modifiers changed from: protected */
    public abstract int getScreenWidth();

    /* access modifiers changed from: protected */
    public TcfDataLoader getTcfDataLoader() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract boolean isJailBroken();

    /* access modifiers changed from: protected */
    public boolean libraryPostUpdate(String str, String str2, Map<String, String> map, ArrayList<String> arrayList) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean libraryUpdate(String str, String str2, Map<String, String> map) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCoreCreated() {
    }

    /* access modifiers changed from: protected */
    public void onCoreDestroyed() {
    }

    /* access modifiers changed from: protected */
    public void onCoreInitializationFinished() {
    }

    /* access modifiers changed from: protected */
    public void onCoreStarted() {
    }

    public void onLogError(String str, Throwable th) {
        this.crashReporter.addReport(new CrashReport(str, th, (Map<String, String>) null));
    }

    public void shutdownSdk() {
        try {
            shutdownSdkNative();
        } catch (UnsatisfiedLinkError e) {
            Logger.e("Error using the native library: ", (Throwable) e);
        }
    }

    public void start() {
        this.crashReporter.start();
    }

    public abstract boolean subscribeToForegroundNotification();

    public abstract boolean unsubscribeFromForegroundNotification();
}
