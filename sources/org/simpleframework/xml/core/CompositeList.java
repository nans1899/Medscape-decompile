package org.simpleframework.xml.core;

import java.util.Collection;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

class CompositeList implements Converter {
    private final Type entry;
    private final CollectionFactory factory;
    private final String name;
    private final Traverser root;
    private final Type type;

    public CompositeList(Context context, Type type2, Type type3, String str) {
        this.factory = new CollectionFactory(context, type2);
        this.root = new Traverser(context);
        this.entry = type3;
        this.type = type2;
        this.name = str;
    }

    public Object read(InputNode inputNode) throws Exception {
        Instance instance = this.factory.getInstance(inputNode);
        Object instance2 = instance.getInstance();
        return !instance.isReference() ? populate(inputNode, instance2) : instance2;
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        Instance instance = this.factory.getInstance(inputNode);
        if (instance.isReference()) {
            return instance.getInstance();
        }
        instance.setInstance(obj);
        return obj != null ? populate(inputNode, obj) : obj;
    }

    private Object populate(InputNode inputNode, Object obj) throws Exception {
        Collection collection = (Collection) obj;
        while (true) {
            InputNode next = inputNode.getNext();
            Class type2 = this.entry.getType();
            if (next == null) {
                return collection;
            }
            collection.add(this.root.read(next, type2));
        }
    }

    public boolean validate(InputNode inputNode) throws Exception {
        Instance instance = this.factory.getInstance(inputNode);
        if (instance.isReference()) {
            return true;
        }
        instance.setInstance((Object) null);
        return validate(inputNode, instance.getType());
    }

    private boolean validate(InputNode inputNode, Class cls) throws Exception {
        while (true) {
            InputNode next = inputNode.getNext();
            Class type2 = this.entry.getType();
            if (next == null) {
                return true;
            }
            this.root.validate(next, type2);
        }
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        for (Object next : (Collection) obj) {
            if (next != null) {
                Class type2 = this.entry.getType();
                Class<?> cls = next.getClass();
                if (type2.isAssignableFrom(cls)) {
                    this.root.write(outputNode, next, type2, this.name);
                } else {
                    throw new PersistenceException("Entry %s does not match %s for %s", cls, this.entry, this.type);
                }
            }
        }
    }
}
