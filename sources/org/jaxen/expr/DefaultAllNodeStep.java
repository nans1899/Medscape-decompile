package org.jaxen.expr;

import org.jaxen.ContextSupport;
import org.jaxen.expr.iter.IterableAxis;

public class DefaultAllNodeStep extends DefaultStep implements AllNodeStep {
    private static final long serialVersionUID = 292886316770123856L;

    public boolean matches(Object obj, ContextSupport contextSupport) {
        return true;
    }

    public DefaultAllNodeStep(IterableAxis iterableAxis, PredicateSet predicateSet) {
        super(iterableAxis, predicateSet);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(DefaultAllNodeStep): ");
        stringBuffer.append(getAxisName());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public String getText() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getAxisName());
        stringBuffer.append("::node()");
        stringBuffer.append(super.getText());
        return stringBuffer.toString();
    }
}
