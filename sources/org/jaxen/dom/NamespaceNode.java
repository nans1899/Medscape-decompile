package org.jaxen.dom;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import javax.xml.XMLConstants;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public class NamespaceNode implements Node {
    public static final short NAMESPACE_NODE = 13;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$w3c$dom$Node;
    private String name;
    private Node parent;
    private HashMap userData = new HashMap();
    private String value;

    public NamedNodeMap getAttributes() {
        return null;
    }

    public Object getFeature(String str, String str2) {
        return null;
    }

    public Node getFirstChild() {
        return null;
    }

    public Node getLastChild() {
        return null;
    }

    public String getNamespaceURI() {
        return null;
    }

    public Node getNextSibling() {
        return null;
    }

    public short getNodeType() {
        return 13;
    }

    public String getPrefix() {
        return null;
    }

    public Node getPreviousSibling() {
        return null;
    }

    public boolean hasAttributes() {
        return false;
    }

    public boolean hasChildNodes() {
        return false;
    }

    public boolean isSupported(String str, String str2) {
        return false;
    }

    public void normalize() {
    }

    public NamespaceNode(Node node, String str, String str2) {
        this.parent = node;
        this.name = str;
        this.value = str2;
    }

    NamespaceNode(Node node, Node node2) {
        String nodeName = node2.getNodeName();
        if (nodeName.equals(XMLConstants.XMLNS_ATTRIBUTE)) {
            this.name = "";
        } else if (nodeName.startsWith("xmlns:")) {
            this.name = nodeName.substring(6);
        } else {
            this.name = nodeName;
        }
        this.parent = node;
        this.value = node2.getNodeValue();
    }

    public String getNodeName() {
        return this.name;
    }

    public String getNodeValue() {
        return this.value;
    }

    public void setNodeValue(String str) throws DOMException {
        disallowModification();
    }

    public Node getParentNode() {
        return this.parent;
    }

    public NodeList getChildNodes() {
        return new EmptyNodeList();
    }

    public Document getOwnerDocument() {
        Node node = this.parent;
        if (node == null) {
            return null;
        }
        return node.getOwnerDocument();
    }

    public Node insertBefore(Node node, Node node2) throws DOMException {
        disallowModification();
        return null;
    }

    public Node replaceChild(Node node, Node node2) throws DOMException {
        disallowModification();
        return null;
    }

    public Node removeChild(Node node) throws DOMException {
        disallowModification();
        return null;
    }

    public Node appendChild(Node node) throws DOMException {
        disallowModification();
        return null;
    }

    public Node cloneNode(boolean z) {
        return new NamespaceNode(this.parent, this.name, this.value);
    }

    public void setPrefix(String str) throws DOMException {
        disallowModification();
    }

    public String getLocalName() {
        return this.name;
    }

    private void disallowModification() throws DOMException {
        throw new DOMException(7, "Namespace node may not be modified");
    }

    public int hashCode() {
        return hashCode(this.parent) + hashCode(this.name) + hashCode(this.value);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof NamespaceNode)) {
            return false;
        }
        NamespaceNode namespaceNode = (NamespaceNode) obj;
        if (!equals(this.parent, namespaceNode.getParentNode()) || !equals(this.name, namespaceNode.getNodeName()) || !equals(this.value, namespaceNode.getNodeValue())) {
            return false;
        }
        return true;
    }

    private int hashCode(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    private boolean equals(Object obj, Object obj2) {
        return (obj == null && obj2 == null) || (obj != null && obj.equals(obj2));
    }

    private static class EmptyNodeList implements NodeList {
        public int getLength() {
            return 0;
        }

        public Node item(int i) {
            return null;
        }

        private EmptyNodeList() {
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public String getBaseURI() {
        Class cls = class$org$w3c$dom$Node;
        if (cls == null) {
            cls = class$("org.w3c.dom.Node");
            class$org$w3c$dom$Node = cls;
        }
        try {
            Class[] clsArr = new Class[0];
            return (String) cls.getMethod("getBaseURI", clsArr).invoke(getParentNode(), clsArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public short compareDocumentPosition(Node node) throws DOMException {
        throw new DOMException(9, "DOM level 3 interfaces are not fully implemented in Jaxen's NamespaceNode class");
    }

    public String getTextContent() {
        return this.value;
    }

    public void setTextContent(String str) throws DOMException {
        disallowModification();
    }

    public boolean isSameNode(Node node) {
        boolean z;
        Class cls;
        boolean isEqualNode = isEqualNode(node);
        Node parentNode = getParentNode();
        Node parentNode2 = node.getParentNode();
        try {
            if (class$org$w3c$dom$Node == null) {
                cls = class$("org.w3c.dom.Node");
                class$org$w3c$dom$Node = cls;
            } else {
                cls = class$org$w3c$dom$Node;
            }
            z = ((Boolean) cls.getMethod("isEqual", new Class[]{cls}).invoke(parentNode, new Object[]{parentNode2})).booleanValue();
        } catch (NoSuchMethodException unused) {
            z = parentNode.equals(parentNode2);
        } catch (InvocationTargetException unused2) {
            z = parentNode.equals(parentNode2);
        } catch (IllegalAccessException unused3) {
            z = parentNode.equals(parentNode2);
        }
        if (!isEqualNode || !z) {
            return false;
        }
        return true;
    }

    public String lookupPrefix(String str) {
        Class cls;
        Class cls2;
        try {
            if (class$org$w3c$dom$Node == null) {
                cls = class$("org.w3c.dom.Node");
                class$org$w3c$dom$Node = cls;
            } else {
                cls = class$org$w3c$dom$Node;
            }
            Class[] clsArr = new Class[1];
            if (class$java$lang$String == null) {
                cls2 = class$("java.lang.String");
                class$java$lang$String = cls2;
            } else {
                cls2 = class$java$lang$String;
            }
            clsArr[0] = cls2;
            return (String) cls.getMethod("lookupPrefix", clsArr).invoke(this.parent, new String[]{str});
        } catch (NoSuchMethodException unused) {
            throw new UnsupportedOperationException("Cannot lookup prefixes in DOM 2");
        } catch (InvocationTargetException unused2) {
            throw new UnsupportedOperationException("Cannot lookup prefixes in DOM 2");
        } catch (IllegalAccessException unused3) {
            throw new UnsupportedOperationException("Cannot lookup prefixes in DOM 2");
        }
    }

    public boolean isDefaultNamespace(String str) {
        return str.equals(lookupNamespaceURI((String) null));
    }

    public String lookupNamespaceURI(String str) {
        Class cls;
        Class cls2;
        try {
            if (class$org$w3c$dom$Node == null) {
                cls = class$("org.w3c.dom.Node");
                class$org$w3c$dom$Node = cls;
            } else {
                cls = class$org$w3c$dom$Node;
            }
            Class[] clsArr = new Class[1];
            if (class$java$lang$String == null) {
                cls2 = class$("java.lang.String");
                class$java$lang$String = cls2;
            } else {
                cls2 = class$java$lang$String;
            }
            clsArr[0] = cls2;
            return (String) cls.getMethod("lookupNamespaceURI", clsArr).invoke(this.parent, new String[]{str});
        } catch (NoSuchMethodException unused) {
            throw new UnsupportedOperationException("Cannot lookup namespace URIs in DOM 2");
        } catch (InvocationTargetException unused2) {
            throw new UnsupportedOperationException("Cannot lookup namespace URIs in DOM 2");
        } catch (IllegalAccessException unused3) {
            throw new UnsupportedOperationException("Cannot lookup namespace URIs in DOM 2");
        }
    }

    public boolean isEqualNode(Node node) {
        if (node.getNodeType() != getNodeType()) {
            return false;
        }
        NamespaceNode namespaceNode = (NamespaceNode) node;
        if (namespaceNode.name == null && this.name != null) {
            return false;
        }
        if (namespaceNode.name != null && this.name == null) {
            return false;
        }
        if (namespaceNode.value == null && this.value != null) {
            return false;
        }
        if (namespaceNode.value != null && this.value == null) {
            return false;
        }
        if (namespaceNode.name == null && this.name == null) {
            return namespaceNode.value.equals(this.value);
        }
        if (!namespaceNode.name.equals(this.name) || !namespaceNode.value.equals(this.value)) {
            return false;
        }
        return true;
    }

    public Object setUserData(String str, Object obj, UserDataHandler userDataHandler) {
        Object userData2 = getUserData(str);
        this.userData.put(str, obj);
        return userData2;
    }

    public Object getUserData(String str) {
        return this.userData.get(str);
    }
}
