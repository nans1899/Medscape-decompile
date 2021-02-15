package org.simpleframework.xml.filter;

import java.util.Map;

public class MapFilter implements Filter {
    private Filter filter;
    private Map map;

    public MapFilter(Map map2) {
        this(map2, (Filter) null);
    }

    public MapFilter(Map map2, Filter filter2) {
        this.filter = filter2;
        this.map = map2;
    }

    public String replace(String str) {
        Map map2 = this.map;
        Object obj = map2 != null ? map2.get(str) : null;
        if (obj != null) {
            return obj.toString();
        }
        Filter filter2 = this.filter;
        if (filter2 != null) {
            return filter2.replace(str);
        }
        return null;
    }
}
