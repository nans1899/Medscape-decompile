package org.jaxen.expr;

import org.jaxen.ContextSupport;
import org.jaxen.expr.iter.IterableAxis;

public class DefaultCommentNodeStep extends DefaultStep implements CommentNodeStep {
    private static final long serialVersionUID = 4340788283861875606L;

    public DefaultCommentNodeStep(IterableAxis iterableAxis, PredicateSet predicateSet) {
        super(iterableAxis, predicateSet);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(DefaultCommentNodeStep): ");
        stringBuffer.append(getAxis());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public String getText() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getAxisName());
        stringBuffer.append("::comment()");
        return stringBuffer.toString();
    }

    public boolean matches(Object obj, ContextSupport contextSupport) {
        return contextSupport.getNavigator().isComment(obj);
    }
}
