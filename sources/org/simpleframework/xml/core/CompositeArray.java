package org.simpleframework.xml.core;

import java.lang.reflect.Array;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;

class CompositeArray implements Converter {
    private final Type entry;
    private final ArrayFactory factory;
    private final String parent;
    private final Traverser root;
    private final Type type;

    public CompositeArray(Context context, Type type2, Type type3, String str) {
        this.factory = new ArrayFactory(context, type2);
        this.root = new Traverser(context);
        this.parent = str;
        this.entry = type3;
        this.type = type2;
    }

    public Object read(InputNode inputNode) throws Exception {
        Instance instance = this.factory.getInstance(inputNode);
        Object instance2 = instance.getInstance();
        return !instance.isReference() ? read(inputNode, instance2) : instance2;
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        int length = Array.getLength(obj);
        int i = 0;
        while (true) {
            Position position = inputNode.getPosition();
            InputNode next = inputNode.getNext();
            if (next == null) {
                return obj;
            }
            if (i < length) {
                read(next, obj, i);
                i++;
            } else {
                throw new ElementException("Array length missing or incorrect for %s at %s", this.type, position);
            }
        }
    }

    private void read(InputNode inputNode, Object obj, int i) throws Exception {
        Array.set(obj, i, !inputNode.isEmpty() ? this.root.read(inputNode, this.entry.getType()) : null);
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
            if (next == null) {
                return true;
            }
            if (!next.isEmpty()) {
                this.root.validate(next, cls);
            }
        }
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.root.write(outputNode, Array.get(obj, i), this.entry.getType(), this.parent);
        }
        outputNode.commit();
    }
}
