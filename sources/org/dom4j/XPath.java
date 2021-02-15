package org.dom4j;

import java.util.List;
import java.util.Map;
import org.jaxen.FunctionContext;
import org.jaxen.NamespaceContext;
import org.jaxen.VariableContext;

public interface XPath extends NodeFilter {
    boolean booleanValueOf(Object obj);

    Object evaluate(Object obj);

    FunctionContext getFunctionContext();

    NamespaceContext getNamespaceContext();

    String getText();

    VariableContext getVariableContext();

    boolean matches(Node node);

    Number numberValueOf(Object obj);

    List selectNodes(Object obj);

    List selectNodes(Object obj, XPath xPath);

    List selectNodes(Object obj, XPath xPath, boolean z);

    Object selectObject(Object obj);

    Node selectSingleNode(Object obj);

    void setFunctionContext(FunctionContext functionContext);

    void setNamespaceContext(NamespaceContext namespaceContext);

    void setNamespaceURIs(Map map);

    void setVariableContext(VariableContext variableContext);

    void sort(List list);

    void sort(List list, boolean z);

    String valueOf(Object obj);
}
