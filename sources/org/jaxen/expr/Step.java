package org.jaxen.expr;

import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.ContextSupport;
import org.jaxen.JaxenException;
import org.jaxen.UnsupportedAxisException;

public interface Step extends Predicated {
    Iterator axisIterator(Object obj, ContextSupport contextSupport) throws UnsupportedAxisException;

    List evaluate(Context context) throws JaxenException;

    int getAxis();

    String getText();

    boolean matches(Object obj, ContextSupport contextSupport) throws JaxenException;

    void simplify();
}
