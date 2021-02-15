package org.simpleframework.xml.core;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

class LabelMap extends LinkedHashMap<String, Label> implements Iterable<Label> {
    private final Policy policy;

    public LabelMap() {
        this((Policy) null);
    }

    public LabelMap(Policy policy2) {
        this.policy = policy2;
    }

    public Iterator<Label> iterator() {
        return values().iterator();
    }

    public Label getLabel(String str) {
        return (Label) remove(str);
    }

    public String[] getKeys() throws Exception {
        HashSet hashSet = new HashSet();
        Iterator<Label> it = iterator();
        while (it.hasNext()) {
            Label next = it.next();
            if (next != null) {
                String path = next.getPath();
                String name = next.getName();
                hashSet.add(path);
                hashSet.add(name);
            }
        }
        return getArray(hashSet);
    }

    public String[] getPaths() throws Exception {
        HashSet hashSet = new HashSet();
        Iterator<Label> it = iterator();
        while (it.hasNext()) {
            Label next = it.next();
            if (next != null) {
                hashSet.add(next.getPath());
            }
        }
        return getArray(hashSet);
    }

    public LabelMap getLabels() throws Exception {
        LabelMap labelMap = new LabelMap(this.policy);
        Iterator<Label> it = iterator();
        while (it.hasNext()) {
            Label next = it.next();
            if (next != null) {
                labelMap.put(next.getPath(), next);
            }
        }
        return labelMap;
    }

    private String[] getArray(Set<String> set) {
        return (String[]) set.toArray(new String[0]);
    }

    public boolean isStrict(Context context) {
        if (this.policy == null) {
            return context.isStrict();
        }
        return context.isStrict() && this.policy.isStrict();
    }
}
