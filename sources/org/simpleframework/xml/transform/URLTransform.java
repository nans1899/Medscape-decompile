package org.simpleframework.xml.transform;

import java.net.URL;

class URLTransform implements Transform<URL> {
    URLTransform() {
    }

    public URL read(String str) throws Exception {
        return new URL(str);
    }

    public String write(URL url) throws Exception {
        return url.toString();
    }
}
