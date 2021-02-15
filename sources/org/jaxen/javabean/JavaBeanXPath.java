package org.jaxen.javabean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jaxen.BaseXPath;
import org.jaxen.Context;
import org.jaxen.JaxenException;

public class JavaBeanXPath extends BaseXPath {
    private static final long serialVersionUID = -1567521943360266313L;

    public JavaBeanXPath(String str) throws JaxenException {
        super(str, DocumentNavigator.getInstance());
    }

    /* access modifiers changed from: protected */
    public Context getContext(Object obj) {
        if (obj instanceof Context) {
            return (Context) obj;
        }
        if (obj instanceof Element) {
            return super.getContext(obj);
        }
        if (!(obj instanceof List)) {
            return super.getContext(new Element((Element) null, "root", obj));
        }
        ArrayList arrayList = new ArrayList();
        for (Object element : (List) obj) {
            arrayList.add(new Element((Element) null, "root", element));
        }
        return super.getContext(arrayList);
    }

    public Object evaluate(Object obj) throws JaxenException {
        Object evaluate = super.evaluate(obj);
        if (evaluate instanceof Element) {
            return ((Element) evaluate).getObject();
        }
        if (!(evaluate instanceof Collection)) {
            return evaluate;
        }
        ArrayList arrayList = new ArrayList();
        for (Object next : (Collection) evaluate) {
            if (next instanceof Element) {
                arrayList.add(((Element) next).getObject());
            } else {
                arrayList.add(next);
            }
        }
        return arrayList;
    }
}
