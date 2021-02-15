package com.comscore.util.crashreport;

import android.os.Build;
import com.comscore.streaming.ContentFeedType;
import com.dd.plist.ASCIIPropertyListParser;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import net.bytebuddy.description.type.TypeDescription;

public class CrashReportHttpFlusher implements CrashReportFlusher {
    private static final int b = 4088;
    private static final int c = 5;
    private static final boolean d;
    private Proxy a;

    static {
        int i = Build.VERSION.SDK_INT;
        d = i < 11 || i > 13;
    }

    private HttpURLConnection a(URL url) throws IOException {
        Proxy proxy = this.a;
        HttpURLConnection httpURLConnection = (HttpURLConnection) (proxy != null ? url.openConnection(proxy) : url.openConnection());
        httpURLConnection.setRequestProperty(HttpHeaders.CONNECTION, "Close");
        return httpURLConnection;
    }

    private Proxy a(String str) {
        int i;
        int indexOf = str.indexOf(58);
        if (indexOf != -1) {
            String substring = str.substring(0, indexOf);
            i = Integer.parseInt(str.substring(indexOf + 1));
            str = substring;
        } else {
            i = 80;
        }
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(str, i));
    }

    private URL a(URL url, int i, String str) throws MalformedURLException {
        switch (i) {
            case 300:
            case ContentFeedType.EAST_HD:
            case ContentFeedType.WEST_HD:
            case ContentFeedType.EAST_SD:
            case 305:
                if (str == null) {
                    return null;
                }
                if (i == 305) {
                    int i2 = 0;
                    if (str.startsWith(url.getProtocol() + ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER)) {
                        i2 = url.getProtocol().length() + 1;
                    }
                    if (str.startsWith("//", i2)) {
                        i2 += 2;
                    }
                    this.a = a(str.substring(i2));
                    return url;
                }
                URL url2 = new URL(url, str);
                if (!url.getProtocol().equals(url2.getProtocol())) {
                    return null;
                }
                return url2;
            default:
                return null;
        }
    }

    private boolean a() {
        return d;
    }

    public URL createURL(String str) {
        if (str.length() > b) {
            String substring = str.substring(0, b);
            int lastIndexOf = substring.lastIndexOf(37);
            if (lastIndexOf >= 4086) {
                substring = substring.substring(0, lastIndexOf);
            }
            str = substring + "&ns_cut=";
        }
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean flush(String str, CrashReportParser crashReportParser, CrashReport crashReport) {
        int i;
        boolean z = false;
        HttpURLConnection httpURLConnection = null;
        try {
            URL createURL = createURL(str + TypeDescription.Generic.OfWildcardType.SYMBOL + crashReportParser.reportToString(crashReport));
            if (a()) {
                httpURLConnection = a(createURL);
                i = httpURLConnection.getResponseCode();
            } else {
                int i2 = 0;
                int i3 = 0;
                while (createURL != null && i3 < 5) {
                    httpURLConnection = a(createURL);
                    httpURLConnection.setInstanceFollowRedirects(false);
                    i2 = httpURLConnection.getResponseCode();
                    createURL = a(createURL, i2, httpURLConnection.getHeaderField(HttpHeaders.LOCATION));
                    i3++;
                }
                i = i2;
            }
            if (i == 200 || i == 204) {
                z = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        return z;
    }
}
