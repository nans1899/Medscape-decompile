package org.jaxen.dom4j;

import org.jaxen.BaseXPath;
import org.jaxen.JaxenException;

public class Dom4jXPath extends BaseXPath {
    private static final long serialVersionUID = -75510941087659775L;

    public Dom4jXPath(String str) throws JaxenException {
        super(str, DocumentNavigator.getInstance());
    }
}
