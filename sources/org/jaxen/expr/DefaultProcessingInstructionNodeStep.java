package org.jaxen.expr;

import org.jaxen.ContextSupport;
import org.jaxen.Navigator;
import org.jaxen.expr.iter.IterableAxis;

public class DefaultProcessingInstructionNodeStep extends DefaultStep implements ProcessingInstructionNodeStep {
    private static final long serialVersionUID = -4825000697808126927L;
    private String name;

    public DefaultProcessingInstructionNodeStep(IterableAxis iterableAxis, String str, PredicateSet predicateSet) {
        super(iterableAxis, predicateSet);
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public String getText() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getAxisName());
        stringBuffer.append("::processing-instruction(");
        String name2 = getName();
        if (!(name2 == null || name2.length() == 0)) {
            stringBuffer.append("'");
            stringBuffer.append(name2);
            stringBuffer.append("'");
        }
        stringBuffer.append(")");
        stringBuffer.append(super.getText());
        return stringBuffer.toString();
    }

    public boolean matches(Object obj, ContextSupport contextSupport) {
        Navigator navigator = contextSupport.getNavigator();
        if (!navigator.isProcessingInstruction(obj)) {
            return false;
        }
        String name2 = getName();
        if (name2 == null || name2.length() == 0) {
            return true;
        }
        return name2.equals(navigator.getProcessingInstructionTarget(obj));
    }
}
