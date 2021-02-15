package bo.app;

import com.appboy.support.AppboyLogger;
import java.net.InetAddress;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.ArrayList;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

class i extends SSLSocketFactory {
    private static final String a = AppboyLogger.getAppboyLogTag(i.class);
    private SSLSocketFactory b;

    public i() {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
        this.b = instance.getSocketFactory();
    }

    public String[] getDefaultCipherSuites() {
        return this.b.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.b.getSupportedCipherSuites();
    }

    public Socket createSocket() {
        return a(this.b.createSocket());
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        return a(this.b.createSocket(socket, str, i, z));
    }

    public Socket createSocket(String str, int i) {
        return a(this.b.createSocket(str, i));
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return a(this.b.createSocket(str, i, inetAddress, i2));
    }

    public Socket createSocket(InetAddress inetAddress, int i) {
        return a(this.b.createSocket(inetAddress, i));
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return a(this.b.createSocket(inetAddress, i, inetAddress2, i2));
    }

    private Socket a(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket)) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            ArrayList arrayList = new ArrayList();
            for (String str : sSLSocket.getSupportedProtocols()) {
                if (!str.equals("SSLv3")) {
                    arrayList.add(str);
                }
            }
            AppboyLogger.v(a, "Enabling SSL protocols: " + arrayList);
            sSLSocket.setEnabledProtocols((String[]) arrayList.toArray(new String[arrayList.size()]));
        }
        return socket;
    }
}
