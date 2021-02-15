package org.dom4j.tree;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.dom4j.DocumentFactory;
import org.dom4j.Namespace;
import org.dom4j.QName;

public class NamespaceStack {
    private Map currentNamespaceCache;
    private Namespace defaultNamespace;
    private DocumentFactory documentFactory;
    private ArrayList namespaceCacheList;
    private ArrayList namespaceStack;
    private Map rootNamespaceCache;

    public NamespaceStack() {
        this.namespaceStack = new ArrayList();
        this.namespaceCacheList = new ArrayList();
        this.rootNamespaceCache = new HashMap();
        this.documentFactory = DocumentFactory.getInstance();
    }

    public NamespaceStack(DocumentFactory documentFactory2) {
        this.namespaceStack = new ArrayList();
        this.namespaceCacheList = new ArrayList();
        this.rootNamespaceCache = new HashMap();
        this.documentFactory = documentFactory2;
    }

    public void push(Namespace namespace) {
        this.namespaceStack.add(namespace);
        this.namespaceCacheList.add((Object) null);
        this.currentNamespaceCache = null;
        String prefix = namespace.getPrefix();
        if (prefix == null || prefix.length() == 0) {
            this.defaultNamespace = namespace;
        }
    }

    public Namespace pop() {
        return remove(this.namespaceStack.size() - 1);
    }

    public int size() {
        return this.namespaceStack.size();
    }

    public void clear() {
        this.namespaceStack.clear();
        this.namespaceCacheList.clear();
        this.rootNamespaceCache.clear();
        this.currentNamespaceCache = null;
    }

    public Namespace getNamespace(int i) {
        return (Namespace) this.namespaceStack.get(i);
    }

    public Namespace getNamespaceForPrefix(String str) {
        if (str == null) {
            str = "";
        }
        for (int size = this.namespaceStack.size() - 1; size >= 0; size--) {
            Namespace namespace = (Namespace) this.namespaceStack.get(size);
            if (str.equals(namespace.getPrefix())) {
                return namespace;
            }
        }
        return null;
    }

    public String getURI(String str) {
        Namespace namespaceForPrefix = getNamespaceForPrefix(str);
        if (namespaceForPrefix != null) {
            return namespaceForPrefix.getURI();
        }
        return null;
    }

    public boolean contains(Namespace namespace) {
        Namespace namespace2;
        String prefix = namespace.getPrefix();
        if (prefix == null || prefix.length() == 0) {
            namespace2 = getDefaultNamespace();
        } else {
            namespace2 = getNamespaceForPrefix(prefix);
        }
        if (namespace2 == null) {
            return false;
        }
        if (namespace2 == namespace) {
            return true;
        }
        return namespace.getURI().equals(namespace2.getURI());
    }

    public QName getQName(String str, String str2, String str3) {
        String str4 = "";
        if (str2 == null) {
            str2 = str3;
        } else if (str3 == null || str3.equals(str4)) {
            str3 = str2;
        } else {
            String str5 = str3;
            str3 = str2;
            str2 = str5;
        }
        if (str == null) {
            str = str4;
        }
        int indexOf = str2.indexOf(":");
        if (indexOf > 0) {
            str4 = str2.substring(0, indexOf);
            if (str3.trim().length() == 0) {
                str3 = str2.substring(indexOf + 1);
            }
        } else if (str3.trim().length() == 0) {
            str3 = str2;
        }
        return pushQName(str3, str2, createNamespace(str4, str), str4);
    }

    public QName getAttributeQName(String str, String str2, String str3) {
        Namespace namespace;
        String str4 = "";
        if (str3 == null || str3.equals(str4)) {
            str3 = str2;
        }
        Map namespaceCache = getNamespaceCache();
        QName qName = (QName) namespaceCache.get(str3);
        if (qName != null) {
            return qName;
        }
        if (str2 == null) {
            str2 = str3;
        }
        if (str == null) {
            str = str4;
        }
        int indexOf = str3.indexOf(":");
        if (indexOf > 0) {
            str4 = str3.substring(0, indexOf);
            namespace = createNamespace(str4, str);
            if (str2.trim().length() == 0) {
                str2 = str3.substring(indexOf + 1);
            }
        } else {
            namespace = Namespace.NO_NAMESPACE;
            if (str2.trim().length() == 0) {
                str2 = str3;
            }
        }
        QName pushQName = pushQName(str2, str3, namespace, str4);
        namespaceCache.put(str3, pushQName);
        return pushQName;
    }

    public void push(String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        push(createNamespace(str, str2));
    }

    public Namespace addNamespace(String str, String str2) {
        Namespace createNamespace = createNamespace(str, str2);
        push(createNamespace);
        return createNamespace;
    }

    public Namespace pop(String str) {
        if (str == null) {
            str = "";
        }
        Namespace namespace = null;
        int size = this.namespaceStack.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            Namespace namespace2 = (Namespace) this.namespaceStack.get(size);
            if (str.equals(namespace2.getPrefix())) {
                remove(size);
                namespace = namespace2;
                break;
            }
            size--;
        }
        if (namespace == null) {
            PrintStream printStream = System.out;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Warning: missing namespace prefix ignored: ");
            stringBuffer.append(str);
            printStream.println(stringBuffer.toString());
        }
        return namespace;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        stringBuffer.append(" Stack: ");
        stringBuffer.append(this.namespaceStack.toString());
        return stringBuffer.toString();
    }

    public DocumentFactory getDocumentFactory() {
        return this.documentFactory;
    }

    public void setDocumentFactory(DocumentFactory documentFactory2) {
        this.documentFactory = documentFactory2;
    }

    public Namespace getDefaultNamespace() {
        if (this.defaultNamespace == null) {
            this.defaultNamespace = findDefaultNamespace();
        }
        return this.defaultNamespace;
    }

    /* access modifiers changed from: protected */
    public QName pushQName(String str, String str2, Namespace namespace, String str3) {
        if (str3 == null || str3.length() == 0) {
            this.defaultNamespace = null;
        }
        return createQName(str, str2, namespace);
    }

    /* access modifiers changed from: protected */
    public QName createQName(String str, String str2, Namespace namespace) {
        return this.documentFactory.createQName(str, namespace);
    }

    /* access modifiers changed from: protected */
    public Namespace createNamespace(String str, String str2) {
        return this.documentFactory.createNamespace(str, str2);
    }

    /* access modifiers changed from: protected */
    public Namespace findDefaultNamespace() {
        for (int size = this.namespaceStack.size() - 1; size >= 0; size--) {
            Namespace namespace = (Namespace) this.namespaceStack.get(size);
            if (namespace != null && (namespace.getPrefix() == null || namespace.getPrefix().length() == 0)) {
                return namespace;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public Namespace remove(int i) {
        Namespace namespace = (Namespace) this.namespaceStack.remove(i);
        this.namespaceCacheList.remove(i);
        this.defaultNamespace = null;
        this.currentNamespaceCache = null;
        return namespace;
    }

    /* access modifiers changed from: protected */
    public Map getNamespaceCache() {
        if (this.currentNamespaceCache == null) {
            int size = this.namespaceStack.size() - 1;
            if (size < 0) {
                this.currentNamespaceCache = this.rootNamespaceCache;
            } else {
                Map map = (Map) this.namespaceCacheList.get(size);
                this.currentNamespaceCache = map;
                if (map == null) {
                    HashMap hashMap = new HashMap();
                    this.currentNamespaceCache = hashMap;
                    this.namespaceCacheList.set(size, hashMap);
                }
            }
        }
        return this.currentNamespaceCache;
    }
}
