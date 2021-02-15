package mnetinternal;

import java.util.HashMap;
import java.util.Map;
import net.media.android.bidder.base.common.Constants;

public final class cu {
    private static cu a;
    private Map<String, Boolean> b;

    private cu() {
        HashMap hashMap = new HashMap();
        this.b = hashMap;
        hashMap.put(Constants.Capabilities.BANNER, true);
        this.b.put(Constants.Capabilities.RESPONSIVE_BANNER, true);
    }

    public static cu a() {
        if (a == null) {
            a = new cu();
        }
        return a;
    }

    public Map<String, Boolean> b() {
        return this.b;
    }

    public boolean a(String str) {
        return this.b.containsKey(str);
    }
}
