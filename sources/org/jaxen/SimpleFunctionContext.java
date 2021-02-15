package org.jaxen;

import java.util.HashMap;

public class SimpleFunctionContext implements FunctionContext {
    private HashMap functions = new HashMap();

    public void registerFunction(String str, String str2, Function function) {
        this.functions.put(new QualifiedName(str, str2), function);
    }

    public Function getFunction(String str, String str2, String str3) throws UnresolvableException {
        QualifiedName qualifiedName = new QualifiedName(str, str3);
        if (this.functions.containsKey(qualifiedName)) {
            return (Function) this.functions.get(qualifiedName);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("No Such Function ");
        stringBuffer.append(qualifiedName.getClarkForm());
        throw new UnresolvableException(stringBuffer.toString());
    }
}
