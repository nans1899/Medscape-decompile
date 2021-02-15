package org.jaxen.expr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.ContextSupport;
import org.jaxen.JaxenException;
import org.jaxen.function.BooleanFunction;

public class PredicateSet implements Serializable {
    private static final long serialVersionUID = -7166491740228977853L;
    private List predicates = Collections.EMPTY_LIST;

    public void addPredicate(Predicate predicate) {
        if (this.predicates == Collections.EMPTY_LIST) {
            this.predicates = new ArrayList();
        }
        this.predicates.add(predicate);
    }

    public List getPredicates() {
        return this.predicates;
    }

    public void simplify() {
        for (Predicate simplify : this.predicates) {
            simplify.simplify();
        }
    }

    public String getText() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Predicate text : this.predicates) {
            stringBuffer.append(text.getText());
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public boolean evaluateAsBoolean(List list, ContextSupport contextSupport) throws JaxenException {
        return anyMatchingNode(list, contextSupport);
    }

    private boolean anyMatchingNode(List list, ContextSupport contextSupport) throws JaxenException {
        if (this.predicates.size() == 0) {
            return false;
        }
        for (Predicate evaluate : this.predicates) {
            int size = list.size();
            Context context = new Context(contextSupport);
            ArrayList arrayList = new ArrayList(1);
            context.setNodeSet(arrayList);
            int i = 0;
            while (true) {
                if (i < size) {
                    Object obj = list.get(i);
                    arrayList.clear();
                    arrayList.add(obj);
                    context.setNodeSet(arrayList);
                    i++;
                    context.setPosition(i);
                    context.setSize(size);
                    Object evaluate2 = evaluate.evaluate(context);
                    if (evaluate2 instanceof Number) {
                        if (((Number) evaluate2).intValue() == i) {
                            return true;
                        }
                    } else if (BooleanFunction.evaluate(evaluate2, context.getNavigator()).booleanValue()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public List evaluatePredicates(List list, ContextSupport contextSupport) throws JaxenException {
        if (this.predicates.size() == 0) {
            return list;
        }
        for (Predicate applyPredicate : this.predicates) {
            list = applyPredicate(applyPredicate, list, contextSupport);
        }
        return list;
    }

    public List applyPredicate(Predicate predicate, List list, ContextSupport contextSupport) throws JaxenException {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        Context context = new Context(contextSupport);
        ArrayList arrayList2 = new ArrayList(1);
        context.setNodeSet(arrayList2);
        int i = 0;
        while (i < size) {
            Object obj = list.get(i);
            arrayList2.clear();
            arrayList2.add(obj);
            context.setNodeSet(arrayList2);
            i++;
            context.setPosition(i);
            context.setSize(size);
            Object evaluate = predicate.evaluate(context);
            if (evaluate instanceof Number) {
                if (((Number) evaluate).intValue() == i) {
                    arrayList.add(obj);
                }
            } else if (BooleanFunction.evaluate(evaluate, context.getNavigator()).booleanValue()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
