package org.simpleframework.xml.filter;

public class SystemFilter implements Filter {
    private Filter filter;

    public SystemFilter() {
        this((Filter) null);
    }

    public SystemFilter(Filter filter2) {
        this.filter = filter2;
    }

    public String replace(String str) {
        String property = System.getProperty(str);
        if (property != null) {
            return property;
        }
        Filter filter2 = this.filter;
        if (filter2 != null) {
            return filter2.replace(str);
        }
        return null;
    }
}
