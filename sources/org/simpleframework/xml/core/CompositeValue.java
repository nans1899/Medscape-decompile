package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class CompositeValue implements Converter {
    private final Context context;
    private final Entry entry;
    private final Traverser root;
    private final Style style;
    private final Type type;

    public CompositeValue(Context context2, Entry entry2, Type type2) throws Exception {
        this.root = new Traverser(context2);
        this.style = context2.getStyle();
        this.context = context2;
        this.entry = entry2;
        this.type = type2;
    }

    public Object read(InputNode inputNode) throws Exception {
        InputNode next = inputNode.getNext();
        Class type2 = this.type.getType();
        if (next != null && !next.isEmpty()) {
            return this.root.read(next, type2);
        }
        return null;
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        Class type2 = this.type.getType();
        if (obj == null) {
            return read(inputNode);
        }
        throw new PersistenceException("Can not read value of %s for %s", type2, this.entry);
    }

    public boolean validate(InputNode inputNode) throws Exception {
        Class type2 = this.type.getType();
        String value = this.entry.getValue();
        if (value == null) {
            value = this.context.getName(type2);
        }
        return validate(inputNode, value);
    }

    private boolean validate(InputNode inputNode, String str) throws Exception {
        InputNode next = inputNode.getNext(this.style.getElement(str));
        Class type2 = this.type.getType();
        if (next != null && !next.isEmpty()) {
            return this.root.validate(next, type2);
        }
        return true;
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        Class type2 = this.type.getType();
        String value = this.entry.getValue();
        if (value == null) {
            value = this.context.getName(type2);
        }
        this.root.write(outputNode, obj, type2, this.style.getElement(value));
    }
}
