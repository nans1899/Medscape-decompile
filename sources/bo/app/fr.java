package bo.app;

import android.util.Base64;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;

public abstract class fr implements fk {
    private static final String a = AppboyLogger.getAppboyLogTag(fr.class);
    private long b;
    private long c;
    private bz d;

    protected fr() {
        long c2 = du.c();
        this.c = c2;
        this.b = c2 / 1000;
    }

    protected fr(bz bzVar) {
        this();
        this.d = bzVar;
    }

    public long c() {
        return this.b;
    }

    public long d() {
        return this.c;
    }

    public bz e() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public String a(String str) {
        if (StringUtils.isNullOrBlank(str)) {
            return null;
        }
        try {
            return new String(Base64.decode(str, 0)).split("_")[0];
        } catch (Exception e) {
            String str2 = a;
            AppboyLogger.e(str2, "Unexpected error decoding Base64 encoded campaign Id " + str, e);
            return null;
        }
    }
}
