package org.simpleframework.xml.core;

import java.util.Collection;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

class CompositeInlineList implements Repeater {
    private final Type entry;
    private final CollectionFactory factory;
    private final String name;
    private final Traverser root;
    private final Type type;

    public CompositeInlineList(Context context, Type type2, Type type3, String str) {
        this.factory = new CollectionFactory(context, type2);
        this.root = new Traverser(context);
        this.entry = type3;
        this.type = type2;
        this.name = str;
    }

    public Object read(InputNode inputNode) throws Exception {
        Collection collection = (Collection) this.factory.getInstance();
        if (collection != null) {
            return read(inputNode, collection);
        }
        return null;
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        Collection collection = (Collection) obj;
        if (collection != null) {
            return read(inputNode, collection);
        }
        return read(inputNode);
    }

    private Object read(InputNode inputNode, Collection collection) throws Exception {
        InputNode parent = inputNode.getParent();
        String name2 = inputNode.getName();
        while (inputNode != null) {
            Object read = read(inputNode, this.entry.getType());
            if (read != null) {
                collection.add(read);
            }
            inputNode = parent.getNext(name2);
        }
        return collection;
    }

    private Object read(InputNode inputNode, Class cls) throws Exception {
        Object read = this.root.read(inputNode, cls);
        Class<?> cls2 = read.getClass();
        if (this.entry.getType().isAssignableFrom(cls2)) {
            return read;
        }
        throw new PersistenceException("Entry %s does not match %s for %s", cls2, this.entry, this.type);
    }

    public boolean validate(InputNode inputNode) throws Exception {
        InputNode parent = inputNode.getParent();
        Class type2 = this.entry.getType();
        String name2 = inputNode.getName();
        while (inputNode != null) {
            if (!this.root.validate(inputNode, type2)) {
                return false;
            }
            inputNode = parent.getNext(name2);
        }
        return true;
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        Collection collection = (Collection) obj;
        OutputNode parent = outputNode.getParent();
        if (!outputNode.isCommitted()) {
            outputNode.remove();
        }
        write(parent, collection);
    }

    public void write(OutputNode outputNode, Collection collection) throws Exception {
        for (Object next : collection) {
            if (next != null) {
                Class type2 = this.entry.getType();
                Class<?> cls = next.getClass();
                if (type2.isAssignableFrom(cls)) {
                    this.root.write(outputNode, next, type2, this.name);
                } else {
                    throw new PersistenceException("Entry %s does not match %s for %s", cls, type2, this.type);
                }
            }
        }
    }
}
