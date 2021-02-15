package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;
import org.simpleframework.xml.stream.Style;

class CompositeKey implements Converter {
    private final Context context;
    private final Entry entry;
    private final Traverser root;
    private final Style style;
    private final Type type;

    public CompositeKey(Context context2, Entry entry2, Type type2) throws Exception {
        this.root = new Traverser(context2);
        this.style = context2.getStyle();
        this.context = context2;
        this.entry = entry2;
        this.type = type2;
    }

    public Object read(InputNode inputNode) throws Exception {
        Position position = inputNode.getPosition();
        Class type2 = this.type.getType();
        String key = this.entry.getKey();
        if (key == null) {
            key = this.context.getName(type2);
        }
        if (!this.entry.isAttribute()) {
            return read(inputNode, key);
        }
        throw new AttributeException("Can not have %s as an attribute for %s at %s", type2, this.entry, position);
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        Position position = inputNode.getPosition();
        Class type2 = this.type.getType();
        if (obj == null) {
            return read(inputNode);
        }
        throw new PersistenceException("Can not read key of %s for %s at %s", type2, this.entry, position);
    }

    private Object read(InputNode inputNode, String str) throws Exception {
        String element = this.style.getElement(str);
        Class type2 = this.type.getType();
        if (element != null) {
            inputNode = inputNode.getNext(element);
        }
        if (inputNode != null && !inputNode.isEmpty()) {
            return this.root.read(inputNode, type2);
        }
        return null;
    }

    public boolean validate(InputNode inputNode) throws Exception {
        Position position = inputNode.getPosition();
        Class type2 = this.type.getType();
        String key = this.entry.getKey();
        if (key == null) {
            key = this.context.getName(type2);
        }
        if (!this.entry.isAttribute()) {
            return validate(inputNode, key);
        }
        throw new ElementException("Can not have %s as an attribute for %s at %s", type2, this.entry, position);
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
        String key = this.entry.getKey();
        if (!this.entry.isAttribute()) {
            if (key == null) {
                key = this.context.getName(type2);
            }
            this.root.write(outputNode, obj, type2, this.style.getElement(key));
            return;
        }
        throw new ElementException("Can not have %s as an attribute for %s", type2, this.entry);
    }
}
