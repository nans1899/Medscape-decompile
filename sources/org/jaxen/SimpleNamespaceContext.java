package org.jaxen;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SimpleNamespaceContext implements NamespaceContext, Serializable {
    private static final long serialVersionUID = -808928409643497762L;
    private Map namespaces;

    public SimpleNamespaceContext() {
        this.namespaces = new HashMap();
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0011  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SimpleNamespaceContext(java.util.Map r4) {
        /*
            r3 = this;
            r3.<init>()
            java.util.Set r0 = r4.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x000b:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0030
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            boolean r2 = r2 instanceof java.lang.String
            if (r2 == 0) goto L_0x0028
            java.lang.Object r1 = r1.getValue()
            boolean r1 = r1 instanceof java.lang.String
            if (r1 == 0) goto L_0x0028
            goto L_0x000b
        L_0x0028:
            java.lang.ClassCastException r4 = new java.lang.ClassCastException
            java.lang.String r0 = "Non-string namespace binding"
            r4.<init>(r0)
            throw r4
        L_0x0030:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>(r4)
            r3.namespaces = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jaxen.SimpleNamespaceContext.<init>(java.util.Map):void");
    }

    public void addElementNamespaces(Navigator navigator, Object obj) throws UnsupportedAxisException {
        Iterator namespaceAxisIterator = navigator.getNamespaceAxisIterator(obj);
        while (namespaceAxisIterator.hasNext()) {
            Object next = namespaceAxisIterator.next();
            String namespacePrefix = navigator.getNamespacePrefix(next);
            String namespaceStringValue = navigator.getNamespaceStringValue(next);
            if (translateNamespacePrefixToUri(namespacePrefix) == null) {
                addNamespace(namespacePrefix, namespaceStringValue);
            }
        }
    }

    public void addNamespace(String str, String str2) {
        this.namespaces.put(str, str2);
    }

    public String translateNamespacePrefixToUri(String str) {
        if (this.namespaces.containsKey(str)) {
            return (String) this.namespaces.get(str);
        }
        return null;
    }
}
