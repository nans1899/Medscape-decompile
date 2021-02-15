package org.simpleframework.xml.stream;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class Builder implements Style {
    private final Cache<String> attributes = new ConcurrentCache();
    private final Cache<String> elements = new ConcurrentCache();
    private final Style style;

    public Builder(Style style2) {
        this.style = style2;
    }

    public String getAttribute(String str) {
        String fetch = this.attributes.fetch(str);
        if (fetch != null) {
            return fetch;
        }
        String attribute = this.style.getAttribute(str);
        if (attribute != null) {
            this.attributes.cache(str, attribute);
        }
        return attribute;
    }

    public String getElement(String str) {
        String fetch = this.elements.fetch(str);
        if (fetch != null) {
            return fetch;
        }
        String element = this.style.getElement(str);
        if (element != null) {
            this.elements.cache(str, element);
        }
        return element;
    }

    public void setAttribute(String str, String str2) {
        this.attributes.cache(str, str2);
    }

    public void setElement(String str, String str2) {
        this.elements.cache(str, str2);
    }
}
