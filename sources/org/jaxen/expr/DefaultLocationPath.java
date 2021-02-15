package org.jaxen.expr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.ContextSupport;
import org.jaxen.JaxenException;

abstract class DefaultLocationPath extends DefaultExpr implements LocationPath {
    private List steps = new LinkedList();

    public boolean isAbsolute() {
        return false;
    }

    DefaultLocationPath() {
    }

    public void addStep(Step step) {
        getSteps().add(step);
    }

    public List getSteps() {
        return this.steps;
    }

    public Expr simplify() {
        for (Step simplify : getSteps()) {
            simplify.simplify();
        }
        return this;
    }

    public String getText() {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = getSteps().iterator();
        while (it.hasNext()) {
            stringBuffer.append(((Step) it.next()).getText());
            if (it.hasNext()) {
                stringBuffer.append("/");
            }
        }
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = getSteps().iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next().toString());
            if (it.hasNext()) {
                stringBuffer.append("/");
            }
        }
        return stringBuffer.toString();
    }

    public Object evaluate(Context context) throws JaxenException {
        List nodeSet = context.getNodeSet();
        List arrayList = new ArrayList(nodeSet);
        ContextSupport contextSupport = context.getContextSupport();
        Context context2 = new Context(contextSupport);
        for (Step step : getSteps()) {
            context2.setNodeSet(arrayList);
            arrayList = step.evaluate(context2);
            if (isReverseAxis(step)) {
                Collections.reverse(arrayList);
            }
        }
        if (getSteps().size() > 1 || nodeSet.size() > 1) {
            Collections.sort(arrayList, new NodeComparator(contextSupport.getNavigator()));
        }
        return arrayList;
    }

    private boolean isReverseAxis(Step step) {
        int axis = step.getAxis();
        return axis == 8 || axis == 6 || axis == 4 || axis == 13;
    }
}
