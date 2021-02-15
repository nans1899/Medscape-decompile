package org.simpleframework.xml.core;

import java.util.Iterator;
import java.util.LinkedHashMap;

class Collector implements Criteria {
    private final Registry alias = new Registry();
    private final Registry registry = new Registry();

    public Variable get(Object obj) {
        return (Variable) this.registry.get(obj);
    }

    public Variable get(Label label) throws Exception {
        if (label == null) {
            return null;
        }
        return (Variable) this.registry.get(label.getKey());
    }

    public Variable resolve(String str) {
        return (Variable) this.alias.get(str);
    }

    public Variable remove(Object obj) throws Exception {
        return (Variable) this.registry.remove(obj);
    }

    public Iterator<Object> iterator() {
        return this.registry.iterator();
    }

    public void set(Label label, Object obj) throws Exception {
        Variable variable = new Variable(label, obj);
        if (label != null) {
            String[] paths = label.getPaths();
            Object key = label.getKey();
            for (String put : paths) {
                this.alias.put(put, variable);
            }
            this.registry.put(key, variable);
        }
    }

    public void commit(Object obj) throws Exception {
        for (Variable variable : this.registry.values()) {
            variable.getContact().set(obj, variable.getValue());
        }
    }

    private static class Registry extends LinkedHashMap<Object, Variable> {
        private Registry() {
        }

        public Iterator<Object> iterator() {
            return keySet().iterator();
        }
    }
}
