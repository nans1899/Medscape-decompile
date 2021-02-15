package org.jaxen.expr;

import org.jaxen.ContextSupport;
import org.jaxen.expr.iter.IterableAxis;

public class DefaultTextNodeStep extends DefaultStep implements TextNodeStep {
    private static final long serialVersionUID = -3821960984972022948L;

    public DefaultTextNodeStep(IterableAxis iterableAxis, PredicateSet predicateSet) {
        super(iterableAxis, predicateSet);
    }

    public boolean matches(Object obj, ContextSupport contextSupport) {
        return contextSupport.getNavigator().isText(obj);
    }

    public String getText() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getAxisName());
        stringBuffer.append("::text()");
        stringBuffer.append(super.getText());
        return stringBuffer.toString();
    }
}
