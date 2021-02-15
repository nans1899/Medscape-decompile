package org.jaxen.saxpath.helpers;

import org.jaxen.saxpath.SAXPathException;
import org.jaxen.saxpath.XPathReader;

public class XPathReaderFactory {
    protected static final String DEFAULT_DRIVER = "org.jaxen.saxpath.base.XPathReader";
    public static final String DRIVER_PROPERTY = "org.saxpath.driver";
    static /* synthetic */ Class class$org$jaxen$saxpath$XPathReader;
    static /* synthetic */ Class class$org$jaxen$saxpath$helpers$XPathReaderFactory;

    private XPathReaderFactory() {
    }

    public static XPathReader createReader() throws SAXPathException {
        String str;
        try {
            str = System.getProperty(DRIVER_PROPERTY);
        } catch (SecurityException unused) {
            str = null;
        }
        if (str == null || str.length() == 0) {
            str = DEFAULT_DRIVER;
        }
        return createReader(str);
    }

    public static XPathReader createReader(String str) throws SAXPathException {
        Class cls;
        Class cls2;
        try {
            if (class$org$jaxen$saxpath$helpers$XPathReaderFactory == null) {
                cls = class$("org.jaxen.saxpath.helpers.XPathReaderFactory");
                class$org$jaxen$saxpath$helpers$XPathReaderFactory = cls;
            } else {
                cls = class$org$jaxen$saxpath$helpers$XPathReaderFactory;
            }
            Class<?> cls3 = Class.forName(str, true, cls.getClassLoader());
            if (class$org$jaxen$saxpath$XPathReader == null) {
                cls2 = class$("org.jaxen.saxpath.XPathReader");
                class$org$jaxen$saxpath$XPathReader = cls2;
            } else {
                cls2 = class$org$jaxen$saxpath$XPathReader;
            }
            if (cls2.isAssignableFrom(cls3)) {
                try {
                    return (XPathReader) cls3.newInstance();
                } catch (IllegalAccessException e) {
                    throw new SAXPathException((Throwable) e);
                } catch (InstantiationException e2) {
                    throw new SAXPathException((Throwable) e2);
                }
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Class [");
                stringBuffer.append(str);
                stringBuffer.append("] does not implement the org.jaxen.saxpath.XPathReader interface.");
                throw new SAXPathException(stringBuffer.toString());
            }
        } catch (ClassNotFoundException e3) {
            throw new SAXPathException((Throwable) e3);
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }
}
