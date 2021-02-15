package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

class CompositeUnion implements Converter {
    private final Context context;
    private final LabelMap elements;
    private final Group group;
    private final Expression path;
    private final Type type;

    public CompositeUnion(Context context2, Group group2, Expression expression, Type type2) throws Exception {
        this.elements = group2.getElements();
        this.context = context2;
        this.group = group2;
        this.type = type2;
        this.path = expression;
    }

    public Object read(InputNode inputNode) throws Exception {
        return ((Label) this.elements.get(this.path.getElement(inputNode.getName()))).getConverter(this.context).read(inputNode);
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        return ((Label) this.elements.get(this.path.getElement(inputNode.getName()))).getConverter(this.context).read(inputNode, obj);
    }

    public boolean validate(InputNode inputNode) throws Exception {
        return ((Label) this.elements.get(this.path.getElement(inputNode.getName()))).getConverter(this.context).validate(inputNode);
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        Class<?> cls = obj.getClass();
        Label label = this.group.getLabel(cls);
        if (label != null) {
            write(outputNode, obj, label);
        } else {
            throw new UnionException("Value of %s not declared in %s with annotation %s", cls, this.type, this.group);
        }
    }

    private void write(OutputNode outputNode, Object obj, Label label) throws Exception {
        label.getConverter(this.context).write(outputNode, obj);
    }
}
