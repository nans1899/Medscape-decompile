package org.dom4j.jaxb;

import javax.xml.bind.Element;

public interface JAXBObjectHandler {
    void handleObject(Element element) throws Exception;
}
