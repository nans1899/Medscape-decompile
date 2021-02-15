package bo.app;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum z {
    UNKNOWN("unknown"),
    NONE("none"),
    TWO_G("2g"),
    THREE_G("3g"),
    FOUR_G("4g"),
    WIFI("wifi");
    
    private static final Map<String, z> g = null;
    private final String h;

    static {
        g = new HashMap();
        Iterator it = EnumSet.allOf(z.class).iterator();
        while (it.hasNext()) {
            z zVar = (z) it.next();
            g.put(zVar.a(), zVar);
        }
    }

    private z(String str) {
        this.h = str;
    }

    public String a() {
        return this.h;
    }
}
