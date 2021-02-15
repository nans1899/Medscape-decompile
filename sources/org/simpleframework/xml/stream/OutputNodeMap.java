package org.simpleframework.xml.stream;

import java.util.Iterator;
import java.util.LinkedHashMap;

class OutputNodeMap extends LinkedHashMap<String, OutputNode> implements NodeMap<OutputNode> {
    private final OutputNode source;

    public OutputNodeMap(OutputNode outputNode) {
        this.source = outputNode;
    }

    public OutputNode getNode() {
        return this.source;
    }

    public String getName() {
        return this.source.getName();
    }

    public OutputNode put(String str, String str2) {
        OutputAttribute outputAttribute = new OutputAttribute(this.source, str, str2);
        if (this.source != null) {
            put(str, outputAttribute);
        }
        return outputAttribute;
    }

    public OutputNode remove(String str) {
        return (OutputNode) super.remove(str);
    }

    public OutputNode get(String str) {
        return (OutputNode) super.get(str);
    }

    public Iterator<String> iterator() {
        return keySet().iterator();
    }
}
