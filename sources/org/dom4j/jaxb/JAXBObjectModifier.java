package org.dom4j.jaxb;

import javax.xml.bind.Element;

public interface JAXBObjectModifier {
    Element modifyObject(Element element) throws Exception;
}
