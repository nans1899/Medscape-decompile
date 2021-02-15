package org.jaxen.dom;

import org.jaxen.BaseXPath;
import org.jaxen.JaxenException;

public class DOMXPath extends BaseXPath {
    private static final long serialVersionUID = 5551221776001439091L;

    public DOMXPath(String str) throws JaxenException {
        super(str, DocumentNavigator.getInstance());
    }
}
