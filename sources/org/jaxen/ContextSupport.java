package org.jaxen;

import java.io.Serializable;
import javax.xml.XMLConstants;

public class ContextSupport implements Serializable {
    private static final long serialVersionUID = 4494082174713652559L;
    private transient FunctionContext functionContext;
    private NamespaceContext namespaceContext;
    private Navigator navigator;
    private VariableContext variableContext;

    public ContextSupport() {
    }

    public ContextSupport(NamespaceContext namespaceContext2, FunctionContext functionContext2, VariableContext variableContext2, Navigator navigator2) {
        setNamespaceContext(namespaceContext2);
        setFunctionContext(functionContext2);
        setVariableContext(variableContext2);
        this.navigator = navigator2;
    }

    public void setNamespaceContext(NamespaceContext namespaceContext2) {
        this.namespaceContext = namespaceContext2;
    }

    public NamespaceContext getNamespaceContext() {
        return this.namespaceContext;
    }

    public void setFunctionContext(FunctionContext functionContext2) {
        this.functionContext = functionContext2;
    }

    public FunctionContext getFunctionContext() {
        return this.functionContext;
    }

    public void setVariableContext(VariableContext variableContext2) {
        this.variableContext = variableContext2;
    }

    public VariableContext getVariableContext() {
        return this.variableContext;
    }

    public Navigator getNavigator() {
        return this.navigator;
    }

    public String translateNamespacePrefixToUri(String str) {
        if ("xml".equals(str)) {
            return XMLConstants.XML_NS_URI;
        }
        NamespaceContext namespaceContext2 = getNamespaceContext();
        if (namespaceContext2 != null) {
            return namespaceContext2.translateNamespacePrefixToUri(str);
        }
        return null;
    }

    public Object getVariableValue(String str, String str2, String str3) throws UnresolvableException {
        VariableContext variableContext2 = getVariableContext();
        if (variableContext2 != null) {
            return variableContext2.getVariableValue(str, str2, str3);
        }
        throw new UnresolvableException("No variable context installed");
    }

    public Function getFunction(String str, String str2, String str3) throws UnresolvableException {
        FunctionContext functionContext2 = getFunctionContext();
        if (functionContext2 != null) {
            return functionContext2.getFunction(str, str2, str3);
        }
        throw new UnresolvableException("No function context installed");
    }
}
