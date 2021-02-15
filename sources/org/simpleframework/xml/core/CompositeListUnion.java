package org.simpleframework.xml.core;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class CompositeListUnion implements Repeater {
    private final Context context;
    private final LabelMap elements;
    private final Group group;
    private final Expression path;
    private final Style style;
    private final Type type;

    public CompositeListUnion(Context context2, Group group2, Expression expression, Type type2) throws Exception {
        this.elements = group2.getElements();
        this.style = context2.getStyle();
        this.context = context2;
        this.group = group2;
        this.type = type2;
        this.path = expression;
    }

    public Object read(InputNode inputNode) throws Exception {
        if (this.group.getText() == null) {
            return readElement(inputNode);
        }
        return readText(inputNode);
    }

    private Object readElement(InputNode inputNode) throws Exception {
        return ((Label) this.elements.get(this.path.getElement(inputNode.getName()))).getConverter(this.context).read(inputNode);
    }

    private Object readText(InputNode inputNode) throws Exception {
        return this.group.getText().getConverter(this.context).read(inputNode);
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        return this.group.getText() != null ? readText(inputNode, obj) : readElement(inputNode, obj);
    }

    private Object readElement(InputNode inputNode, Object obj) throws Exception {
        return ((Label) this.elements.get(this.path.getElement(inputNode.getName()))).getConverter(this.context).read(inputNode, obj);
    }

    private Object readText(InputNode inputNode, Object obj) throws Exception {
        return this.group.getText().getConverter(this.context).read(inputNode.getParent(), obj);
    }

    public boolean validate(InputNode inputNode) throws Exception {
        return ((Label) this.elements.get(this.path.getElement(inputNode.getName()))).getConverter(this.context).validate(inputNode);
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        Collection collection = (Collection) obj;
        if (!this.group.isInline()) {
            write(outputNode, collection);
        } else if (!collection.isEmpty()) {
            write(outputNode, collection);
        } else if (!outputNode.isCommitted()) {
            outputNode.remove();
        }
    }

    private void write(OutputNode outputNode, Collection collection) throws Exception {
        for (Object next : collection) {
            if (next != null) {
                Class<?> cls = next.getClass();
                Label label = this.group.getLabel(cls);
                if (label != null) {
                    write(outputNode, next, label);
                } else {
                    throw new UnionException("Entry of %s not declared in %s with annotation %s", cls, this.type, this.group);
                }
            }
        }
    }

    private void write(OutputNode outputNode, Object obj, Label label) throws Exception {
        Converter converter = label.getConverter(this.context);
        Set singleton = Collections.singleton(obj);
        if (!label.isInline()) {
            String element = this.style.getElement(label.getName());
            if (!outputNode.isCommitted()) {
                outputNode.setName(element);
            }
        }
        converter.write(outputNode, singleton);
    }
}
