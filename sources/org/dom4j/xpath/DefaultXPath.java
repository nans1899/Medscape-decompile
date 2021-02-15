package org.dom4j.xpath;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.InvalidXPathException;
import org.dom4j.Node;
import org.dom4j.NodeFilter;
import org.dom4j.XPath;
import org.dom4j.XPathException;
import org.jaxen.FunctionContext;
import org.jaxen.JaxenException;
import org.jaxen.NamespaceContext;
import org.jaxen.SimpleNamespaceContext;
import org.jaxen.VariableContext;
import org.jaxen.dom4j.Dom4jXPath;

public class DefaultXPath implements XPath, NodeFilter, Serializable {
    private NamespaceContext namespaceContext;
    private String text;
    private org.jaxen.XPath xpath;

    public DefaultXPath(String str) throws InvalidXPathException {
        this.text = str;
        this.xpath = parse(str);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("[XPath: ");
        stringBuffer.append(this.xpath);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public String getText() {
        return this.text;
    }

    public FunctionContext getFunctionContext() {
        return this.xpath.getFunctionContext();
    }

    public void setFunctionContext(FunctionContext functionContext) {
        this.xpath.setFunctionContext(functionContext);
    }

    public NamespaceContext getNamespaceContext() {
        return this.namespaceContext;
    }

    public void setNamespaceURIs(Map map) {
        setNamespaceContext(new SimpleNamespaceContext(map));
    }

    public void setNamespaceContext(NamespaceContext namespaceContext2) {
        this.namespaceContext = namespaceContext2;
        this.xpath.setNamespaceContext(namespaceContext2);
    }

    public VariableContext getVariableContext() {
        return this.xpath.getVariableContext();
    }

    public void setVariableContext(VariableContext variableContext) {
        this.xpath.setVariableContext(variableContext);
    }

    public Object evaluate(Object obj) {
        try {
            setNSContext(obj);
            List selectNodes = this.xpath.selectNodes(obj);
            return (selectNodes == null || selectNodes.size() != 1) ? selectNodes : selectNodes.get(0);
        } catch (JaxenException e) {
            handleJaxenException(e);
            return null;
        }
    }

    public Object selectObject(Object obj) {
        return evaluate(obj);
    }

    public List selectNodes(Object obj) {
        try {
            setNSContext(obj);
            return this.xpath.selectNodes(obj);
        } catch (JaxenException e) {
            handleJaxenException(e);
            return Collections.EMPTY_LIST;
        }
    }

    public List selectNodes(Object obj, XPath xPath) {
        List selectNodes = selectNodes(obj);
        xPath.sort(selectNodes);
        return selectNodes;
    }

    public List selectNodes(Object obj, XPath xPath, boolean z) {
        List selectNodes = selectNodes(obj);
        xPath.sort(selectNodes, z);
        return selectNodes;
    }

    public Node selectSingleNode(Object obj) {
        try {
            setNSContext(obj);
            Object selectSingleNode = this.xpath.selectSingleNode(obj);
            if (selectSingleNode instanceof Node) {
                return (Node) selectSingleNode;
            }
            if (selectSingleNode == null) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer("The result of the XPath expression is not a Node. It was: ");
            stringBuffer.append(selectSingleNode);
            stringBuffer.append(" of type: ");
            stringBuffer.append(selectSingleNode.getClass().getName());
            throw new XPathException(stringBuffer.toString());
        } catch (JaxenException e) {
            handleJaxenException(e);
            return null;
        }
    }

    public String valueOf(Object obj) {
        try {
            setNSContext(obj);
            return this.xpath.stringValueOf(obj);
        } catch (JaxenException e) {
            handleJaxenException(e);
            return "";
        }
    }

    public Number numberValueOf(Object obj) {
        try {
            setNSContext(obj);
            return this.xpath.numberValueOf(obj);
        } catch (JaxenException e) {
            handleJaxenException(e);
            return null;
        }
    }

    public boolean booleanValueOf(Object obj) {
        try {
            setNSContext(obj);
            return this.xpath.booleanValueOf(obj);
        } catch (JaxenException e) {
            handleJaxenException(e);
            return false;
        }
    }

    public void sort(List list) {
        sort(list, false);
    }

    public void sort(List list, boolean z) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            HashMap hashMap = new HashMap(size);
            for (int i = 0; i < size; i++) {
                Object obj = list.get(i);
                if (obj instanceof Node) {
                    Node node = (Node) obj;
                    hashMap.put(node, getCompareValue(node));
                }
            }
            sort(list, (Map) hashMap);
            if (z) {
                removeDuplicates(list, hashMap);
            }
        }
    }

    public boolean matches(Node node) {
        try {
            setNSContext(node);
            List selectNodes = this.xpath.selectNodes(node);
            if (selectNodes == null || selectNodes.size() <= 0) {
                return false;
            }
            Object obj = selectNodes.get(0);
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue();
            }
            return selectNodes.contains(node);
        } catch (JaxenException e) {
            handleJaxenException(e);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void sort(List list, final Map map) {
        Collections.sort(list, new Comparator() {
            public int compare(Object obj, Object obj2) {
                Object obj3 = map.get(obj);
                Object obj4 = map.get(obj2);
                if (obj3 == obj4) {
                    return 0;
                }
                if (obj3 instanceof Comparable) {
                    return ((Comparable) obj3).compareTo(obj4);
                }
                if (obj3 == null) {
                    return 1;
                }
                if (obj4 != null && obj3.equals(obj4)) {
                    return 0;
                }
                return -1;
            }
        });
    }

    /* access modifiers changed from: protected */
    public void removeDuplicates(List list, Map map) {
        HashSet hashSet = new HashSet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Object obj = map.get(it.next());
            if (hashSet.contains(obj)) {
                it.remove();
            } else {
                hashSet.add(obj);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Object getCompareValue(Node node) {
        return valueOf(node);
    }

    protected static org.jaxen.XPath parse(String str) {
        try {
            return new Dom4jXPath(str);
        } catch (JaxenException e) {
            throw new InvalidXPathException(str, e.getMessage());
        } catch (Throwable th) {
            throw new InvalidXPathException(str, th);
        }
    }

    /* access modifiers changed from: protected */
    public void setNSContext(Object obj) {
        if (this.namespaceContext == null) {
            this.xpath.setNamespaceContext(DefaultNamespaceContext.create(obj));
        }
    }

    /* access modifiers changed from: protected */
    public void handleJaxenException(JaxenException jaxenException) throws XPathException {
        throw new XPathException(this.text, (Exception) jaxenException);
    }
}
