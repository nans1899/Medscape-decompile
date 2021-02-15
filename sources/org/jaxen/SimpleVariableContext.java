package org.jaxen;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SimpleVariableContext implements VariableContext, Serializable {
    private static final long serialVersionUID = 961322093794516518L;
    private Map variables = new HashMap();

    public void setVariableValue(String str, String str2, Object obj) {
        this.variables.put(new QualifiedName(str, str2), obj);
    }

    public void setVariableValue(String str, Object obj) {
        this.variables.put(new QualifiedName((String) null, str), obj);
    }

    public Object getVariableValue(String str, String str2, String str3) throws UnresolvableException {
        QualifiedName qualifiedName = new QualifiedName(str, str3);
        if (this.variables.containsKey(qualifiedName)) {
            return this.variables.get(qualifiedName);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Variable ");
        stringBuffer.append(qualifiedName.getClarkForm());
        throw new UnresolvableException(stringBuffer.toString());
    }
}
