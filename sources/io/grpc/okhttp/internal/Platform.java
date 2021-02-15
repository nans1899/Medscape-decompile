package io.grpc.okhttp.internal;

import com.google.firebase.iid.MessengerIpcClient;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import okio.Buffer;

public class Platform {
    private static final String[] ANDROID_SECURITY_PROVIDERS = {"com.google.android.gms.org.conscrypt.OpenSSLProvider", "org.conscrypt.OpenSSLProvider", "com.android.org.conscrypt.OpenSSLProvider", "org.apache.harmony.xnet.provider.jsse.OpenSSLProvider", "com.google.android.libraries.stitch.sslguard.SslGuardProvider"};
    private static final Platform PLATFORM = findPlatform();
    public static final Logger logger = Logger.getLogger(Platform.class.getName());
    private final Provider sslProvider;

    public enum TlsExtensionType {
        ALPN_AND_NPN,
        NPN,
        NONE
    }

    public void afterHandshake(SSLSocket sSLSocket) {
    }

    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
    }

    public String getPrefix() {
        return "OkHttp";
    }

    public String getSelectedProtocol(SSLSocket sSLSocket) {
        return null;
    }

    public void tagSocket(Socket socket) throws SocketException {
    }

    public void untagSocket(Socket socket) throws SocketException {
    }

    public static Platform get() {
        return PLATFORM;
    }

    public Platform(Provider provider) {
        this.sslProvider = provider;
    }

    public void logW(String str) {
        System.out.println(str);
    }

    public Provider getProvider() {
        return this.sslProvider;
    }

    public TlsExtensionType getTlsExtensionType() {
        return TlsExtensionType.NONE;
    }

    public void connectSocket(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static io.grpc.okhttp.internal.Platform findPlatform() {
        /*
            java.lang.Class<byte[]> r0 = byte[].class
            boolean r1 = io.grpc.internal.GrpcUtil.IS_RESTRICTED_APPENGINE
            if (r1 == 0) goto L_0x000b
            java.security.Provider r1 = getAppEngineProvider()
            goto L_0x000f
        L_0x000b:
            java.security.Provider r1 = getAndroidSecurityProvider()
        L_0x000f:
            r9 = r1
            r1 = 1
            r2 = 0
            r3 = 0
            if (r9 == 0) goto L_0x00b4
            io.grpc.okhttp.internal.OptionalMethod r4 = new io.grpc.okhttp.internal.OptionalMethod
            java.lang.Class[] r5 = new java.lang.Class[r1]
            java.lang.Class r6 = java.lang.Boolean.TYPE
            r5[r3] = r6
            java.lang.String r6 = "setUseSessionTickets"
            r4.<init>(r2, r6, r5)
            io.grpc.okhttp.internal.OptionalMethod r5 = new io.grpc.okhttp.internal.OptionalMethod
            java.lang.Class[] r6 = new java.lang.Class[r1]
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r3] = r7
            java.lang.String r7 = "setHostname"
            r5.<init>(r2, r7, r6)
            io.grpc.okhttp.internal.OptionalMethod r7 = new io.grpc.okhttp.internal.OptionalMethod
            java.lang.Class[] r6 = new java.lang.Class[r3]
            java.lang.String r8 = "getAlpnSelectedProtocol"
            r7.<init>(r0, r8, r6)
            io.grpc.okhttp.internal.OptionalMethod r8 = new io.grpc.okhttp.internal.OptionalMethod
            java.lang.Class[] r6 = new java.lang.Class[r1]
            r6[r3] = r0
            java.lang.String r0 = "setAlpnProtocols"
            r8.<init>(r2, r0, r6)
            java.lang.String r0 = "android.net.TrafficStats"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0062 }
            java.lang.String r6 = "tagSocket"
            java.lang.Class[] r10 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0062 }
            java.lang.Class<java.net.Socket> r11 = java.net.Socket.class
            r10[r3] = r11     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0062 }
            java.lang.reflect.Method r6 = r0.getMethod(r6, r10)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0062 }
            java.lang.String r10 = "untagSocket"
            java.lang.Class[] r1 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0063 }
            java.lang.Class<java.net.Socket> r11 = java.net.Socket.class
            r1[r3] = r11     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0063 }
            java.lang.reflect.Method r0 = r0.getMethod(r10, r1)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0063 }
            goto L_0x0064
        L_0x0062:
            r6 = r2
        L_0x0063:
            r0 = r2
        L_0x0064:
            boolean r1 = io.grpc.internal.GrpcUtil.IS_RESTRICTED_APPENGINE
            if (r1 == 0) goto L_0x006c
            io.grpc.okhttp.internal.Platform$TlsExtensionType r1 = io.grpc.okhttp.internal.Platform.TlsExtensionType.ALPN_AND_NPN
        L_0x006a:
            r10 = r1
            goto L_0x00a9
        L_0x006c:
            java.lang.String r1 = r9.getName()
            java.lang.String r2 = "GmsCore_OpenSSL"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00a6
            java.lang.String r1 = r9.getName()
            java.lang.String r2 = "Conscrypt"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00a6
            java.lang.String r1 = r9.getName()
            java.lang.String r2 = "Ssl_Guard"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0091
            goto L_0x00a6
        L_0x0091:
            boolean r1 = isAtLeastAndroid5()
            if (r1 == 0) goto L_0x009a
            io.grpc.okhttp.internal.Platform$TlsExtensionType r1 = io.grpc.okhttp.internal.Platform.TlsExtensionType.ALPN_AND_NPN
            goto L_0x006a
        L_0x009a:
            boolean r1 = isAtLeastAndroid41()
            if (r1 == 0) goto L_0x00a3
            io.grpc.okhttp.internal.Platform$TlsExtensionType r1 = io.grpc.okhttp.internal.Platform.TlsExtensionType.NPN
            goto L_0x006a
        L_0x00a3:
            io.grpc.okhttp.internal.Platform$TlsExtensionType r1 = io.grpc.okhttp.internal.Platform.TlsExtensionType.NONE
            goto L_0x006a
        L_0x00a6:
            io.grpc.okhttp.internal.Platform$TlsExtensionType r1 = io.grpc.okhttp.internal.Platform.TlsExtensionType.ALPN_AND_NPN
            goto L_0x006a
        L_0x00a9:
            io.grpc.okhttp.internal.Platform$Android r1 = new io.grpc.okhttp.internal.Platform$Android
            r2 = r1
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r0
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            return r1
        L_0x00b4:
            javax.net.ssl.SSLContext r0 = javax.net.ssl.SSLContext.getDefault()     // Catch:{ NoSuchAlgorithmException -> 0x016f }
            java.security.Provider r0 = r0.getProvider()     // Catch:{ NoSuchAlgorithmException -> 0x016f }
            java.lang.String r4 = "TLS"
            javax.net.ssl.SSLContext r4 = javax.net.ssl.SSLContext.getInstance(r4, r0)     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            r4.init(r2, r2, r2)     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            javax.net.ssl.SSLEngine r4 = r4.createSSLEngine()     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            io.grpc.okhttp.internal.Platform$1 r5 = new io.grpc.okhttp.internal.Platform$1     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            r5.<init>()     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            java.lang.Object r5 = java.security.AccessController.doPrivileged(r5)     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            java.lang.reflect.Method r5 = (java.lang.reflect.Method) r5     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            r5.invoke(r4, r6)     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            io.grpc.okhttp.internal.Platform$2 r4 = new io.grpc.okhttp.internal.Platform$2     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            r4.<init>()     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            java.lang.Object r4 = java.security.AccessController.doPrivileged(r4)     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            java.lang.reflect.Method r4 = (java.lang.reflect.Method) r4     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            io.grpc.okhttp.internal.Platform$3 r5 = new io.grpc.okhttp.internal.Platform$3     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            r5.<init>()     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            java.lang.Object r5 = java.security.AccessController.doPrivileged(r5)     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            java.lang.reflect.Method r5 = (java.lang.reflect.Method) r5     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            io.grpc.okhttp.internal.Platform$JdkAlpnPlatform r6 = new io.grpc.okhttp.internal.Platform$JdkAlpnPlatform     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            r6.<init>(r0, r4, r5)     // Catch:{ IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException -> 0x00f5 }
            return r6
        L_0x00f5:
            java.lang.String r2 = "org.eclipse.jetty.alpn.ALPN"
            java.lang.Class r4 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            r5.<init>()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            r5.append(r2)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.String r6 = "$Provider"
            r5.append(r6)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.String r5 = r5.toString()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.Class r5 = java.lang.Class.forName(r5)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            r6.<init>()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            r6.append(r2)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.String r7 = "$ClientProvider"
            r6.append(r7)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.String r6 = r6.toString()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.Class r8 = java.lang.Class.forName(r6)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            r6.<init>()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            r6.append(r2)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.String r2 = "$ServerProvider"
            r6.append(r2)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.String r2 = r6.toString()     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.Class r9 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.String r2 = "put"
            r6 = 2
            java.lang.Class[] r6 = new java.lang.Class[r6]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.Class<javax.net.ssl.SSLSocket> r7 = javax.net.ssl.SSLSocket.class
            r6[r3] = r7     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            r6[r1] = r5     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.reflect.Method r5 = r4.getMethod(r2, r6)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.String r2 = "get"
            java.lang.Class[] r6 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.Class<javax.net.ssl.SSLSocket> r7 = javax.net.ssl.SSLSocket.class
            r6[r3] = r7     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.reflect.Method r6 = r4.getMethod(r2, r6)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.String r2 = "remove"
            java.lang.Class[] r1 = new java.lang.Class[r1]     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.Class<javax.net.ssl.SSLSocket> r7 = javax.net.ssl.SSLSocket.class
            r1[r3] = r7     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            java.lang.reflect.Method r7 = r4.getMethod(r2, r1)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            io.grpc.okhttp.internal.Platform$JdkWithJettyBootPlatform r1 = new io.grpc.okhttp.internal.Platform$JdkWithJettyBootPlatform     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            r4 = r1
            r10 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10)     // Catch:{ ClassNotFoundException | NoSuchMethodException -> 0x0169 }
            return r1
        L_0x0169:
            io.grpc.okhttp.internal.Platform r1 = new io.grpc.okhttp.internal.Platform
            r1.<init>(r0)
            return r1
        L_0x016f:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.internal.Platform.findPlatform():io.grpc.okhttp.internal.Platform");
    }

    private static boolean isAtLeastAndroid5() {
        try {
            Platform.class.getClassLoader().loadClass("android.net.Network");
            return true;
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Can't find class", e);
            return false;
        }
    }

    private static boolean isAtLeastAndroid41() {
        try {
            Platform.class.getClassLoader().loadClass("android.app.ActivityOptions");
            return true;
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Can't find class", e);
            return false;
        }
    }

    private static Provider getAppEngineProvider() {
        try {
            return (Provider) Class.forName("org.conscrypt.OpenSSLProvider").getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable th) {
            throw new RuntimeException("Unable to load conscrypt security provider", th);
        }
    }

    private static Provider getAndroidSecurityProvider() {
        for (Provider provider : Security.getProviders()) {
            for (String str : ANDROID_SECURITY_PROVIDERS) {
                if (str.equals(provider.getClass().getName())) {
                    logger.log(Level.FINE, "Found registered provider {0}", str);
                    return provider;
                }
            }
        }
        logger.log(Level.WARNING, "Unable to find Conscrypt");
        return null;
    }

    private static class Android extends Platform {
        private final OptionalMethod<Socket> getAlpnSelectedProtocol;
        private final OptionalMethod<Socket> setAlpnProtocols;
        private final OptionalMethod<Socket> setHostname;
        private final OptionalMethod<Socket> setUseSessionTickets;
        private final TlsExtensionType tlsExtensionType;
        private final Method trafficStatsTagSocket;
        private final Method trafficStatsUntagSocket;

        public Android(OptionalMethod<Socket> optionalMethod, OptionalMethod<Socket> optionalMethod2, Method method, Method method2, OptionalMethod<Socket> optionalMethod3, OptionalMethod<Socket> optionalMethod4, Provider provider, TlsExtensionType tlsExtensionType2) {
            super(provider);
            this.setUseSessionTickets = optionalMethod;
            this.setHostname = optionalMethod2;
            this.trafficStatsTagSocket = method;
            this.trafficStatsUntagSocket = method2;
            this.getAlpnSelectedProtocol = optionalMethod3;
            this.setAlpnProtocols = optionalMethod4;
            this.tlsExtensionType = tlsExtensionType2;
        }

        public TlsExtensionType getTlsExtensionType() {
            return this.tlsExtensionType;
        }

        public void connectSocket(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
            try {
                socket.connect(inetSocketAddress, i);
            } catch (SecurityException e) {
                IOException iOException = new IOException("Exception in connect");
                iOException.initCause(e);
                throw iOException;
            }
        }

        public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
            if (str != null) {
                this.setUseSessionTickets.invokeOptionalWithoutCheckedException(sSLSocket, true);
                this.setHostname.invokeOptionalWithoutCheckedException(sSLSocket, str);
            }
            if (this.setAlpnProtocols.isSupported(sSLSocket)) {
                this.setAlpnProtocols.invokeWithoutCheckedException(sSLSocket, concatLengthPrefixed(list));
            }
        }

        public String getSelectedProtocol(SSLSocket sSLSocket) {
            byte[] bArr;
            if (this.getAlpnSelectedProtocol.isSupported(sSLSocket) && (bArr = (byte[]) this.getAlpnSelectedProtocol.invokeWithoutCheckedException(sSLSocket, new Object[0])) != null) {
                return new String(bArr, Util.UTF_8);
            }
            return null;
        }

        public void tagSocket(Socket socket) throws SocketException {
            Method method = this.trafficStatsTagSocket;
            if (method != null) {
                try {
                    method.invoke((Object) null, new Object[]{socket});
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2.getCause());
                }
            }
        }

        public void untagSocket(Socket socket) throws SocketException {
            Method method = this.trafficStatsUntagSocket;
            if (method != null) {
                try {
                    method.invoke((Object) null, new Object[]{socket});
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2.getCause());
                }
            }
        }
    }

    private static class JdkAlpnPlatform extends Platform {
        private final Method getApplicationProtocol;
        private final Method setApplicationProtocols;

        private JdkAlpnPlatform(Provider provider, Method method, Method method2) {
            super(provider);
            this.setApplicationProtocols = method;
            this.getApplicationProtocol = method2;
        }

        public TlsExtensionType getTlsExtensionType() {
            return TlsExtensionType.ALPN_AND_NPN;
        }

        public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            ArrayList arrayList = new ArrayList(list.size());
            for (Protocol next : list) {
                if (next != Protocol.HTTP_1_0) {
                    arrayList.add(next.toString());
                }
            }
            try {
                this.setApplicationProtocols.invoke(sSLParameters, new Object[]{arrayList.toArray(new String[arrayList.size()])});
                sSLSocket.setSSLParameters(sSLParameters);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2);
            }
        }

        public String getSelectedProtocol(SSLSocket sSLSocket) {
            try {
                return (String) this.getApplicationProtocol.invoke(sSLSocket, new Object[0]);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    private static class JdkWithJettyBootPlatform extends Platform {
        private final Class<?> clientProviderClass;
        private final Method getMethod;
        private final Method putMethod;
        private final Method removeMethod;
        private final Class<?> serverProviderClass;

        public JdkWithJettyBootPlatform(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2, Provider provider) {
            super(provider);
            this.putMethod = method;
            this.getMethod = method2;
            this.removeMethod = method3;
            this.clientProviderClass = cls;
            this.serverProviderClass = cls2;
        }

        public TlsExtensionType getTlsExtensionType() {
            return TlsExtensionType.ALPN_AND_NPN;
        }

        public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Protocol protocol = list.get(i);
                if (protocol != Protocol.HTTP_1_0) {
                    arrayList.add(protocol.toString());
                }
            }
            try {
                Object newProxyInstance = Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.clientProviderClass, this.serverProviderClass}, new JettyNegoProvider(arrayList));
                this.putMethod.invoke((Object) null, new Object[]{sSLSocket, newProxyInstance});
            } catch (InvocationTargetException e) {
                throw new AssertionError(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void afterHandshake(SSLSocket sSLSocket) {
            try {
                this.removeMethod.invoke((Object) null, new Object[]{sSLSocket});
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (InvocationTargetException unused2) {
            }
        }

        public String getSelectedProtocol(SSLSocket sSLSocket) {
            try {
                JettyNegoProvider jettyNegoProvider = (JettyNegoProvider) Proxy.getInvocationHandler(this.getMethod.invoke((Object) null, new Object[]{sSLSocket}));
                if (!jettyNegoProvider.unsupported && jettyNegoProvider.selected == null) {
                    logger.log(Level.INFO, "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
                    return null;
                } else if (jettyNegoProvider.unsupported) {
                    return null;
                } else {
                    return jettyNegoProvider.selected;
                }
            } catch (InvocationTargetException unused) {
                throw new AssertionError();
            } catch (IllegalAccessException unused2) {
                throw new AssertionError();
            }
        }
    }

    private static class JettyNegoProvider implements InvocationHandler {
        private final List<String> protocols;
        /* access modifiers changed from: private */
        public String selected;
        /* access modifiers changed from: private */
        public boolean unsupported;

        public JettyNegoProvider(List<String> list) {
            this.protocols = list;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (objArr == null) {
                objArr = Util.EMPTY_STRING_ARRAY;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return true;
            }
            if (name.equals(MessengerIpcClient.KEY_UNSUPPORTED) && Void.TYPE == returnType) {
                this.unsupported = true;
                return null;
            } else if (name.equals("protocols") && objArr.length == 0) {
                return this.protocols;
            } else {
                if ((name.equals("selectProtocol") || name.equals("select")) && String.class == returnType && objArr.length == 1 && (objArr[0] instanceof List)) {
                    List list = (List) objArr[0];
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        if (this.protocols.contains(list.get(i))) {
                            String str = (String) list.get(i);
                            this.selected = str;
                            return str;
                        }
                    }
                    String str2 = this.protocols.get(0);
                    this.selected = str2;
                    return str2;
                } else if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr.length != 1) {
                    return method.invoke(this, objArr);
                } else {
                    this.selected = (String) objArr[0];
                    return null;
                }
            }
        }
    }

    public static byte[] concatLengthPrefixed(List<Protocol> list) {
        Buffer buffer = new Buffer();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                buffer.writeByte(protocol.toString().length());
                buffer.writeUtf8(protocol.toString());
            }
        }
        return buffer.readByteArray();
    }
}
