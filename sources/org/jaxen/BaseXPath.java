package org.jaxen;

import java.io.Serializable;
import java.util.List;
import org.jaxen.expr.Expr;
import org.jaxen.expr.XPathExpr;
import org.jaxen.function.BooleanFunction;
import org.jaxen.function.NumberFunction;
import org.jaxen.function.StringFunction;
import org.jaxen.saxpath.SAXPathException;
import org.jaxen.saxpath.XPathReader;
import org.jaxen.saxpath.XPathSyntaxException;
import org.jaxen.saxpath.helpers.XPathReaderFactory;
import org.jaxen.util.SingletonList;

public class BaseXPath implements XPath, Serializable {
    private static final long serialVersionUID = -1993731281300293168L;
    private final String exprText;
    private Navigator navigator;
    private ContextSupport support;
    private final XPathExpr xpath;

    protected BaseXPath(String str) throws JaxenException {
        try {
            XPathReader createReader = XPathReaderFactory.createReader();
            JaxenHandler jaxenHandler = new JaxenHandler();
            createReader.setXPathHandler(jaxenHandler);
            createReader.parse(str);
            this.xpath = jaxenHandler.getXPathExpr();
            this.exprText = str;
        } catch (XPathSyntaxException e) {
            throw new XPathSyntaxException(e);
        } catch (SAXPathException e2) {
            throw new JaxenException((Throwable) e2);
        }
    }

    public BaseXPath(String str, Navigator navigator2) throws JaxenException {
        this(str);
        this.navigator = navigator2;
    }

    public Object evaluate(Object obj) throws JaxenException {
        List selectNodes = selectNodes(obj);
        if (selectNodes != null && selectNodes.size() == 1) {
            Object obj2 = selectNodes.get(0);
            if ((obj2 instanceof String) || (obj2 instanceof Number) || (obj2 instanceof Boolean)) {
                return obj2;
            }
        }
        return selectNodes;
    }

    public List selectNodes(Object obj) throws JaxenException {
        return selectNodesForContext(getContext(obj));
    }

    public Object selectSingleNode(Object obj) throws JaxenException {
        List selectNodes = selectNodes(obj);
        if (selectNodes.isEmpty()) {
            return null;
        }
        return selectNodes.get(0);
    }

    public String valueOf(Object obj) throws JaxenException {
        return stringValueOf(obj);
    }

    public String stringValueOf(Object obj) throws JaxenException {
        Context context = getContext(obj);
        Object selectSingleNodeForContext = selectSingleNodeForContext(context);
        if (selectSingleNodeForContext == null) {
            return "";
        }
        return StringFunction.evaluate(selectSingleNodeForContext, context.getNavigator());
    }

    public boolean booleanValueOf(Object obj) throws JaxenException {
        Context context = getContext(obj);
        List selectNodesForContext = selectNodesForContext(context);
        if (selectNodesForContext == null) {
            return false;
        }
        return BooleanFunction.evaluate(selectNodesForContext, context.getNavigator()).booleanValue();
    }

    public Number numberValueOf(Object obj) throws JaxenException {
        Context context = getContext(obj);
        return NumberFunction.evaluate(selectSingleNodeForContext(context), context.getNavigator());
    }

    public void addNamespace(String str, String str2) throws JaxenException {
        NamespaceContext namespaceContext = getNamespaceContext();
        if (namespaceContext instanceof SimpleNamespaceContext) {
            ((SimpleNamespaceContext) namespaceContext).addNamespace(str, str2);
            return;
        }
        throw new JaxenException("Operation not permitted while using a non-simple namespace context.");
    }

    public void setNamespaceContext(NamespaceContext namespaceContext) {
        getContextSupport().setNamespaceContext(namespaceContext);
    }

    public void setFunctionContext(FunctionContext functionContext) {
        getContextSupport().setFunctionContext(functionContext);
    }

    public void setVariableContext(VariableContext variableContext) {
        getContextSupport().setVariableContext(variableContext);
    }

    public NamespaceContext getNamespaceContext() {
        return getContextSupport().getNamespaceContext();
    }

    public FunctionContext getFunctionContext() {
        return getContextSupport().getFunctionContext();
    }

    public VariableContext getVariableContext() {
        return getContextSupport().getVariableContext();
    }

    public Expr getRootExpr() {
        return this.xpath.getRootExpr();
    }

    public String toString() {
        return this.exprText;
    }

    public String debug() {
        return this.xpath.toString();
    }

    /* access modifiers changed from: protected */
    public Context getContext(Object obj) {
        if (obj instanceof Context) {
            return (Context) obj;
        }
        Context context = new Context(getContextSupport());
        if (obj instanceof List) {
            context.setNodeSet((List) obj);
        } else {
            context.setNodeSet(new SingletonList(obj));
        }
        return context;
    }

    /* access modifiers changed from: protected */
    public ContextSupport getContextSupport() {
        if (this.support == null) {
            this.support = new ContextSupport(createNamespaceContext(), createFunctionContext(), createVariableContext(), getNavigator());
        }
        return this.support;
    }

    public Navigator getNavigator() {
        return this.navigator;
    }

    /* access modifiers changed from: protected */
    public FunctionContext createFunctionContext() {
        return XPathFunctionContext.getInstance();
    }

    /* access modifiers changed from: protected */
    public NamespaceContext createNamespaceContext() {
        return new SimpleNamespaceContext();
    }

    /* access modifiers changed from: protected */
    public VariableContext createVariableContext() {
        return new SimpleVariableContext();
    }

    /* access modifiers changed from: protected */
    public List selectNodesForContext(Context context) throws JaxenException {
        return this.xpath.asList(context);
    }

    /* access modifiers changed from: protected */
    public Object selectSingleNodeForContext(Context context) throws JaxenException {
        List selectNodesForContext = selectNodesForContext(context);
        if (selectNodesForContext.isEmpty()) {
            return null;
        }
        return selectNodesForContext.get(0);
    }
}
