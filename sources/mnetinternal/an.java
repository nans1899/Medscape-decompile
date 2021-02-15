package mnetinternal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.media.android.bidder.base.MNet;
import net.media.android.bidder.base.common.Constants;
import net.media.android.bidder.base.configs.c;

public final class an {
    private static final Map<String, String> a = new ConcurrentHashMap();

    public static void a() {
        String d = c.a().d(Constants.DEFAULT_DTC);
        a.put("bids", String.format("https://%s.d.ina.media.net/d/%s/api/v5/rtb/b", new Object[]{MNet.getCustomerId(), d}));
        a.put("prf", String.format("https://%s.d.ina.media.net/d/%s/api/v5/rtb/b/p", new Object[]{MNet.getCustomerId(), d}));
        a.put(Constants.CONFIG_FILE_NAME, String.format("https://%s.c.ina.media.net/c/api/v2/c/a", new Object[]{MNet.getCustomerId()}));
        a.put("logger", String.format("https://%s.p.ina.media.net/p/api/v1/l", new Object[]{MNet.getCustomerId()}));
        a.put("flog", String.format("https://%s.d.ina.media.net/d/%s/api/v5/rtb/l", new Object[]{MNet.getCustomerId(), d}));
        a.put("hello", String.format("https://%s.d.ina.media.net/d/api/v1/hello", new Object[]{MNet.getCustomerId()}));
    }

    public static String b() {
        String str = a.get("hello");
        if (str != null) {
            return str;
        }
        String format = String.format("https://%s.d.ina.media.net/d/api/v1/hello", new Object[]{MNet.getCustomerId()});
        a.put("hello", format);
        return format;
    }

    public static String c() {
        String str = a.get(Constants.CONFIG_FILE_NAME);
        if (str != null) {
            return str;
        }
        String format = String.format("https://%s.c.ina.media.net/c/api/v2/c/a", new Object[]{MNet.getCustomerId()});
        a.put(Constants.CONFIG_FILE_NAME, format);
        return format;
    }

    public static String d() {
        String str = a.get("logger");
        if (str != null) {
            return str;
        }
        String format = String.format("https://%s.p.ina.media.net/p/api/v1/l", new Object[]{MNet.getCustomerId()});
        a.put("logger", format);
        return format;
    }

    public static String e() {
        String d = c.a().d(Constants.DEFAULT_DTC);
        String str = a.get("bids");
        if (str != null) {
            return str;
        }
        String format = String.format("https://%s.d.ina.media.net/d/%s/api/v5/rtb/b", new Object[]{MNet.getCustomerId(), d});
        a.put("bids", format);
        return format;
    }

    public static String f() {
        String str = a.get("prf");
        if (str != null) {
            return str;
        }
        String format = String.format("https://%s.d.ina.media.net/d/%s/api/v5/rtb/b/p", new Object[]{MNet.getCustomerId(), c.a().d(Constants.DEFAULT_DTC)});
        a.put("prf", format);
        return format;
    }

    public static String g() {
        String str = a.get("flog");
        if (str != null) {
            return str;
        }
        String format = String.format("https://%s.d.ina.media.net/d/%s/api/v5/rtb/l", new Object[]{MNet.getCustomerId(), c.a().d(Constants.DEFAULT_DTC)});
        a.put("flog", format);
        return format;
    }
}
