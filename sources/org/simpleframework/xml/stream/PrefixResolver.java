package org.simpleframework.xml.stream;

import java.util.Iterator;
import java.util.LinkedHashMap;

class PrefixResolver extends LinkedHashMap<String, String> implements NamespaceMap {
    private final OutputNode source;

    public PrefixResolver(OutputNode outputNode) {
        this.source = outputNode;
    }

    public String getPrefix() {
        return this.source.getPrefix();
    }

    public String setReference(String str) {
        return setReference(str, "");
    }

    public String setReference(String str, String str2) {
        if (resolvePrefix(str) != null) {
            return null;
        }
        return (String) put(str, str2);
    }

    public String getPrefix(String str) {
        String str2;
        if (size() <= 0 || (str2 = (String) get(str)) == null) {
            return resolvePrefix(str);
        }
        return str2;
    }

    public String getReference(String str) {
        if (containsValue(str)) {
            Iterator<String> it = iterator();
            while (it.hasNext()) {
                String next = it.next();
                String str2 = (String) get(next);
                if (str2 != null && str2.equals(str)) {
                    return next;
                }
            }
        }
        return resolveReference(str);
    }

    private String resolveReference(String str) {
        NamespaceMap namespaces = this.source.getNamespaces();
        if (namespaces != null) {
            return namespaces.getReference(str);
        }
        return null;
    }

    private String resolvePrefix(String str) {
        NamespaceMap namespaces = this.source.getNamespaces();
        if (namespaces == null) {
            return null;
        }
        String prefix = namespaces.getPrefix(str);
        if (!containsValue(prefix)) {
            return prefix;
        }
        return null;
    }

    public Iterator<String> iterator() {
        return keySet().iterator();
    }
}
