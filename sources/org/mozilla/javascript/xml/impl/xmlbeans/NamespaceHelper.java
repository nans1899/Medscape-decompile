package org.mozilla.javascript.xml.impl.xmlbeans;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.XmlCursor;
import org.mozilla.javascript.ObjArray;

class NamespaceHelper {
    private XMLLibImpl lib;
    private final Map prefixToURI = new HashMap();
    private final Set undeclared = new HashSet();
    private final Map uriToPrefix = new HashMap();

    private NamespaceHelper(XMLLibImpl xMLLibImpl) {
        this.lib = xMLLibImpl;
        this.prefixToURI.put("", "");
        HashSet hashSet = new HashSet();
        hashSet.add("");
        this.uriToPrefix.put("", hashSet);
    }

    private void declareNamespace(String str, String str2, ObjArray objArray) {
        Set set = (Set) this.uriToPrefix.get(str2);
        if (set == null) {
            set = new HashSet();
            this.uriToPrefix.put(str2, set);
        }
        if (!set.contains(str)) {
            String str3 = (String) this.prefixToURI.get(str);
            set.add(str);
            this.prefixToURI.put(str, str2);
            if (objArray != null) {
                objArray.add(new Namespace(this.lib, str, str2));
            }
            if (str3 != null) {
                ((Set) this.uriToPrefix.get(str3)).remove(str);
            }
        }
    }

    private void processName(XmlCursor xmlCursor, ObjArray objArray) {
        String namespaceURI = xmlCursor.getName().getNamespaceURI();
        Set set = (Set) this.uriToPrefix.get(namespaceURI);
        if (set == null || set.size() == 0) {
            this.undeclared.add(namespaceURI);
            if (objArray != null) {
                objArray.add(new Namespace(this.lib, namespaceURI));
            }
        }
    }

    private void update(XmlCursor xmlCursor, ObjArray objArray) {
        xmlCursor.push();
        while (xmlCursor.toNextToken().isAnyAttr()) {
            if (xmlCursor.isNamespace()) {
                QName name = xmlCursor.getName();
                declareNamespace(name.getLocalPart(), name.getNamespaceURI(), objArray);
            }
        }
        xmlCursor.pop();
        processName(xmlCursor, objArray);
        xmlCursor.push();
        boolean firstAttribute = xmlCursor.toFirstAttribute();
        while (firstAttribute) {
            processName(xmlCursor, objArray);
            firstAttribute = xmlCursor.toNextAttribute();
        }
        xmlCursor.pop();
    }

    public static Object[] inScopeNamespaces(XMLLibImpl xMLLibImpl, XmlCursor xmlCursor) {
        ObjArray objArray = new ObjArray();
        NamespaceHelper namespaceHelper = new NamespaceHelper(xMLLibImpl);
        xmlCursor.push();
        int i = 0;
        while (xmlCursor.hasPrevToken()) {
            if (xmlCursor.isContainer()) {
                xmlCursor.push();
                i++;
            }
            xmlCursor.toParent();
        }
        for (int i2 = 0; i2 < i; i2++) {
            xmlCursor.pop();
            namespaceHelper.update(xmlCursor, (ObjArray) null);
        }
        for (Map.Entry entry : namespaceHelper.prefixToURI.entrySet()) {
            objArray.add(new Namespace(xMLLibImpl, (String) entry.getKey(), (String) entry.getValue()));
        }
        for (String namespace : namespaceHelper.undeclared) {
            objArray.add(new Namespace(xMLLibImpl, namespace));
        }
        xmlCursor.pop();
        return objArray.toArray();
    }

    static Namespace getNamespace(XMLLibImpl xMLLibImpl, XmlCursor xmlCursor, Object[] objArr) {
        String str;
        String str2 = "";
        if (xmlCursor.isProcinst()) {
            str = str2;
        } else {
            QName name = xmlCursor.getName();
            String namespaceURI = name.getNamespaceURI();
            str2 = name.getPrefix();
            str = namespaceURI;
        }
        if (objArr == null) {
            return new Namespace(xMLLibImpl, str2, str);
        }
        Namespace namespace = null;
        int i = 0;
        while (true) {
            if (i == objArr.length) {
                break;
            }
            Namespace namespace2 = objArr[i];
            if (namespace2 != null && namespace2.uri().equals(str)) {
                if (str2.equals(namespace2.prefix())) {
                    namespace = namespace2;
                    break;
                } else if (namespace == null || (namespace.prefix() == null && namespace2.prefix() != null)) {
                    namespace = namespace2;
                }
            }
            i++;
        }
        return namespace == null ? new Namespace(xMLLibImpl, str2, str) : namespace;
    }

    public static Object[] namespaceDeclarations(XMLLibImpl xMLLibImpl, XmlCursor xmlCursor) {
        ObjArray objArray = new ObjArray();
        NamespaceHelper namespaceHelper = new NamespaceHelper(xMLLibImpl);
        xmlCursor.push();
        int i = 0;
        while (xmlCursor.hasPrevToken()) {
            if (xmlCursor.isContainer()) {
                xmlCursor.push();
                i++;
            }
            xmlCursor.toParent();
        }
        for (int i2 = 0; i2 < i - 1; i2++) {
            xmlCursor.pop();
            namespaceHelper.update(xmlCursor, (ObjArray) null);
        }
        if (i > 0) {
            xmlCursor.pop();
            namespaceHelper.update(xmlCursor, objArray);
        }
        xmlCursor.pop();
        return objArray.toArray();
    }

    public static Map getAllNamespaces(XMLLibImpl xMLLibImpl, XmlCursor xmlCursor) {
        NamespaceHelper namespaceHelper = new NamespaceHelper(xMLLibImpl);
        xmlCursor.push();
        int i = 0;
        while (xmlCursor.hasPrevToken()) {
            if (xmlCursor.isContainer()) {
                xmlCursor.push();
                i++;
            }
            xmlCursor.toParent();
        }
        for (int i2 = 0; i2 < i; i2++) {
            xmlCursor.pop();
            namespaceHelper.update(xmlCursor, (ObjArray) null);
        }
        xmlCursor.pop();
        return namespaceHelper.prefixToURI;
    }

    public static void getNamespaces(XmlCursor xmlCursor, Map map) {
        xmlCursor.push();
        while (xmlCursor.toNextToken().isAnyAttr()) {
            if (xmlCursor.isNamespace()) {
                QName name = xmlCursor.getName();
                map.put(name.getLocalPart(), name.getNamespaceURI());
            }
        }
        xmlCursor.pop();
    }

    public static void removeNamespace(XmlCursor xmlCursor, String str) {
        xmlCursor.push();
        while (true) {
            if (xmlCursor.toNextToken().isAnyAttr()) {
                if (xmlCursor.isNamespace() && xmlCursor.getName().getLocalPart().equals(str)) {
                    xmlCursor.removeXml();
                    break;
                }
            } else {
                break;
            }
        }
        xmlCursor.pop();
    }
}
