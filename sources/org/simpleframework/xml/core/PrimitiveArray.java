package org.simpleframework.xml.core;

import java.lang.reflect.Array;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;

class PrimitiveArray implements Converter {
    private final Type entry;
    private final ArrayFactory factory;
    private final String parent;
    private final Primitive root;
    private final Type type;

    public PrimitiveArray(Context context, Type type2, Type type3, String str) {
        this.factory = new ArrayFactory(context, type2);
        this.root = new Primitive(context, type3);
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
                Array.set(obj, i, this.root.read(next));
                i++;
            } else {
                throw new ElementException("Array length missing or incorrect for %s at %s", this.type, position);
            }
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
            if (next == null) {
                return true;
            }
            this.root.validate(next);
        }
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        int length = Array.getLength(obj);
        int i = 0;
        while (i < length) {
            OutputNode child = outputNode.getChild(this.parent);
            if (child != null) {
                write(child, obj, i);
                i++;
            } else {
                return;
            }
        }
    }

    private void write(OutputNode outputNode, Object obj, int i) throws Exception {
        Object obj2 = Array.get(obj, i);
        if (obj2 != null && !isOverridden(outputNode, obj2)) {
            this.root.write(outputNode, obj2);
        }
    }

    private boolean isOverridden(OutputNode outputNode, Object obj) throws Exception {
        return this.factory.setOverride(this.entry, obj, outputNode);
    }
}
