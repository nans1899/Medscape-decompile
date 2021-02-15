package bo.app;

import com.appboy.support.AppboyLogger;
import com.tapstream.sdk.http.RequestBuilders;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

public class k {
    private static final String a = AppboyLogger.getAppboyLogTag(k.class);
    private static i b;

    static {
        try {
            b = new i();
        } catch (Exception e) {
            AppboyLogger.e(a, "Exception initializing static TLS socket factory.", e);
        }
    }

    public static URLConnection a(URL url) {
        URLConnection openConnection = url.openConnection();
        if (url.getProtocol().equals(RequestBuilders.DEFAULT_SCHEME)) {
            try {
                ((HttpsURLConnection) openConnection).setSSLSocketFactory(b);
            } catch (Exception e) {
                AppboyLogger.e(a, "Exception setting TLS socket factory on url connection.", e);
            }
        }
        return openConnection;
    }
}
