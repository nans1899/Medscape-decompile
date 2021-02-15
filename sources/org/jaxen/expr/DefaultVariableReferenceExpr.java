package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.UnresolvableException;

class DefaultVariableReferenceExpr extends DefaultExpr implements VariableReferenceExpr {
    private static final long serialVersionUID = 8832095437149358674L;
    private String localName;
    private String prefix;

    DefaultVariableReferenceExpr(String str, String str2) {
        this.prefix = str;
        this.localName = str2;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getVariableName() {
        return this.localName;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[(DefaultVariableReferenceExpr): ");
        stringBuffer.append(getQName());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    private String getQName() {
        if ("".equals(this.prefix)) {
            return this.localName;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.prefix);
        stringBuffer.append(":");
        stringBuffer.append(this.localName);
        return stringBuffer.toString();
    }

    public String getText() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("$");
        stringBuffer.append(getQName());
        return stringBuffer.toString();
    }

    public Object evaluate(Context context) throws UnresolvableException {
        return context.getVariableValue(context.translateNamespacePrefixToUri(getPrefix()), this.prefix, this.localName);
    }
}
