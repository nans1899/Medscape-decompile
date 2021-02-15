package org.simpleframework.xml.stream;

class IdentityStyle implements Style {
    public String getAttribute(String str) {
        return str;
    }

    public String getElement(String str) {
        return str;
    }

    IdentityStyle() {
    }
}
