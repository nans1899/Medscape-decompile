package org.conscrypt;

import android.os.Build;
import android.util.Log;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import dalvik.system.BlockGuard;
import dalvik.system.CloseGuard;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketImpl;
import java.security.AlgorithmParameters;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SNIServerName;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import org.conscrypt.ct.CTLogStore;
import org.conscrypt.ct.CTPolicy;

final class Platform {
    private static final String TAG = "Conscrypt";
    private static Method m_getCurveName;

    public static String getDefaultProviderName() {
        return TAG;
    }

    public static String getEndpointIdentificationAlgorithm(SSLParameters sSLParameters) {
        return null;
    }

    static CertBlacklist newDefaultBlacklist() {
        return null;
    }

    static ConscryptCertStore newDefaultCertStore() {
        return null;
    }

    static CTLogStore newDefaultLogStore() {
        return null;
    }

    static CTPolicy newDefaultPolicy(CTLogStore cTLogStore) {
        return null;
    }

    static boolean provideTrustManagerByDefault() {
        return false;
    }

    public static void setEndpointIdentificationAlgorithm(SSLParameters sSLParameters, String str) {
    }

    public static void setup() {
    }

    static boolean supportsConscryptCertStore() {
        return false;
    }

    static SSLEngine unwrapEngine(SSLEngine sSLEngine) {
        return sSLEngine;
    }

    static SSLEngine wrapEngine(ConscryptEngine conscryptEngine) {
        return conscryptEngine;
    }

