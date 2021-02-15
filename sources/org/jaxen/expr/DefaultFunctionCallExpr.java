package org.jaxen.expr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.JaxenException;

public class DefaultFunctionCallExpr extends DefaultExpr implements FunctionCallExpr {
    private static final long serialVersionUID = -4747789292572193708L;
    private String functionName;
    private List parameters = new ArrayList();
    private String prefix;

    public DefaultFunctionCallExpr(String str, String str2) {
        this.prefix = str;
        this.functionName = str2;
    }

    public void addParameter(Expr expr) {
        this.parameters.add(expr);
    }

    public List getParameters() {
        return this.parameters;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getFunctionName() {
        return this.functionName;
    }

    public String getText() {
        StringBuffer stringBuffer = new StringBuffer();
        String prefix2 = getPrefix();
        if (prefix2 != null && prefix2.length() > 0) {
            stringBuffer.append(prefix2);
            stringBuffer.append(":");
        }
        stringBuffer.append(getFunctionName());
        stringBuffer.append("(");
        Iterator it = getParameters().iterator();
        while (it.hasNext()) {
            stringBuffer.append(((Expr) it.next()).getText());
            if (it.hasNext()) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    public Expr simplify() {
        List parameters2 = getParameters();
        int size = parameters2.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(((Expr) parameters2.get(i)).simplify());
        }
        this.parameters = arrayList;
        return this;
    }

    public String toString() {
        if (getPrefix() == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[(DefaultFunctionCallExpr): ");
            stringBuffer.append(getFunctionName());
            stringBuffer.append("(");
            stringBuffer.append(getParameters());
            stringBuffer.append(") ]");
            return stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("[(DefaultFunctionCallExpr): ");
        stringBuffer2.append(getPrefix());
        stringBuffer2.append(":");
        stringBuffer2.append(getFunctionName());
        stringBuffer2.append("(");
        stringBuffer2.append(getParameters());
        stringBuffer2.append(") ]");
        return stringBuffer2.toString();
    }

    public Object evaluate(Context context) throws JaxenException {
        return context.getFunction(context.translateNamespacePrefixToUri(getPrefix()), getPrefix(), getFunctionName()).call(context, evaluateParams(context));
    }

    public List evaluateParams(Context context) throws JaxenException {
        List parameters2 = getParameters();
        int size = parameters2.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(((Expr) parameters2.get(i)).evaluate(context));
        }
        return arrayList;
    }
}
