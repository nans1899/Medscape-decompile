package bo.app;

import android.net.Uri;
import com.appboy.support.AppboyLogger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public final class eb {
    private static final String a = AppboyLogger.getAppboyLogTag(eb.class);

    public static URI a(Uri uri) {
        try {
            return new URI(uri.toString());
        } catch (URISyntaxException unused) {
            String str = a;
            AppboyLogger.e(str, "Could not create URI from uri [" + uri.toString() + "]");
            return null;
        }
    }

    public static URL a(URI uri) {
        try {
            return new URL(uri.toString());
        } catch (MalformedURLException e) {
            String str = a;
            AppboyLogger.e(str, "Unable to parse URI [" + e.getMessage() + "]", e);
            return null;
        }
    }

    public static String a(Object... objArr) {
        long j = 1;
        for (Object hashCode : objArr) {
            int hashCode2 = hashCode.hashCode();
            j *= hashCode2 == 0 ? 1 : (long) hashCode2;
        }
        return Long.toHexString(j);
    }
}
