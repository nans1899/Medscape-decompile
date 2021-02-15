package org.jaxen.saxpath;

public interface XPathReader extends SAXPathEventSource {
    void parse(String str) throws SAXPathException;
}
