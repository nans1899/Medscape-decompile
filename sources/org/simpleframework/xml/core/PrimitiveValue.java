package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class PrimitiveValue implements Converter {
    private final Context context;
    private final Entry entry;
    private final PrimitiveFactory factory;
    private final Primitive root;
    private final Style style;
    private final Type type;

    public PrimitiveValue(Context context2, Entry entry2, Type type2) {
        this.factory = new PrimitiveFactory(context2, type2);
        this.root = new Primitive(context2, type2);
        this.style = context2.getStyle();
        this.context = context2;
        this.entry = entry2;
        this.type = type2;
    }

    public Object read(InputNode inputNode) throws Exception {
        Class type2 = this.type.getType();
        String value = this.entry.getValue();
        if (this.entry.isInline()) {
            return readAttribute(inputNode, value);
        }
        if (value == null) {
            value = this.context.getName(type2);
        }
        return readElement(inputNode, value);
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        Class type2 = this.type.getType();
        if (obj == null) {
            return read(inputNode);
        }
        throw new PersistenceException("Can not read value of %s for %s", type2, this.entry);
    }

    private Object readElement(InputNode inputNode, String str) throws Exception {
        InputNode next = inputNode.getNext(this.style.getAttribute(str));
        if (next == null) {
            return null;
        }
        return this.root.read(next);
    }

    private Object readAttribute(InputNode inputNode, String str) throws Exception {
        if (str != null) {
            inputNode = inputNode.getAttribute(this.style.getAttribute(str));
        }
        if (inputNode == null) {
            return null;
        }
        return this.root.read(inputNode);
    }

    public boolean validate(InputNode inputNode) throws Exception {
        Class type2 = this.type.getType();
        String value = this.entry.getValue();
        if (this.entry.isInline()) {
            return validateAttribute(inputNode, value);
        }
        if (value == null) {
            value = this.context.getName(type2);
        }
        return validateElement(inputNode, value);
    }

    private boolean validateElement(InputNode inputNode, String str) throws Exception {
        if (inputNode.getNext(this.style.getAttribute(str)) == null) {
            return true;
        }
        return this.root.validate(inputNode);
    }

    private boolean validateAttribute(InputNode inputNode, String str) throws Exception {
        if (str != null) {
            inputNode = inputNode.getNext(this.style.getAttribute(str));
        }
        if (inputNode == null) {
            return true;
        }
        return this.root.validate(inputNode);
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        Class type2 = this.type.getType();
        String value = this.entry.getValue();
        if (!this.entry.isInline()) {
            if (value == null) {
                value = this.context.getName(type2);
            }
            writeElement(outputNode, obj, value);
            return;
        }
        writeAttribute(outputNode, obj, value);
    }

    private void writeElement(OutputNode outputNode, Object obj, String str) throws Exception {
        OutputNode child = outputNode.getChild(this.style.getAttribute(str));
        if (obj != null && !isOverridden(child, obj)) {
            this.root.write(child, obj);
        }
    }

    private void writeAttribute(OutputNode outputNode, Object obj, String str) throws Exception {
        if (obj != null) {
            if (str != null) {
                outputNode = outputNode.setAttribute(this.style.getAttribute(str), (String) null);
            }
            this.root.write(outputNode, obj);
        }
    }

    private boolean isOverridden(OutputNode outputNode, Object obj) throws Exception {
        return this.factory.setOverride(this.type, obj, outputNode);
    }
}
