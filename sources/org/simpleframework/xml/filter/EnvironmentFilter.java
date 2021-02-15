package org.simpleframework.xml.filter;

public class EnvironmentFilter implements Filter {
    private Filter filter;

    public EnvironmentFilter() {
        this((Filter) null);
    }

    public EnvironmentFilter(Filter filter2) {
        this.filter = filter2;
    }

    public String replace(String str) {
        String str2 = System.getenv(str);
        if (str2 != null) {
            return str2;
        }
        Filter filter2 = this.filter;
        if (filter2 != null) {
            return filter2.replace(str);
        }
        return null;
    }
}
