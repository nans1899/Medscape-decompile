package org.jaxen;

import java.util.List;

public interface XPath {
    void addNamespace(String str, String str2) throws JaxenException;

    boolean booleanValueOf(Object obj) throws JaxenException;

    Object evaluate(Object obj) throws JaxenException;

    FunctionContext getFunctionContext();

    NamespaceContext getNamespaceContext();

    Navigator getNavigator();

    VariableContext getVariableContext();

    Number numberValueOf(Object obj) throws JaxenException;

    List selectNodes(Object obj) throws JaxenException;

    Object selectSingleNode(Object obj) throws JaxenException;

    void setFunctionContext(FunctionContext functionContext);

    void setNamespaceContext(NamespaceContext namespaceContext);

    void setVariableContext(VariableContext variableContext);

    String stringValueOf(Object obj) throws JaxenException;

    String valueOf(Object obj) throws JaxenException;
}
