package org.jaxen.expr;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.ContextSupport;
import org.jaxen.JaxenException;
import org.jaxen.UnsupportedAxisException;
import org.jaxen.expr.iter.IterableAxis;
import org.jaxen.saxpath.Axis;

public abstract class DefaultStep implements Step {
    private IterableAxis axis;
    private PredicateSet predicates;

    public DefaultStep(IterableAxis iterableAxis, PredicateSet predicateSet) {
        this.axis = iterableAxis;
        this.predicates = predicateSet;
    }

    public void addPredicate(Predicate predicate) {
        this.predicates.addPredicate(predicate);
    }

    public List getPredicates() {
        return this.predicates.getPredicates();
    }

    public PredicateSet getPredicateSet() {
        return this.predicates;
    }

    public int getAxis() {
        return this.axis.value();
    }

    public IterableAxis getIterableAxis() {
        return this.axis;
    }

    public String getAxisName() {
        return Axis.lookup(getAxis());
    }

    public String getText() {
        return this.predicates.getText();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getIterableAxis());
        stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        stringBuffer.append(super.toString());
        return stringBuffer.toString();
    }

    public void simplify() {
        this.predicates.simplify();
    }

    public Iterator axisIterator(Object obj, ContextSupport contextSupport) throws UnsupportedAxisException {
        return getIterableAxis().iterator(obj, contextSupport);
    }

    public List evaluate(Context context) throws JaxenException {
        List nodeSet = context.getNodeSet();
        IdentitySet identitySet = new IdentitySet();
        int size = nodeSet.size();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ContextSupport contextSupport = context.getContextSupport();
        for (int i = 0; i < size; i++) {
            Iterator it = this.axis.iterator(nodeSet.get(i), contextSupport);
            while (it.hasNext()) {
                Object next = it.next();
                if (!identitySet.contains(next) && matches(next, contextSupport)) {
                    identitySet.add(next);
                    arrayList.add(next);
                }
            }
            arrayList2.addAll(getPredicateSet().evaluatePredicates(arrayList, contextSupport));
            arrayList.clear();
        }
        return arrayList2;
    }
}
