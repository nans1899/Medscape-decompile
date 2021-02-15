package org.jaxen.jdom;

import org.jaxen.BaseXPath;
import org.jaxen.JaxenException;

public class JDOMXPath extends BaseXPath {
    private static final long serialVersionUID = 6426091824802286928L;

    public JDOMXPath(String str) throws JaxenException {
        super(str, DocumentNavigator.getInstance());
    }
}
