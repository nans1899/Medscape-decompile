package org.simpleframework.xml.core;

import org.simpleframework.xml.filter.Filter;

class TemplateFilter implements Filter {
    private Context context;
    private Filter filter;

    public TemplateFilter(Context context2, Filter filter2) {
        this.context = context2;
        this.filter = filter2;
    }

    public String replace(String str) {
        Object attribute = this.context.getAttribute(str);
        if (attribute != null) {
            return attribute.toString();
        }
        return this.filter.replace(str);
    }
}
