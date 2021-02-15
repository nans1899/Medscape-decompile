package org.dom4j.io;

import org.dom4j.Element;

public interface ElementModifier {
    Element modifyElement(Element element) throws Exception;
}
