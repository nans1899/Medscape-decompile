package org.simpleframework.xml.filter;

import java.util.Map;

public class PlatformFilter extends StackFilter {
    public PlatformFilter() {
        this((Map) null);
    }

    public PlatformFilter(Map map) {
        push(new EnvironmentFilter());
        push(new SystemFilter());
        push(new MapFilter(map));
    }
}
