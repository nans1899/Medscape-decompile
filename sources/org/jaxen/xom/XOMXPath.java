package org.jaxen.xom;

import org.jaxen.BaseXPath;
import org.jaxen.JaxenException;

public class XOMXPath extends BaseXPath {
    private static final long serialVersionUID = -5332108546921857671L;

    public XOMXPath(String str) throws JaxenException {
        super(str, new DocumentNavigator());
    }
}