    static {
        try {
            Method declaredMethod = ECParameterSpec.class.getDeclaredMethod("getCurveName", new Class[0]);
            m_getCurveName = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (Exception unused) {
        }
    }

    private Platform() {
    }

    public static FileDescriptor getFileDescriptor(Socket socket) {
        try {
            Field declaredField = Socket.class.getDeclaredField("impl");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(socket);
            Field declaredField2 = SocketImpl.class.getDeclaredField("fd");
            declaredField2.setAccessible(true);
            return (FileDescriptor) declaredField2.get(obj);
        } catch (Exception e) {
            throw new RuntimeException("Can't get FileDescriptor from socket", e);
        }
    }

    public static FileDescriptor getFileDescriptorFromSSLSocket(AbstractConscryptSocket abstractConscryptSocket) {
        return getFileDescriptor(abstractConscryptSocket);
    }

    public static String getCurveName(ECParameterSpec eCParameterSpec) {
        Method method = m_getCurveName;
        if (method == null) {
            return null;
        }
        try {
            return (String) method.invoke(eCParameterSpec, new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void setCurveName(ECParameterSpec eCParameterSpec, String str) {
        try {
            eCParameterSpec.getClass().getDeclaredMethod("setCurveName", new Class[]{String.class}).invoke(eCParameterSpec, new Object[]{str});
        } catch (Exception unused) {
        }
    }

    public static void setSocketWriteTimeout(Socket socket, long j) throws SocketException {
        try {
            FileDescriptor fileDescriptor = getFileDescriptor(socket);
            if (fileDescriptor == null || !fileDescriptor.valid()) {
                throw new SocketException("Socket closed");
            }
            Class<?> cls = getClass("android.system.StructTimeval", "libcore.io.StructTimeval");
            if (cls == null) {
                Log.w(TAG, "StructTimeval == null; not setting socket write timeout");
                return;
            }
            Method declaredMethod = cls.getDeclaredMethod("fromMillis", new Class[]{Long.TYPE});
            if (declaredMethod == null) {
                Log.w(TAG, "fromMillis == null; not setting socket write timeout");
                return;
            }
            Object invoke = declaredMethod.invoke((Object) null, new Object[]{Long.valueOf(j)});
            Class<?> cls2 = Class.forName("libcore.io.Libcore");
            if (cls2 == null) {
                Log.w(TAG, "Libcore == null; not setting socket write timeout");
                return;
            }
            Field field = cls2.getField(AdParameterKeys.OS);
            if (field == null) {
                Log.w(TAG, "os == null; not setting socket write timeout");
                return;
            }
            Object obj = field.get((Object) null);
            if (obj == null) {
                Log.w(TAG, "instance_os == null; not setting socket write timeout");
                return;
            }
            Class<?> cls3 = getClass("android.system.OsConstants", "libcore.io.OsConstants");
            if (cls3 == null) {
                Log.w(TAG, "OsConstants == null; not setting socket write timeout");
                return;
            }
            Field field2 = cls3.getField("SOL_SOCKET");
            if (field2 == null) {
                Log.w(TAG, "SOL_SOCKET == null; not setting socket write timeout");
                return;
            }
            Field field3 = cls3.getField("SO_SNDTIMEO");
            if (field3 == null) {
                Log.w(TAG, "SO_SNDTIMEO == null; not setting socket write timeout");
                return;
            }
            Method method = obj.getClass().getMethod("setsockoptTimeval", new Class[]{FileDescriptor.class, Integer.TYPE, Integer.TYPE, cls});
            if (method == null) {
                Log.w(TAG, "setsockoptTimeval == null; not setting socket write timeout");
                return;
            }
            method.invoke(obj, new Object[]{fileDescriptor, field2.get((Object) null), field3.get((Object) null), invoke});
        } catch (Exception e) {
            logStackTraceSnippet("Could not set socket write timeout: " + e, e);
            for (Throwable cause = e.getCause(); cause != null; cause = cause.getCause()) {
                logStackTraceSnippet("Caused by: " + cause, cause);
            }
        }
    }

    private static void logStackTraceSnippet(String str, Throwable th) {
        Log.w(TAG, str);
        StackTraceElement[] stackTrace = th.getStackTrace();
        int i = 0;
        while (i < 2 && i < stackTrace.length) {
            Log.w(TAG, "\tat " + stackTrace[i].toString());
            i++;
        }
    }

    private static void setSSLParametersOnImpl(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        sSLParametersImpl.setEndpointIdentificationAlgorithm((String) sSLParameters.getClass().getMethod("getEndpointIdentificationAlgorithm", new Class[0]).invoke(sSLParameters, new Object[0]));
        sSLParametersImpl.setUseCipherSuitesOrder(((Boolean) sSLParameters.getClass().getMethod("getUseCipherSuitesOrder", new Class[0]).invoke(sSLParameters, new Object[0])).booleanValue());
    }

    public static void setSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, AbstractConscryptSocket abstractConscryptSocket) {
        String sniHostnameFromParams;
        try {
            setSSLParametersOnImpl(sSLParameters, sSLParametersImpl);
            if (Build.VERSION.SDK_INT >= 24 && (sniHostnameFromParams = getSniHostnameFromParams(sSLParameters)) != null) {
                abstractConscryptSocket.setHostname(sniHostnameFromParams);
            }
        } catch (IllegalAccessException | NoSuchMethodException unused) {
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    public static void setSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, ConscryptEngine conscryptEngine) {
        String sniHostnameFromParams;
        try {
            setSSLParametersOnImpl(sSLParameters, sSLParametersImpl);
            if (Build.VERSION.SDK_INT >= 24 && (sniHostnameFromParams = getSniHostnameFromParams(sSLParameters)) != null) {
                conscryptEngine.setHostname(sniHostnameFromParams);
            }
        } catch (IllegalAccessException | NoSuchMethodException unused) {
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    private static String getSniHostnameFromParams(SSLParameters sSLParameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<SNIServerName> list = (List) sSLParameters.getClass().getMethod("getServerNames", new Class[0]).invoke(sSLParameters, new Object[0]);
        if (list == null) {
            return null;
        }
        for (SNIServerName sNIServerName : list) {
            if (sNIServerName.getType() == 0) {
                return ((SNIHostName) sNIServerName).getAsciiName();
            }
        }
        return null;
    }

    private static void getSSLParametersFromImpl(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        sSLParameters.getClass().getMethod("setEndpointIdentificationAlgorithm", new Class[]{String.class}).invoke(sSLParameters, new Object[]{sSLParametersImpl.getEndpointIdentificationAlgorithm()});
        sSLParameters.getClass().getMethod("setUseCipherSuitesOrder", new Class[]{Boolean.TYPE}).invoke(sSLParameters, new Object[]{Boolean.valueOf(sSLParametersImpl.getUseCipherSuitesOrder())});
    }

    public static void getSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, AbstractConscryptSocket abstractConscryptSocket) {
        try {
            getSSLParametersFromImpl(sSLParameters, sSLParametersImpl);
            if (Build.VERSION.SDK_INT >= 24) {
                setParametersSniHostname(sSLParameters, sSLParametersImpl, abstractConscryptSocket);
            }
        } catch (IllegalAccessException | NoSuchMethodException unused) {
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    private static void setParametersSniHostname(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, AbstractConscryptSocket abstractConscryptSocket) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (sSLParametersImpl.getUseSni() && AddressUtils.isValidSniHostname(abstractConscryptSocket.getHostname())) {
            sSLParameters.getClass().getMethod("setServerNames", new Class[]{List.class}).invoke(sSLParameters, new Object[]{Collections.singletonList(new SNIHostName(abstractConscryptSocket.getHostname()))});
        }
    }

    public static void getSSLParameters(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, ConscryptEngine conscryptEngine) {
        try {
            getSSLParametersFromImpl(sSLParameters, sSLParametersImpl);
            if (Build.VERSION.SDK_INT >= 24) {
                setParametersSniHostname(sSLParameters, sSLParametersImpl, conscryptEngine);
            }
        } catch (IllegalAccessException | NoSuchMethodException unused) {
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    private static void setParametersSniHostname(SSLParameters sSLParameters, SSLParametersImpl sSLParametersImpl, ConscryptEngine conscryptEngine) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (sSLParametersImpl.getUseSni() && AddressUtils.isValidSniHostname(conscryptEngine.getHostname())) {
            sSLParameters.getClass().getMethod("setServerNames", new Class[]{List.class}).invoke(sSLParameters, new Object[]{Collections.singletonList(new SNIHostName(conscryptEngine.getHostname()))});
        }
    }

    private static Class<?> getClass(String... strArr) {
        int i = 0;
        while (i < strArr.length) {
            try {
                return Class.forName(strArr[i]);
            } catch (Exception unused) {
                i++;
            }
        }
        return null;
    }

    private static boolean checkTrusted(String str, X509TrustManager x509TrustManager, X509Certificate[] x509CertificateArr, String str2, Class<?> cls, Object obj) throws CertificateException {
        try {
            x509TrustManager.getClass().getMethod(str, new Class[]{X509Certificate[].class, String.class, cls}).invoke(x509TrustManager, new Object[]{x509CertificateArr, str2, obj});
            return true;
        } catch (IllegalAccessException | NoSuchMethodException unused) {
            return false;
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof CertificateException) {
                throw ((CertificateException) e.getCause());
            }
            throw new RuntimeException(e.getCause());
        }
    }

    public static void checkClientTrusted(X509TrustManager x509TrustManager, X509Certificate[] x509CertificateArr, String str, AbstractConscryptSocket abstractConscryptSocket) throws CertificateException {
        if (!checkTrusted("checkClientTrusted", x509TrustManager, x509CertificateArr, str, Socket.class, abstractConscryptSocket)) {
            if (!checkTrusted("checkClientTrusted", x509TrustManager, x509CertificateArr, str, String.class, abstractConscryptSocket.getHandshakeSession().getPeerHost())) {
                x509TrustManager.checkClientTrusted(x509CertificateArr, str);
            }
        }
    }

    public static void checkServerTrusted(X509TrustManager x509TrustManager, X509Certificate[] x509CertificateArr, String str, AbstractConscryptSocket abstractConscryptSocket) throws CertificateException {
        if (!checkTrusted("checkServerTrusted", x509TrustManager, x509CertificateArr, str, Socket.class, abstractConscryptSocket)) {
            if (!checkTrusted("checkServerTrusted", x509TrustManager, x509CertificateArr, str, String.class, abstractConscryptSocket.getHandshakeSession().getPeerHost())) {
                x509TrustManager.checkServerTrusted(x509CertificateArr, str);
            }
        }
    }

    public static void checkClientTrusted(X509TrustManager x509TrustManager, X509Certificate[] x509CertificateArr, String str, ConscryptEngine conscryptEngine) throws CertificateException {
        if (!checkTrusted("checkClientTrusted", x509TrustManager, x509CertificateArr, str, SSLEngine.class, conscryptEngine)) {
            if (!checkTrusted("checkClientTrusted", x509TrustManager, x509CertificateArr, str, String.class, conscryptEngine.getHandshakeSession().getPeerHost())) {
                x509TrustManager.checkClientTrusted(x509CertificateArr, str);
            }
        }
    }

    public static void checkServerTrusted(X509TrustManager x509TrustManager, X509Certificate[] x509CertificateArr, String str, ConscryptEngine conscryptEngine) throws CertificateException {
        if (!checkTrusted("checkServerTrusted", x509TrustManager, x509CertificateArr, str, SSLEngine.class, conscryptEngine)) {
            if (!checkTrusted("checkServerTrusted", x509TrustManager, x509CertificateArr, str, String.class, conscryptEngine.getHandshakeSession().getPeerHost())) {
                x509TrustManager.checkServerTrusted(x509CertificateArr, str);
            }
        }
    }

    public static OpenSSLKey wrapRsaKey(PrivateKey privateKey) {
        Method declaredMethod;
        Method declaredMethod2;
        if (Build.VERSION.SDK_INT >= 17) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("org.apache.harmony.xnet.provider.jsse.OpenSSLRSAPrivateKey");
            if (!cls.isInstance(privateKey)) {
                Log.e(TAG, "Private key is not an OpenSSLRSAPrivateKey instance, its class name is:" + privateKey.getClass().getCanonicalName());
                return null;
            }
            try {
                declaredMethod = cls.getDeclaredMethod("getOpenSSLKey", new Class[0]);
                declaredMethod.setAccessible(true);
                Object invoke = declaredMethod.invoke(privateKey, new Object[0]);
                declaredMethod.setAccessible(false);
                if (invoke == null) {
                    Log.e(TAG, "Could not getOpenSSLKey on instance: " + privateKey.toString());
                    return null;
                }
                try {
                    declaredMethod2 = invoke.getClass().getDeclaredMethod("getPkeyContext", new Class[0]);
                    declaredMethod2.setAccessible(true);
                    long longValue = ((Number) declaredMethod2.invoke(invoke, new Object[0])).longValue();
                    declaredMethod2.setAccessible(false);
                    if (longValue != 0) {
                        return new OpenSSLKey(longValue);
                    }
                    Log.e(TAG, "getPkeyContext() returned null");
                    return null;
                } catch (Exception e) {
                    Log.e(TAG, "No getPkeyContext() method on OpenSSLKey member:" + e);
                    return null;
                }
            } catch (Exception e2) {
                Log.e(TAG, "Error during conversion of privatekey instance: " + privateKey.toString(), e2);
                return null;
            } catch (Throwable th) {
                declaredMethod2.setAccessible(false);
                throw th;
            }
        } catch (Exception e3) {
            Log.e(TAG, "Cannot find system OpenSSLRSAPrivateKey class: " + e3);
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void logEvent(java.lang.String r9) {
        /*
            java.lang.String r0 = "android.os.Process"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ Exception -> 0x006b }
            r1 = 0
            java.lang.Class[] r2 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x006b }
            java.lang.reflect.Constructor r2 = r0.getDeclaredConstructor(r2)     // Catch:{ Exception -> 0x006b }
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x006b }
            java.lang.Object r2 = r2.newInstance(r3)     // Catch:{ Exception -> 0x006b }
            java.lang.String r3 = "myUid"
            r4 = 0
            java.lang.Class[] r4 = (java.lang.Class[]) r4     // Catch:{ Exception -> 0x006b }
            java.lang.reflect.Method r0 = r0.getMethod(r3, r4)     // Catch:{ Exception -> 0x006b }
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x006b }
            java.lang.Object r0 = r0.invoke(r2, r3)     // Catch:{ Exception -> 0x006b }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Exception -> 0x006b }
            int r0 = r0.intValue()     // Catch:{ Exception -> 0x006b }
            java.lang.String r2 = "android.util.EventLog"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ Exception -> 0x006b }
            java.lang.Class[] r3 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x006b }
            java.lang.reflect.Constructor r3 = r2.getDeclaredConstructor(r3)     // Catch:{ Exception -> 0x006b }
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x006b }
            java.lang.Object r3 = r3.newInstance(r4)     // Catch:{ Exception -> 0x006b }
            java.lang.String r4 = "writeEvent"
            r5 = 2
            java.lang.Class[] r6 = new java.lang.Class[r5]     // Catch:{ Exception -> 0x006b }
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x006b }
            r6[r1] = r7     // Catch:{ Exception -> 0x006b }
            java.lang.Class<java.lang.Object[]> r7 = java.lang.Object[].class
            r8 = 1
            r6[r8] = r7     // Catch:{ Exception -> 0x006b }
            java.lang.reflect.Method r2 = r2.getMethod(r4, r6)     // Catch:{ Exception -> 0x006b }
            java.lang.Object[] r4 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x006b }
            r6 = 1397638484(0x534e4554, float:8.859264E11)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x006b }
            r4[r1] = r6     // Catch:{ Exception -> 0x006b }
            r6 = 3
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x006b }
            java.lang.String r7 = "conscrypt"
            r6[r1] = r7     // Catch:{ Exception -> 0x006b }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x006b }
            r6[r8] = r0     // Catch:{ Exception -> 0x006b }
            r6[r5] = r9     // Catch:{ Exception -> 0x006b }
            r4[r8] = r6     // Catch:{ Exception -> 0x006b }
            r2.invoke(r3, r4)     // Catch:{ Exception -> 0x006b }
        L_0x006b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.conscrypt.Platform.logEvent(java.lang.String):void");
    }

    static ConscryptEngineSocket createEngineSocket(SSLParametersImpl sSLParametersImpl) throws IOException {
        if (Build.VERSION.SDK_INT >= 24) {
            return new Java8EngineSocket(sSLParametersImpl);
        }
        return new ConscryptEngineSocket(sSLParametersImpl);
    }

    static ConscryptEngineSocket createEngineSocket(String str, int i, SSLParametersImpl sSLParametersImpl) throws IOException {
        if (Build.VERSION.SDK_INT >= 24) {
            return new Java8EngineSocket(str, i, sSLParametersImpl);
        }
        return new ConscryptEngineSocket(str, i, sSLParametersImpl);
    }

    static ConscryptEngineSocket createEngineSocket(InetAddress inetAddress, int i, SSLParametersImpl sSLParametersImpl) throws IOException {
        if (Build.VERSION.SDK_INT >= 24) {
            return new Java8EngineSocket(inetAddress, i, sSLParametersImpl);
        }
        return new ConscryptEngineSocket(inetAddress, i, sSLParametersImpl);
    }

    static ConscryptEngineSocket createEngineSocket(String str, int i, InetAddress inetAddress, int i2, SSLParametersImpl sSLParametersImpl) throws IOException {
        if (Build.VERSION.SDK_INT >= 24) {
            return new Java8EngineSocket(str, i, inetAddress, i2, sSLParametersImpl);
        }
        return new ConscryptEngineSocket(str, i, inetAddress, i2, sSLParametersImpl);
    }

    static ConscryptEngineSocket createEngineSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2, SSLParametersImpl sSLParametersImpl) throws IOException {
        if (Build.VERSION.SDK_INT >= 24) {
            return new Java8EngineSocket(inetAddress, i, inetAddress2, i2, sSLParametersImpl);
        }
        return new ConscryptEngineSocket(inetAddress, i, inetAddress2, i2, sSLParametersImpl);
    }

    static ConscryptEngineSocket createEngineSocket(Socket socket, String str, int i, boolean z, SSLParametersImpl sSLParametersImpl) throws IOException {
        if (Build.VERSION.SDK_INT >= 24) {
            return new Java8EngineSocket(socket, str, i, z, sSLParametersImpl);
        }
        return new ConscryptEngineSocket(socket, str, i, z, sSLParametersImpl);
    }

    static ConscryptFileDescriptorSocket createFileDescriptorSocket(SSLParametersImpl sSLParametersImpl) throws IOException {
        if (Build.VERSION.SDK_INT >= 24) {
            return new Java8FileDescriptorSocket(sSLParametersImpl);
        }
        return new ConscryptFileDescriptorSocket(sSLParametersImpl);
    }

    static ConscryptFileDescriptorSocket createFileDescriptorSocket(String str, int i, SSLParametersImpl sSLParametersImpl) throws IOException {
        if (Build.VERSION.SDK_INT >= 24) {
            return new Java8FileDescriptorSocket(str, i, sSLParametersImpl);
        }
        return new ConscryptFileDescriptorSocket(str, i, sSLParametersImpl);
    }

    static ConscryptFileDescriptorSocket createFileDescriptorSocket(InetAddress inetAddress, int i, SSLParametersImpl sSLParametersImpl) throws IOException {
        if (Build.VERSION.SDK_INT >= 24) {
            return new Java8FileDescriptorSocket(inetAddress, i, sSLParametersImpl);
        }
        return new ConscryptFileDescriptorSocket(inetAddress, i, sSLParametersImpl);
    }

    static ConscryptFileDescriptorSocket createFileDescriptorSocket(String str, int i, InetAddress inetAddress, int i2, SSLParametersImpl sSLParametersImpl) throws IOException {
        if (Build.VERSION.SDK_INT >= 24) {
            return new Java8FileDescriptorSocket(str, i, inetAddress, i2, sSLParametersImpl);
        }
        return new ConscryptFileDescriptorSocket(str, i, inetAddress, i2, sSLParametersImpl);
    }

    static ConscryptFileDescriptorSocket createFileDescriptorSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2, SSLParametersImpl sSLParametersImpl) throws IOException {
        if (Build.VERSION.SDK_INT >= 24) {
            return new Java8FileDescriptorSocket(inetAddress, i, inetAddress2, i2, sSLParametersImpl);
        }
        return new ConscryptFileDescriptorSocket(inetAddress, i, inetAddress2, i2, sSLParametersImpl);
    }

    static ConscryptFileDescriptorSocket createFileDescriptorSocket(Socket socket, String str, int i, boolean z, SSLParametersImpl sSLParametersImpl) throws IOException {
        if (Build.VERSION.SDK_INT >= 24) {
            return new Java8FileDescriptorSocket(socket, str, i, z, sSLParametersImpl);
        }
        return new ConscryptFileDescriptorSocket(socket, str, i, z, sSLParametersImpl);
    }

    public static SSLSocketFactory wrapSocketFactoryIfNeeded(OpenSSLSocketFactoryImpl openSSLSocketFactoryImpl) {
        if (Build.VERSION.SDK_INT < 19) {
            return new PreKitKatPlatformOpenSSLSocketAdapterFactory(openSSLSocketFactoryImpl);
        }
        return Build.VERSION.SDK_INT < 22 ? new KitKatPlatformOpenSSLSocketAdapterFactory(openSSLSocketFactoryImpl) : openSSLSocketFactoryImpl;
    }

    public static GCMParameters fromGCMParameterSpec(AlgorithmParameterSpec algorithmParameterSpec) {
        Class<?> cls;
        try {
            cls = Class.forName("javax.crypto.spec.GCMParameterSpec");
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        if (cls == null || !cls.isAssignableFrom(algorithmParameterSpec.getClass())) {
            return null;
        }
        try {
            return new GCMParameters(((Integer) cls.getMethod("getTLen", new Class[0]).invoke(algorithmParameterSpec, new Object[0])).intValue(), (byte[]) cls.getMethod("getIV", new Class[0]).invoke(algorithmParameterSpec, new Object[0]));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("GCMParameterSpec lacks expected methods", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("GCMParameterSpec lacks expected methods", e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException("Could not fetch GCM parameters", e3.getTargetException());
        }
    }

    static AlgorithmParameterSpec fromGCMParameters(AlgorithmParameters algorithmParameters) {
        Class cls;
        try {
            cls = Class.forName("javax.crypto.spec.GCMParameterSpec");
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        if (cls != null) {
            try {
                return algorithmParameters.getParameterSpec(cls);
            } catch (InvalidParameterSpecException unused2) {
            }
        }
        return null;
    }

    public static AlgorithmParameterSpec toGCMParameterSpec(int i, byte[] bArr) {
        Class<?> cls;
        try {
            cls = Class.forName("javax.crypto.spec.GCMParameterSpec");
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        if (cls != null) {
            try {
                return (AlgorithmParameterSpec) cls.getConstructor(new Class[]{Integer.TYPE, byte[].class}).newInstance(new Object[]{Integer.valueOf(i), bArr});
            } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                e2.getCause().printStackTrace();
            }
        }
        return null;
    }

    public static CloseGuard closeGuardGet() {
        if (Build.VERSION.SDK_INT < 14) {
            return null;
        }
        return CloseGuard.get();
    }

    public static void closeGuardOpen(Object obj, String str) {
        if (Build.VERSION.SDK_INT >= 14) {
            ((CloseGuard) obj).open(str);
        }
    }

    public static void closeGuardClose(Object obj) {
        if (Build.VERSION.SDK_INT >= 14) {
            ((CloseGuard) obj).close();
        }
    }

    public static void closeGuardWarnIfOpen(Object obj) {
        if (Build.VERSION.SDK_INT >= 14) {
            ((CloseGuard) obj).warnIfOpen();
        }
    }

    public static void blockGuardOnNetwork() {
        BlockGuard.getThreadPolicy().onNetwork();
    }

    public static String oidToAlgorithmName(String str) {
        try {
            Method declaredMethod = Class.forName("org.apache.harmony.security.utils.AlgNameMapper").getDeclaredMethod("map2AlgName", new Class[]{String.class});
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke((Object) null, new Object[]{str});
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException(e);
            }
        } catch (Exception unused) {
            try {
                Class<?> cls = Class.forName("sun.security.x509.AlgorithmId");
                Method declaredMethod2 = cls.getDeclaredMethod("get", new Class[]{String.class});
                declaredMethod2.setAccessible(true);
                Method declaredMethod3 = cls.getDeclaredMethod("getName", new Class[0]);
                declaredMethod3.setAccessible(true);
                return (String) declaredMethod3.invoke(declaredMethod2.invoke((Object) null, new Object[]{str}), new Object[0]);
            } catch (InvocationTargetException e2) {
                Throwable cause2 = e2.getCause();
                if (cause2 instanceof RuntimeException) {
                    throw ((RuntimeException) cause2);
                } else if (cause2 instanceof Error) {
                    throw ((Error) cause2);
                } else {
                    throw new RuntimeException(e2);
                }
            } catch (Exception unused2) {
                return str;
            }
        }
    }

    public static SSLSession wrapSSLSession(ExternalSession externalSession) {
        return Build.VERSION.SDK_INT >= 24 ? new Java8ExtendedSSLSession(externalSession) : externalSession;
    }

    public static String getOriginalHostNameFromInetAddress(InetAddress inetAddress) {
        if (Build.VERSION.SDK_INT > 27) {
            try {
                Method declaredMethod = InetAddress.class.getDeclaredMethod("holder", new Class[0]);
                declaredMethod.setAccessible(true);
                Method declaredMethod2 = Class.forName("java.net.InetAddress$InetAddressHolder").getDeclaredMethod("getOriginalHostName", new Class[0]);
                declaredMethod2.setAccessible(true);
                String str = (String) declaredMethod2.invoke(declaredMethod.invoke(inetAddress, new Object[0]), new Object[0]);
                return str == null ? inetAddress.getHostAddress() : str;
            } catch (InvocationTargetException e) {
                throw new RuntimeException("Failed to get originalHostName", e);
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException unused) {
            }
        }
        return inetAddress.getHostAddress();
    }

    public static String getHostStringFromInetSocketAddress(InetSocketAddress inetSocketAddress) {
        if (Build.VERSION.SDK_INT <= 23) {
            return null;
        }
        try {
            return (String) InetSocketAddress.class.getDeclaredMethod("getHostString", new Class[0]).invoke(inetSocketAddress, new Object[0]);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (Exception unused) {
            return null;
        }
    }

    static boolean supportsX509ExtendedTrustManager() {
        return Build.VERSION.SDK_INT > 23;
    }

    public static boolean isCTVerificationRequired(String str) {
        String property;
        boolean z = false;
        if (str == null || (property = Security.getProperty("conscrypt.ct.enable")) == null || !Boolean.valueOf(property).booleanValue()) {
            return false;
        }
        List<String> asList = Arrays.asList(str.split("\\."));
        Collections.reverse(asList);
        String str2 = "conscrypt.ct.enforce";
        for (String str3 : asList) {
            String property2 = Security.getProperty(str2 + ".*");
            if (property2 != null) {
                z = Boolean.valueOf(property2).booleanValue();
            }
            str2 = str2 + "." + str3;
        }
        String property3 = Security.getProperty(str2);
        return property3 != null ? Boolean.valueOf(property3).booleanValue() : z;
    }

    static KeyStore getDefaultCertKeyStore() throws KeyStoreException {
        KeyStore instance = KeyStore.getInstance("AndroidCAStore");
        try {
            instance.load((InputStream) null, (char[]) null);
            return instance;
        } catch (IOException e) {
            throw new KeyStoreException(e);
        } catch (CertificateException e2) {
            throw new KeyStoreException(e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new KeyStoreException(e3);
        }
    }
}
